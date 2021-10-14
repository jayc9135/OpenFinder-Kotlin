package com.jayc.openfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddDataActivity : AppCompatActivity() {

    private lateinit var back_btn: ImageView
    private lateinit var home_btn: ImageView
    private lateinit var submit_btn: Button
    private lateinit var app_input: EditText
    private lateinit var alt_input: EditText
    private lateinit var searchIcon: ImageView

    private var database = Firebase.database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        back_btn = findViewById(R.id.back_button)
        home_btn = findViewById(R.id.home_icon)
        submit_btn = findViewById(R.id.submit_btn)
        app_input = findViewById(R.id.app_name_input)
        alt_input = findViewById(R.id.alt_name_input)

        back_btn.setOnClickListener {
            goHome()
        }
        home_btn.setOnClickListener {
            goHome()
        }
        searchIcon = findViewById(R.id.search_icon)
        searchIcon.setOnClickListener {
            openSearchActivity()
        }

        val myRef = database.getReference("UserInput-Kotlin")
        submit_btn.setOnClickListener {
            var original = app_input.text.toString()
            var alternate = alt_input.text.toString()

            val newInput = input(original, alternate)
            myRef.push().setValue(newInput)
            Toast.makeText(this, "YOUR INPUT IS SUBMITTED", Toast.LENGTH_SHORT).show()
        }


    }

    data class input(val original1: String? = null, val alternate1: String? = null) {

    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

}