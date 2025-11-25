package com.cyborg_prjkt.temanbaca

import android.os.Bundle
import android.widget.Button
import android.widget.TextView // Import TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

@Suppress ("DEPRECATION")
class AsesmenKata : AppCompatActivity() {

    private val daftarKata: List<String> = listOf(
        "MEJA",
        "KERTAS",
        "PENSIL",
        "PAPAN",
        "GURU",
        "MURID",
        "KOTAK",
        "LAMPU",
        "PINTU",
        "JENDELA",
        "SEPEDA",
        "MOTOR",
        "RUMAH",
        "JALAN",
        "SUNGAI",
        "LANGIT",
        "AWAN",
        "BINTANG",
        "BULAN",
        "MATAHARI"
    )

    private lateinit var btnNext: Button
    private lateinit var tvKataList: List<TextView>
    private val randomGenerator = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_kata)

        btnNext = findViewById(R.id.next)
        val tvlist = listOf(
            R.id.Kata_1,
            R.id.Kata_2,
            R.id.Kata_3,
            R.id.Kata_4,
            R.id.Kata_5,
        )
        tvKataList = tvlist.map { findViewById(it) }
        KataAcak()

        btnNext.setOnClickListener {
            KataAcak()
        }
    }
    private fun KataAcak() {
        val uniqueIndices = mutableSetOf<Int>()
        while (uniqueIndices.size < 5) {
            val randomIndex = randomGenerator.nextInt(daftarKata.size)
            uniqueIndices.add(randomIndex)
        }
        val KataAcakList = uniqueIndices.map { daftarKata[it] }
        tvKataList.forEachIndexed { index, textView ->
            textView.text = KataAcakList[index]
        }
    }
}