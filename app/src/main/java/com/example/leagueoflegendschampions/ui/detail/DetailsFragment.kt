package com.example.leagueoflegendschampions.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.leagueoflegendschampions.R
import com.example.leagueoflegendschampions.databinding.FragmentDetailsBinding
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.ui.commun.app
import com.example.leagueoflegendschampions.ui.commun.getViewModel
import com.example.leagueoflegendschampions.ui.commun.loadUrl
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var binding: FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { DetailViewModel(
            args.id, ChampionRepository(app))
        }

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))

        binding?.championDetailFavorite?.setOnClickListener {
            viewModel.onFavoriteClicked()
        }


    }

    private fun updateUi(model: DetailViewModel.UiModel) =
        binding?.let {
            val champion = model.champion
            championDetailToolbar.title = champion.name
            championDetailImage.loadUrl(BaseURL.SPLASH_BASE_URL + champion.id + "_0.jpg", 500)
            championDetailSummary.text = champion.blurb
            championDetailInfo.setChampion(champion)
            val icon = if(champion.favorite)
                R.drawable.ic_favorite_on
            else
                R.drawable.ic_favorite_off

            context?.let {
                championDetailFavorite.setImageDrawable(ContextCompat.getDrawable(it, icon))
            }
        }

}