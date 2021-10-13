package com.jayc.openfinder

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReviewActivity : AppCompatActivity() {

    private lateinit var back_btn: ImageView
    private lateinit var home_btn: ImageView
    private lateinit var submit_btn: Button
    private lateinit var review_name_input: EditText
    private lateinit var review_stars: RatingBar
    private lateinit var review_suggestion_input: EditText
    private lateinit var add_data_icon: ImageView

    private var database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        back_btn = findViewById(R.id.back_button)
        home_btn = findViewById(R.id.home_icon)
        submit_btn = findViewById(R.id.submit_btn)
        review_name_input = findViewById(R.id.review_name_input)
        review_stars = findViewById(R.id.review_stars)
        review_suggestion_input = findViewById(R.id.review_suggestion_input)

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

        val myRef = database.getReference("Ratings-Kotlin")
        submit_btn.setOnClickListener {
            var name = review_name_input.text.toString()
            var stars = review_stars.rating.toString()
            var suggestion = review_suggestion_input.text.toString()

            val newInput = input(name, stars, suggestion)
            myRef.push().setValue(newInput)
            Toast.makeText(this, "YOUR REVIEW IS SUBMITTED", Toast.LENGTH_SHORT).show()
        }


    }

    data class input(
        val name1: String? = null,
        val stars1: String? = null,
        val suggestion1: String? = null
    ) {

    }

    private fun goHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openAddActivity() {
        val intent = Intent(this, AddDataActivity::class.java)
        startActivity(intent)
    }
}