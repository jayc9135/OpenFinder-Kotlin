package com.jayc.openfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.jayc.openfinder.R
import com.jayc.openfinder.adapters.SearchAdapter
import com.jayc.openfinder.models.Search

class SearchActivity : AppCompatActivity() {

    private lateinit var searchRecView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchArrayList: ArrayList<Search>

    private lateinit var searchBar: EditText
    private lateinit var searchBtn: ImageView

    private lateinit var back_btn: ImageView
    private lateinit var home_btn: ImageView
    private lateinit var add_data_icon: ImageView

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchRecView = findViewById(R.id.search_recyclerview)
        searchRecView.layoutManager = LinearLayoutManager(this)
        searchRecView.setHasFixedSize(true)


        searchArrayList = ArrayList()
        retrieveAllItems()

        searchBar = findViewById(R.id.searchBar)
        searchBtn = findViewById(R.id.search_button)
        searchBtn.setOnClickListener {
            searchApp(searchBar.text.toString().lowercase())
        }

        back_btn = findViewById(R.id.back_button)
        home_btn = findViewById(R.id.home_icon)
        add_data_icon = findViewById(R.id.add_icon)
        back_btn.setOnClickListener {
            goHome()
        }
        home_btn.setOnClickListener {
            goHome()
        }
        add_data_icon.setOnClickListener {
            openAddActivity()
        }
    }

    private fun retrieveAllItems() {
        val database = Firebase.database
        val myRef = database.reference.child("SearchDb")

        myRef.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                (searchArrayList as ArrayList<Search>).clear()
                if(searchBar.text.toString() == ""){
                    for (p0 in snapshot.children)
                    {
                        val searchItem: Search? = p0.getValue(Search::class.java)
                        if (searchItem != null) {
                            (searchArrayList as ArrayList<Search>).add(searchItem)
                        }
                    }
                }

                searchAdapter = SearchAdapter(context, searchArrayList, false)
                searchRecView.adapter = searchAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun searchApp(str: String){
        val database = Firebase.database
        val queryRef = database.reference
            .child("SearchDb").orderByChild("search")
            .startAt(str)
            .endAt(str + "\uf8ff")

        queryRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                (searchArrayList as ArrayList<Search>).clear()

                for (p0 in snapshot.children)
                {
                    val searchItem: Search? = p0.getValue(Search::class.java)
                    if (searchItem != null) {
                        (searchArrayList as ArrayList<Search>).add(searchItem)
                    }
                }
                searchAdapter = SearchAdapter(context, searchArrayList, false)
                searchRecView.adapter = searchAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
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
