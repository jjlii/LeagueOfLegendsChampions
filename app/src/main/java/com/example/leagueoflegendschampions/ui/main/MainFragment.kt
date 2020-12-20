package com.example.leagueoflegendschampions.ui.main

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.leagueoflegendschampions.PermissionRequester
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.FragmentMainBinding
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var binding : FragmentMainBinding? = null
    private lateinit var adapter : ChampionAdapter
    private val coarsePermissionRequester by lazy {
        activity?.let {
            PermissionRequester(
                it,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.bindingInflate(R.layout.fragment_main, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        viewModel = getViewModel { MainViewModel(ChampionRepository(app)) }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainFragment
        }

        adapter =ChampionAdapter(viewModel::onChampionClick)
        binding?.championListView?.adapter = adapter

        viewModel.navigation.observe(viewLifecycleOwner, EventObserver { id->
            navController.navigate(
                R.id.action_mainFragment_to_detailFragment,
                bundleOf("id" to id)
            )
        })

        viewModel.requestLocationPermission.observe(viewLifecycleOwner, EventObserver{
            coarsePermissionRequester?.request {
                viewModel.onCoarsePermissionRequested()
            }
        })
    }
}