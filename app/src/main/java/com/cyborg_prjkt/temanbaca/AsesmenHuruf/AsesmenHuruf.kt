package com.cyborg_prjkt.temanbaca.AsesmenHuruf

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.cyborg_prjkt.temanbaca.R
import java.util.Random


@Suppress("DEPRECATION")
class AsesmenHuruf : AppCompatActivity() {

    private val kombinasiList: List<String> = listOf(
        "b d p q b q",
        "y m n w u n",
        "i l t i l t",
        "c e i a e o",
        "a a o e a e",
        "g q o c g q",
        "s z c s z s",
        "a c q o q c",
        "t f i t l f",
        "m w n u m w"
    )
    private val buttonIds = listOf(
        R.id.btn1,
        R.id.btn2,
        R.id.btn3,
        R.id.btn4,
        R.id.btn5
    )
    private lateinit var button: List<Button>
    private lateinit var btnRandom: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_huruf)

        val foundButtons = mutableListOf<Button>()
        for (id in buttonIds) {
            val buttonObject = findViewById<Button>(id)
            foundButtons.add(buttonObject)
        }
        button = foundButtons

        btnRandom = findViewById(R.id.random)
        btnRandom.setOnClickListener {
            tampilkanKombinasi()
        }

        button.forEach { idbtn ->
            idbtn.setOnClickListener {
                toggleCheck(idbtn)
            }
        }

        btnNext = findViewById(R.id.next)
        btnNext.setOnClickListener {
            val asesmenhuruf2 = Intent(this, AsesmenHuruf2::class.java)
            startActivity(asesmenhuruf2)
        }
        tampilkanKombinasi()
    }

    private fun tampilkanKombinasi() {
        val randomGenerator = Random()
        val randomIndex = randomGenerator.nextInt(kombinasiList.size)
        val randomLetter = kombinasiList[randomIndex]
        val setKarakter: List<Char> = randomLetter
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.first() }

        val numberForButton: List<Char> = setKarakter.take(6)
        val numberOFButtons = button.size
        if (numberForButton.size >= numberOFButtons) {
            for (i in 0 until numberOFButtons) {
                val currentButton = button[i]
                val LettertoAssign = numberForButton[i]
                val buttonText = LettertoAssign.toString().toLowerCase()
                currentButton.tag = buttonText
                currentButton.text = buttonText
        }
        }
    }

    private fun toggleCheck(button: Button) {
        val originalText = button.tag.toString() ?: ""
        val currentText = button.text.toString()
        val checkMark = "✔"

        if (currentText.contains(checkMark)) {
            button.text = originalText
        } else {
            button.text = checkMark
        }
    }
}