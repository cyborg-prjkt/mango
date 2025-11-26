package com.cyborg_prjkt.temanbaca
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Random

@Suppress("DEPRECATION")
class AsesmenBentukHurufDasar : AppCompatActivity() {

    private val alfabetList: Array<String> = arrayOf(
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z"
    )

    private lateinit var btnKapital: MaterialButton
    private lateinit var btnKecil: MaterialButton
    private lateinit var btnAcak: MaterialButton
    private val randomGenerator = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_bentuk_huruf_dasar)

        btnAcak = findViewById(R.id.acak)
        btnKapital = findViewById(R.id.hurufKapital)
        btnKecil = findViewById(R.id.hurufKecil)
        btnAcak = findViewById(R.id.acak)

        btnAcak.setOnClickListener {
            tampilkanHurufAcak()
        }

        btnKapital.tag = btnKapital.text.toString()
        btnKecil.tag = btnKecil.text.toString()

        btnKapital.setOnClickListener {
            toggleCheck(btnKapital)
        }
        btnKecil.setOnClickListener {
            toggleCheck(btnKecil)
        }

    }
    private fun tampilkanHurufAcak() {
        val randomIndex = randomGenerator.nextInt(alfabetList.size)
        val hurufAcak = alfabetList[randomIndex]
        val hurufKapital = hurufAcak.uppercase()
        val hurufKecil = hurufAcak.lowercase()

        btnKapital.text = hurufKapital
        btnKecil.text = hurufKecil
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