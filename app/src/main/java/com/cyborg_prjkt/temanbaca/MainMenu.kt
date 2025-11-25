package com.cyborg_prjkt.temanbaca
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import com.cyborg_prjkt.temanbaca.AsesmenHuruf.AsesmenHuruf

@Suppress("DEPRECATION")
class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val asesmenbentukhurufdasar = findViewById<Button>(R.id.asesmenbentukhurufdasar)
        asesmenbentukhurufdasar.setOnClickListener {
            val asesmenbentukhurufdasar = Intent(this, AsesmenBentukHurufDasar::class.java)
            startActivity(asesmenbentukhurufdasar)
        }

        val asesmenhuruf = findViewById<Button>(R.id.asesmenHuruf)
        asesmenhuruf.setOnClickListener {
            val asesmenHuruf = Intent(this, AsesmenHuruf::class.java)
            startActivity(asesmenHuruf)
        }

        val asesmensukuKata = findViewById<Button>(R.id.asesmenSukukata)
        asesmensukuKata.setOnClickListener {
            val asesmensukukata = Intent(this, AsesmenSukuKata::class.java)
            startActivity(asesmensukukata)
        }
        val asesmenKata = findViewById<Button>(R.id.asesmenkata)
        asesmenKata.setOnClickListener {
            val asesmenkata = Intent(this, AsesmenKata::class.java)
            startActivity(asesmenkata)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}