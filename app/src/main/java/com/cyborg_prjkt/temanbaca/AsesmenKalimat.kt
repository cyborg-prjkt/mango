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
class AsesmenKalimat : AppCompatActivity() {

    private val daftarKalimat: List<String> = listOf(
        "Ibu sedang memasak nasi di dapur.",
        "Adi pergi ke sekolah dengan memakai sepatu baru.",
        "Kucing itu tidur di atas bantal yang empuk.",
        "Langit terlihat sangat biru dan cerah hari ini.",
        "Saya suka membaca buku cerita fantasi.",
        "Tolong tutup pintu itu dengan pelan.",
        "Pohon mangga di depan rumah sudah berbuah.",
        "Ayah membeli koran di warung dekat rumah."
    )

    private lateinit var btnNext: Button
    private lateinit var tvKalimatList: List<TextView>
    private val randomGenerator = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asesmen_kalimat)

        btnNext = findViewById(R.id.next)
        val tvlist = listOf(
            R.id.kalimat_1,
            R.id.kalimat_2,
            R.id.kalimat_3,
            R.id.kalimat_4,
            R.id.kalimat_5,
        )
        tvKalimatList = tvlist.map { findViewById(it) }
        KalimatAcak()

        btnNext.setOnClickListener {
            KalimatAcak()
        }
    }
    private fun KalimatAcak() {
        val uniqueIndices = mutableSetOf<Int>()
        while (uniqueIndices.size < 5) {
            val randomIndex = randomGenerator.nextInt(daftarKalimat.size)
            uniqueIndices.add(randomIndex)
        }
        val kalimatAcakList = uniqueIndices.map { daftarKalimat[it] }
        tvKalimatList.forEachIndexed { index, textView ->
            textView.text = kalimatAcakList[index]
        }
    }
}