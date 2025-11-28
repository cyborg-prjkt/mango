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
class AsesmenHuruf : AppCompatActivity(){

    private val kombinasiList : Array<Char> = arrayOf(
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z'
    )
    private val buttonIds = listOf(
        R.id.btn1,
        R.id.btn2,
        R.id.btn3,
        R.id.btn4,
        R.id.btn5
    )
    private lateinit var button : List<Button>
    private lateinit var btnRandom : Button
    private lateinit var btnNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_huruf)

        val foundButtons = mutableListOf<Button>()
        for (id in buttonIds){
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
    }
    private fun tampilkanKombinasi() {
        val randomGenerator = Random()
        val selectedLetters = mutableListOf<Char>()
        while (selectedLetters.size < 5){
            val alphabetSize = kombinasiList.size
            val randomIndex = randomGenerator.nextInt(alphabetSize)
            val randomLetter = kombinasiList[randomIndex]
            if (!selectedLetters.contains(randomLetter)){
                selectedLetters.add(randomLetter)
            }
        }
        val numberOFButtons = button.size
        for (i in 0 until numberOFButtons){
            val currentButton = button[i]
            val LettertoAssign = selectedLetters[i]
            val buttonText = LettertoAssign.toString().toLowerCase()
            currentButton.text = buttonText
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