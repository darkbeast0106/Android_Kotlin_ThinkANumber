package com.darkbeast0106.kotlin_thinkanumber

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var eletek: Array<ImageView>
    private lateinit var tippeltSzamText: TextView
    private lateinit var btnNovel: Button
    private lateinit var btnCsokkent: Button
    private lateinit var btnTippel: Button
    private lateinit var btnKonnyu: Button
    private lateinit var btnNehez: Button
    private var gondoltSzam = 0
    private var tippeltSzam = 1
    private var maxSzam = 10
    private var elet = 3
    private lateinit var alertVege: AlertDialog.Builder
    private lateinit var alertNehezseg: AlertDialog.Builder
    private var nehezseg = false
    private lateinit var random: Random


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        ujJatek()

        btnTippel.setOnClickListener {
            when {
                gondoltSzam < tippeltSzam -> {
                    Toast.makeText(
                        this@MainActivity,
                        "A gondolt szám kisebb", Toast.LENGTH_SHORT
                    ).show()
                    eletLevon()
                }
                gondoltSzam > tippeltSzam -> {
                    Toast.makeText(
                        this@MainActivity,
                        "A gondolt szám nagyobb", Toast.LENGTH_SHORT
                    ).show()
                    eletLevon()
                }
                else -> {
                    alertVege.setTitle("Győzelem")
                    val dialog: AlertDialog = alertVege.create()
                    dialog.show()
                }
            }
        }
        btnNovel.setOnClickListener {
            if (tippeltSzam < maxSzam) {
                tippeltSzam++
                tippeltSzamText.text = tippeltSzam.toString()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "A szám nem lehet nagyobb mint 10", Toast.LENGTH_SHORT
                ).show()
            }
        }
        btnCsokkent.setOnClickListener {
            if (tippeltSzam > 1) {
                tippeltSzam--
                tippeltSzamText.text = tippeltSzam.toString()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "A szám nem lehet kisebb mint 1", Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnKonnyu.setOnClickListener {
            nehezseg = false
            alertNehezseg.create().show()
        }

        btnNehez.setOnClickListener {
            nehezseg = true
            alertNehezseg.create().show()
        }
    }

    private fun init() {
        val imageHp1 = findViewById<ImageView>(R.id.image_hp1)
        val imageHp2 = findViewById<ImageView>(R.id.image_hp2)
        val imageHp3 = findViewById<ImageView>(R.id.image_hp3)
        val imageHp4 = findViewById<ImageView>(R.id.image_hp4)
        val imageHp5 = findViewById<ImageView>(R.id.image_hp5)
        tippeltSzamText = findViewById(R.id.text_tippelt_szam)
        btnNovel = findViewById(R.id.btn_novel)
        btnCsokkent = findViewById(R.id.btn_csokkent)
        btnTippel = findViewById(R.id.btn_tipp)
        btnKonnyu = findViewById(R.id.btn_konnyu)
        btnNehez = findViewById(R.id.btn_nehez)
        eletek = arrayOf(imageHp1, imageHp2, imageHp3, imageHp4, imageHp5)
        alertVege = AlertDialog.Builder(this@MainActivity)
        alertVege.setCancelable(false).setMessage("Szeretne új játékot játszani?")
            .setPositiveButton("Igen") { _, _ -> ujJatek() }
            .setNegativeButton("Nem") { _, _ -> finish() }
        alertNehezseg = AlertDialog.Builder(this@MainActivity)
        alertNehezseg.setCancelable(false).setTitle("Nehézség váltása")
            .setMessage("Biztosan új játékot kezd a kiválasztott nehézséggel?")
            .setPositiveButton("Igen") { _, _ -> nehezsegAllit() }
            .setNegativeButton("Nem") { _, _ -> }
        random = Random()
    }

    private fun nehezsegAllit() {
        if (nehezseg) {
            maxSzam = 40
            eletek[3].visibility = View.VISIBLE
            eletek[4].visibility = View.VISIBLE
        } else {
            maxSzam = 10
            eletek[3].visibility = View.GONE
            eletek[4].visibility = View.GONE
        }
        ujJatek()
    }

    private fun ujJatek() {
        tippeltSzam = 1
        elet = when (maxSzam) {
            40 -> 5
            else -> 3
        }
        gondoltSzam = random.nextInt(maxSzam) + 1
        Log.d("gondolt", gondoltSzam.toString())
        tippeltSzamText.text = tippeltSzam.toString()
        for (elet in eletek) {
            elet.setImageResource(R.drawable.heart2)
        }
    }

    private fun eletLevon() {
        if (elet > 0) {
            elet--
            eletek[elet].setImageResource(R.drawable.heart1)
            if (elet < 1) {
                alertVege.setTitle("Vesztettél").create().show()
            }
        }
    }

}