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

    // Daftar 10 set huruf asesmen yang spesifik
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

    // ID 5 tombol asesmen di layout
    private val buttonIds = listOf(
        R.id.btn1,
        R.id.btn2,
        R.id.btn3,
        R.id.btn4,
        R.id.btn5
    )

    // Variabel Pelacak Progress dan Skor
    private var currentSetIndex: Int = 0          // Index set yang sedang ditampilkan (0 hingga 9)
    private var totalCorectScore: Int = 0         // Total skor kesalahan centang
    private lateinit var currentTargetLetter: String // Huruf Target untuk set saat ini
    private val totalSets: Int = kombinasiList.size // Total 10 set

    // Variabel View
    private lateinit var button: List<Button>
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_huruf)

        setFullscreenMode()

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
        val selectedSetString = kombinasiList[currentSetIndex]

        val targetCandidate = selectedSetString.trim().split(" ")

        if (targetCandidate.size > 2){
            currentTargetLetter = targetCandidate[2]
        }else{
            currentTargetLetter = targetCandidate[0]
        }

        Toast.makeText(this, "Carilah huruf ${currentTargetLetter.toUpperCase()}", Toast.LENGTH_LONG).show()

        val setKarakter: List<Char> = targetCandidate
            .filter { it.isNotBlank() }
            .map { it.first() }

        val hurufUntukTombol: List<Char> = setKarakter.take(button.size)

        resetButtonState()

        val numberOFButtons = button.size
        if (hurufUntukTombol.size >= numberOFButtons) {
            for (i in 0 until numberOFButtons) {
                val currentButton = button[i]
                val LettertoAssign = hurufUntukTombol[i]
                val buttonText = LettertoAssign.toString()

                currentButton.tag = buttonText
                currentButton.text = buttonText
            }
        }
    }

    private fun handleNextSet() {
        val isAnswared = button.any { it.text.toString() == "✔"}
        if (!isAnswared) {
            Toast.makeText(this, "Jawaban masih kosong mohon centang terlebih dahulu", Toast.LENGTH_LONG).show()
            return
        }

        val scoreInSet = calculateSetScore()

        totalCorectScore += scoreInSet

        Toast.makeText(this, "Set ${currentSetIndex + 1}: Score Benar $scoreInSet. Total Score: $totalCorectScore", Toast.LENGTH_LONG).show()

        currentSetIndex++

        if (currentSetIndex >= totalSets) {

            val asesmenhuruf2 = Intent(this, AsesmenHuruf2::class.java)
            asesmenhuruf2.putExtra("SCORE_HURUF_CORRECT", totalCorectScore)

            startActivity(asesmenhuruf2)
            finish()

        } else {
            tampilkanKombinasi()
        }
    }

    private fun calculateSetScore(): Int {
        var correctCheck = 0
        val target = currentTargetLetter // Huruf target dari set saat ini

        button.forEach { btn ->
            val originalLetter = btn.tag?.toString() ?: ""
            val isChecked = btn.text.toString() == "✔"

            if (isChecked) {

                if (originalLetter == target) {
                    correctCheck++
                }
            }
        }
        return correctCheck
    }

    private fun toggleCheck(button: Button) {
        val originalText = button.tag?.toString() ?: ""
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

    private fun setFullscreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
    }
}