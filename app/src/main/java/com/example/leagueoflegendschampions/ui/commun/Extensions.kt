package com.example.leagueoflegendschampions.ui.commun

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.leagueoflegendschampions.ChampionApp
import com.example.leagueoflegendschampions.R
import java.lang.IllegalStateException

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun ImageView.loadUrl(url: String, override: Int) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_image_not_supported).override(override).into(this)
}
inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T{
    val vmFactory = object  : ViewModelProvider.Factory{
        override fun <U : ViewModel?> create(modelClass: Class<U>): U =
                factory() as U
    }
    return ViewModelProvider(this, vmFactory).get()
}

val Context.app: ChampionApp
    get() = applicationContext as ChampionApp

val Fragment.app: ChampionApp
    get() = ((activity?.app)
        ?: IllegalStateException("Fragment needs to be attach to the activity to access the App instance"))
            as ChampionApp