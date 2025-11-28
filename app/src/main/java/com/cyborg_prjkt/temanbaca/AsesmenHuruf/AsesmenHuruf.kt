package com.cyborg_prjkt.temanbaca.AsesmenHuruf

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyborg_prjkt.temanbaca.R

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
        R.id.btn5,
        R.id.btn6
    )
    private var currentSetIndex: Int = 0
    private var totalCorrectSets: Int = 0
    private val totalSets: Int = kombinasiList.size
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

        button.forEach { idbtn ->
            idbtn.setOnClickListener {
                toggleCheck(idbtn)
            }
        }

        btnNext = findViewById(R.id.next)
        btnNext.setOnClickListener {
            handleNextSet()
        }
        tampilkanKombinasi()
    }

    private fun tampilkanKombinasi() {
        val randomLetter = kombinasiList[currentSetIndex]
        val setKarakter: List<Char> = randomLetter
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.first() }

        val numberForButton: List<Char> = setKarakter.take(button.size)
        resetButtonState()

        val numberOFButtons = button.size
        if (numberForButton.size >= numberOFButtons) {
            for (i in 0 until numberOFButtons) {
                val currentButton = button[i]
                val LettertoAssign = numberForButton[i]
                val buttonText = LettertoAssign.toString()

                currentButton.tag = buttonText
                currentButton.text = buttonText
            }
        }
    }

    private fun handleNextSet() {
        val isSetCorrect = checkCurrentSetScoreAssumption()
        if (isSetCorrect) {
            totalCorrectSets++
            Toast.makeText(this, "Set ${currentSetIndex + 1}: BENAR (+1 Skor)", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Set ${currentSetIndex + 1}: SALAH", Toast.LENGTH_SHORT).show()
        }

        currentSetIndex++

        if (currentSetIndex >= totalSets) {

            val totalErrors = totalSets - totalCorrectSets

            val asesmenhuruf2 = Intent(this, AsesmenHuruf2::class.java)
            asesmenhuruf2.putExtra("SCORE_HURUF", totalErrors)

            startActivity(asesmenhuruf2)
            finish()

        } else {
            tampilkanKombinasi()
        }
    }

    private fun toggleCheck(button: Button) {
        val originalText = button.tag.toString() ?: ""
        val checkMark = "✔"

        if (button.text.toString() == checkMark) {
            button.text = originalText
        } else {
            button.text = checkMark
        }
    }

    private fun resetButtonState() {
        button.forEach { btn ->
            btn.text = btn.tag?.toString() ?: ""
            btn.isSelected = false
        }
    }

    private fun checkCurrentSetScoreAssumption(): Boolean {
        var correctCount = 0
        val totalButtons = button.size

        button.forEach { btn ->
            if (btn.text.toString() == "✔") {
                correctCount++
            }
        }
        return correctCount == totalButtons
    }
}