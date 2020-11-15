package com.example.leagueoflegendschampions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : CoroutineScopeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}