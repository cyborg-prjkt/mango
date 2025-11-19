package com.cyborg_prjkt.temanbaca
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class AsesmenBentukHurufDasar : AppCompatActivity() {

    private lateinit var btnKapital: Button
    private lateinit var btnKecil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_bentuk_huruf_dasar)

        btnKapital = findViewById(R.id.hurufKapital)
        btnKecil = findViewById(R.id.hurufKecil)
        btnKapital.tag = btnKapital.text.toString()
        btnKecil.tag = btnKecil.text.toString()

        btnKapital.setOnClickListener {
            toggleCheck(btnKapital)
        }
        btnKecil.setOnClickListener {
            toggleCheck(btnKecil)
        }

    }
    private fun toggleCheck(button: Button) {
        val originalText = button.tag.toString() ?: button.text.toString()
        val currentText = button.text.toString()
        val checkMark = "✔"

        if (currentText.contains(checkMark)) {
            button.text = originalText
        } else {
            button.text = checkMark
        }
    }
}