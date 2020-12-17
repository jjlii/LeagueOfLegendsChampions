package com.example.leagueoflegendschampions.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.leagueoflegendschampions.ui.commun.loadUrl

@BindingAdapter("url", "override")
fun ImageView.bindUrl(url: String?, override: Int){
    if (url != null){
        loadUrl(url, override)
    }
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?){
    visibility = visible?.let {
        if (visible) View.VISIBLE
        else
            View.GONE
    }?: View.GONE
}

