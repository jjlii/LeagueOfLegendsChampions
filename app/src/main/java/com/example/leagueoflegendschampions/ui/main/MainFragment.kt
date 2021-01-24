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
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.leagueoflegendschampions.data.AndroidPermissionChecker
import com.example.leagueoflegendschampions.data.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.data.database.RoomDataSource
import com.example.leagueoflegendschampions.data.server.ChampionDbDataSource
import com.example.leagueoflegendschampions.ui.commun.PermissionRequester
import com.example.leagueoflegendschampions.databinding.FragmentMainBinding
import com.example.leagueoflegendschampions.ui.commun.EventObserver
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.usecases.GetChampionsUseCase


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        getViewModel {
            app.component.mainViewModel
        }
    }
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