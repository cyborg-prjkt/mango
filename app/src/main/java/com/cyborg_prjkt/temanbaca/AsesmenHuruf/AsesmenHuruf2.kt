package com.cyborg_prjkt.temanbaca.AsesmenHuruf

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyborg_prjkt.temanbaca.R

@Suppress("DEPRECATION")
class AsesmenHuruf2 : AppCompatActivity() {

    private val soalList: List<Pair<String, String>> = listOf(
        Pair("p d b q p d", "p"),
        Pair("p q b d q p", "q"),
        Pair("w m u n w m", "u"),
        Pair("a e o e o a", "e"),
        Pair("i l t i i t l", "i"),
        Pair("m u n w w m", "w"),
        Pair("e o u e o n", "n"),
        Pair("p b b d b p", "b"),
        Pair("t i t t l i", "t"),
        Pair("o o e e o a", "a")
    )

    private val letterTextViewIds = listOf(
        R.id.tv1,
        R.id.tv2,
        R.id.tv3,
        R.id.tv4,
        R.id.tv5,
        R.id.tv6,
    )

    private var currentSetIndex: Int = 0
    private var totalCorectScore: Int = 0
    private lateinit var currentTargetLetter: String
    private val totalSets: Int = soalList.size

    private lateinit var letterTextViews: List<TextView>
    private lateinit var btnNext: Button
    private lateinit var tvsoal: TextView
    private lateinit var tvscore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_huruf2)

        // START PERBAIKAN 2: SKOR MANDIRI (Tidak mengambil skor dari intent)
        // totalCorectScore = intent.getIntExtra("SCORE_HURUF_CORRECT", 0) // Baris ini dihapus/dinonaktifkan
        totalCorectScore = 0 // Skor dimulai dari 0 untuk Asesmen Huruf 2
        // END PERBAIKAN 2

        tvscore = findViewById(R.id.txtscore)
        tvsoal = findViewById(R.id.txtsoal)

        setFullscreenMode()

        val foundTextViews = mutableListOf<TextView>()
        for (id in letterTextViewIds) {
            val textViewObject = findViewById<TextView>(id)
            if (textViewObject != null) {
                foundTextViews.add(textViewObject)
            }
        }
        letterTextViews = foundTextViews

        letterTextViews.forEach { textView ->
            textView.setOnClickListener {
                toggleCheck(textView)
            }
        }

        btnNext = findViewById(R.id.next)
        btnNext.setOnClickListener {
            handleNextSet()
        }

        tampilkanKombinasi()
        updateScoreDisplay()
    }

    private fun tampilkanKombinasi() {
        if (currentSetIndex >= totalSets) return

        val (kombinasiString, targetLetter) = soalList[currentSetIndex]

        currentTargetLetter = targetLetter.toLowerCase()

        tvsoal.text = "Carilah huruf ${currentTargetLetter.toLowerCase()}"

        val setKarakter: List<Char> = kombinasiString.trim()
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.first() }

        val hurufUntukTextView: List<Char> = setKarakter.take(letterTextViews.size)
        resetButtonState()

        val numberOFTextViews = letterTextViews.size
        for (i in 0 until numberOFTextViews) {
            val currentTextView = letterTextViews[i]
            val LettertoAssign = hurufUntukTextView.getOrElse(i) { '-' }
            val textViewText = LettertoAssign.toString().toLowerCase()

            currentTextView.tag = textViewText
            currentTextView.text = textViewText
        }
    }

    private fun handleNextSet() {
        // START PERBAIKAN 1: Cek Jawaban Kosong
        // Cek apakah ada satupun TextView yang teksnya adalah "✔" (telah dicentang)
        val checkMark = "✔"
        val isAnswered = letterTextViews.any { it.text.toString() == checkMark }

        if (!isAnswered) {
            Toast.makeText(this, "Jawaban masih kosong, mohon centang terlebih dahulu", Toast.LENGTH_LONG).show()
            return
        }
        // END PERBAIKAN 1

        val scoreInSet = calculateSetScore()
        totalCorectScore += scoreInSet
        updateScoreDisplay()

        currentSetIndex++

        if (currentSetIndex >= totalSets) {
            // START PERBAIKAN 2: Kirim skor Asesmen Huruf 2 secara terpisah
            val intentResult = Intent(this, AsesmenHuruf::class.java).apply {
                // Mengganti nama ekstra untuk skor agar jelas ini skor dari Slide 2
                putExtra("SCORE_HURUF2_FINAL", totalCorectScore)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intentResult)
            finish()
            Toast.makeText(this, "Asesmen Huruf 2 Selesai. Skor Total: $totalCorectScore", Toast.LENGTH_LONG).show()
            // END PERBAIKAN 2

        } else {
            tampilkanKombinasi()
        }
    }

    private fun calculateSetScore(): Int {
        var correctCheck = 0
        val target = currentTargetLetter.toLowerCase()
        val checkMark = "✔" // Ditambahkan definisi checkMark

        letterTextViews.forEach { tv ->
            val originalLetter = tv.tag?.toString() ?: ""
            val isChecked = tv.text.toString() == checkMark // Menggunakan checkMark

            if (isChecked) {
                if (originalLetter == target) {
                    correctCheck++
                } else {
                    correctCheck--
                }
            } else {
                // Tambahkan pengecekan jika huruf target tidak dicentang (optional: bisa dikurangi 1)
                if (originalLetter == target) {
                    // correctCheck--
                }
            }
        }

        return maxOf(0, correctCheck)
    }

    private fun toggleCheck(textView: TextView) {
        val originalText = textView.tag?.toString() ?: ""
        val checkMark = "✔"

        if (textView.text.toString() == checkMark) {
            textView.text = originalText
        } else {
            textView.text = checkMark
        }
    }

    private fun resetButtonState() {
        letterTextViews.forEach { tv ->
            tv.text = tv.tag?.toString() ?: ""
            tv.isSelected = false
        }
    }

    private fun updateScoreDisplay() {
        tvscore.text = "Score: $totalCorectScore"
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