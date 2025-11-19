package com.cyborg_prjkt.temanbaca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.media.MediaPlayer
import android.widget.VideoView
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

@Suppress ("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnplay = findViewById<Button>(R.id.btnplay)

        btnplay.setOnClickListener {
            val mainmenu = Intent(this, MainMenu::class.java)
            startActivity(mainmenu)
            finish()
        }
    }
}