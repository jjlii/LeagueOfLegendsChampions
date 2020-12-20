package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.FragmentDetailBinding
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.bindingInflate
import com.example.leagueoflegendschampions.ui.commun.getViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = container?.bindingInflate(R.layout.fragment_detail, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { DetailViewModel(args.id, ChampionRepository(app)) }

        binding?.apply {
            viewmodel = viewModel
            lifecycleOwner = this@DetailFragment
        }
    }
}