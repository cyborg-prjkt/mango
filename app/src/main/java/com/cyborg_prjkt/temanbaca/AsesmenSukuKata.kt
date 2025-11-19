package com.cyborg_prjkt.temanbaca

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView // Import TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

@Suppress ("DEPRECATION")
class AsesmenSukuKata : AppCompatActivity() {

    private val kombinasilist: Array<String> = arrayOf(
        "Ba", "Bi", "Bu", "Be", "Bo",
        "Ca", "Ci", "Cu", "Ce", "Co",
        "Da", "Di", "Du", "De", "Do",
        "Fa", "Fi", "Fu", "Fe", "Fo",
        "Ga", "Gi", "Gu", "Ge", "Go",
        "Ha", "Hi", "Hu", "He", "Ho",
        "Ja", "Ji", "Ju", "Je", "Jo",
        "Ka", "Ki", "Ku", "Ke", "Ko",
        "La", "Li", "Lu", "Le", "Lo",
        "Ma", "Mi", "Mu", "Me", "Mo",
        "Na", "Ni", "Nu", "Ne", "No",
        "Pa", "Pi", "Pu", "Pe", "Po",
        "Ra", "Ri", "Ru", "Re", "Ro",
        "Sa", "Si", "Su", "Se", "So",
        "Ta", "Ti", "Tu", "Te", "To",
        "Va", "Vi", "Vu", "Ve", "Vo",
        "Wa", "Wi", "Wu", "We", "Wo",
        "Ya", "Yi", "Yu", "Ye", "Yo",
        "Za", "Zi", "Zu", "Ze", "Zo"
    )
    private val buttonIds = listOf(
        R.id.syllable_1,
        R.id.syllable_2,
        R.id.syllable_3,
        R.id.syllable_4,
        R.id.syllable_5,
    )
    // PERBAIKAN: Mengganti List<Button> menjadi List<TextView>
    private lateinit var letterTextViews : List<TextView>
    private lateinit var btnNext : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_suku_kata)

        val foundTextViews = mutableListOf<TextView>()
        for (id in buttonIds) {
            val textViewObject = findViewById<TextView>(id)
            if (textViewObject != null) {
                foundTextViews.add(textViewObject)
            }
        }
        letterTextViews = foundTextViews

        btnNext = findViewById(R.id.next)
        btnNext.setOnClickListener {
            tampilkanKombinasi()
        }

        tampilkanKombinasi()
    }

    private fun tampilkanKombinasi() {
        val generatorRandom = Random()
        val requiredSize = letterTextViews.size
        val syllabelsToDisplay = mutableListOf<String>()

        val generatePairs = mutableSetOf<String>()
        while (syllabelsToDisplay.size < requiredSize) {
            val randomIndex1 = generatorRandom.nextInt(kombinasilist.size)
            val kataDepan = kombinasilist[randomIndex1]
            var kataBelakang: String
            var randomIndex2: Int

            do {
                randomIndex2 = generatorRandom.nextInt(kombinasilist.size)
                kataBelakang = kombinasilist[randomIndex2]
            }while (kataDepan == kataBelakang)

            val combinedSyllable = "$kataDepan-$kataBelakang"

            if (!generatePairs.contains(combinedSyllable)) {
                generatePairs.add(combinedSyllable)
                syllabelsToDisplay.add(combinedSyllable)
            }
        }
        syllabelsToDisplay.shuffle(generatorRandom)

        syllabelsToDisplay.forEachIndexed { index, syllable ->
            val currentText = letterTextViews[index]
            currentText.text = syllable
        }
    }
}