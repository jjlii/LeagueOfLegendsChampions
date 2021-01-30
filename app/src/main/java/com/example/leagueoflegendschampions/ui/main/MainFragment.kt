package com.example.leagueoflegendschampions.ui.main

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.leagueoflegendschampions.databinding.FragmentMainBinding
import com.example.leagueoflegendschampions.ui.commun.EventObserver
import com.example.leagueoflegendschampions.ui.commun.PermissionRequester
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : ScopeFragment() {

    private val viewModel: MainViewModel by viewModel()
    private var binding: FragmentMainBinding? = null
    private lateinit var adapter: ChampionAdapter

    private val coarsePermissionRequester by lazy {
        activity?.let {
            PermissionRequester(it,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        adapter = ChampionAdapter(viewModel::onChampionClick)
        binding?.championListView?.adapter = adapter

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        viewModel.navigation.observe(viewLifecycleOwner, EventObserver { champion->
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(champion.id)
            navController.navigate(action)
        })

    }

    private fun updateUi( model: MainViewModel.UiModel){
        if (model !is MainViewModel.UiModel.Loading){
            binding?.progress?.visibility = View.GONE
        }
        when(model){
            is MainViewModel.UiModel.Content -> adapter.championList = model.champions
            is MainViewModel.UiModel.Loading -> binding?.progress?.visibility = View.VISIBLE
            is MainViewModel.UiModel.RequestLocationPermission -> coarsePermissionRequester?.request {
                viewModel.onCoarsePermissionRequested()
            }
        }
    }

}