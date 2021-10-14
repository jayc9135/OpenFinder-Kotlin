package com.jayc.openfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CreditActivity : AppCompatActivity() {

    private lateinit var back_btn: ImageView
    private lateinit var home_btn: ImageView
    private lateinit var add_data_icon: ImageView
    private lateinit var searchIcon: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        back_btn = findViewById(R.id.back_button)
        home_btn = findViewById(R.id.home_icon)
        back_btn.setOnClickListener {
            goHome()
        }
        home_btn.setOnClickListener {
            goHome()
        }
        add_data_icon = findViewById(R.id.add_icon)
        add_data_icon.setOnClickListener {
            openAddActivity()
        }
        searchIcon = findViewById(R.id.search_icon)
        searchIcon.setOnClickListener {
            openSearchActivity()
        }
    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openAddActivity() {
        val intent = Intent(this, AddDataActivity::class.java)
        startActivity(intent)
    }

    private fun openSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}