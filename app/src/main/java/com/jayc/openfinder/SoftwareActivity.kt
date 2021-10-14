package com.jayc.openfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.jayc.openfinder.adapters.SoftwareAdapter
import com.jayc.openfinder.models.Software

class SoftwareActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var softwareArrayList: ArrayList<Software>
    private lateinit var softwareAdapter: SoftwareAdapter
    private lateinit var db: FirebaseFirestore

    private lateinit var category_heading: TextView

    private lateinit var back_btn: ImageView
    private lateinit var home_btn: ImageView
    private lateinit var add_data_icon: ImageView
    private lateinit var searchIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_software)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        softwareArrayList = arrayListOf()
        softwareAdapter = SoftwareAdapter(this, softwareArrayList)
        recyclerView.adapter = softwareAdapter
        getData()

        back_btn = findViewById(R.id.back_button)
        home_btn = findViewById(R.id.home_icon)
        add_data_icon = findViewById(R.id.add_icon)
        add_data_icon.setOnClickListener {
            openAddActivity()
        }
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
    }

    private fun getData() {
        val category = intent.getStringExtra("heading")
        category_heading = findViewById(R.id.software_heading)
        category_heading.text = category

        val argToPass = intent.getStringExtra("argToPass")

        db = FirebaseFirestore.getInstance()
        if (argToPass != null) {
            db.collection(argToPass)
                .addSnapshotListener(object : EventListener<QuerySnapshot> {
                    override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                        if (p1 != null) {
                            Log.e("Firestore Error", p1.message.toString())
                            return
                        }

                        for (dc: DocumentChange in p0?.documentChanges!!) {
                            if (dc.type == DocumentChange.Type.ADDED) {
                                softwareArrayList.add(dc.document.toObject(Software::class.java))
                            }

                        }
                        softwareAdapter.notifyDataSetChanged()
                    }
                })
        }

    }

    private fun openAddActivity() {
        val intent = Intent(this, AddDataActivity::class.java)
        startActivity(intent)
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