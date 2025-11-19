package com.cyborg_prjkt.temanbaca.AsesmenHuruf

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView // Import TextView
import androidx.appcompat.app.AppCompatActivity
import com.cyborg_prjkt.temanbaca.R
import java.util.Random

@Suppress ("DEPRECATION")
class AsesmenHuruf2 : AppCompatActivity() {

    private val kombinasilist : Array<Char> = arrayOf(
        'a','b','c','d','e','f','g','h','i','j','k','l','m',
        'n','o','p','q','r','s','t','u','v','w','x','y','z'
    )
    private val buttonIds = listOf(
        R.id.tvC,
        R.id.tvL,
        R.id.tvX,
        R.id.tvZ,
        R.id.tvQ,
        R.id.tvF,
        R.id.tvY,
        R.id.tvN,
    )
    private lateinit var letterTextViews : List<TextView>
    private lateinit var btnAcak : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_huruf2)

        val foundTextViews = mutableListOf<TextView>()
        for (id in buttonIds) {
            val textViewObject = findViewById<TextView>(id)
            if (textViewObject != null) {
                foundTextViews.add(textViewObject)
            }
        }
        letterTextViews = foundTextViews

        btnAcak = findViewById(R.id.acak)
        btnAcak.setOnClickListener {
            tampilkanKombinasi()
        }

        tampilkanKombinasi()
    }

    private fun tampilkanKombinasi() {
        val randomGenerator = Random()
        val selectedLetters = mutableListOf<Char>()
        val requiredSize = letterTextViews.size

        while (selectedLetters.size < requiredSize){
            val alphabetSize = kombinasilist.size
            val randomIndex = randomGenerator.nextInt(alphabetSize)
            val randomLetter = kombinasilist[randomIndex]
            if (!selectedLetters.contains(randomLetter)){
                selectedLetters.add(randomLetter)
            }
        }

        selectedLetters.shuffle(randomGenerator)

        selectedLetters.forEachIndexed { index, letter ->
            val currentView = letterTextViews[index]
            currentView.text = letter.toString().lowercase()
        }
    }
}