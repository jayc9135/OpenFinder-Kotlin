package com.jayc.openfinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.firebase.firestore.*
import com.jayc.openfinder.adapters.CategoryAdapter
import com.jayc.openfinder.adapters.ViewPagerAdapter
import com.jayc.openfinder.models.Category

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryArrayList: ArrayList<Category>
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var db: FirebaseFirestore

    private lateinit var viewPager: ViewPager

    private lateinit var add_data_icon: ImageView
    private lateinit var searchIcon: ImageView

    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        mToolbar.title = ""
        setSupportActionBar(mToolbar)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.setHasFixedSize(true)

        categoryArrayList = arrayListOf()

        categoryAdapter = CategoryAdapter(this, categoryArrayList)

        recyclerView.adapter = categoryAdapter

        getData()

        initviewPager()

        add_data_icon = findViewById(R.id.add_icon)
        add_data_icon.setOnClickListener {
            openAddActivity()
        }
        searchIcon = findViewById(R.id.search_icon)
        searchIcon.setOnClickListener {
            openSearchActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.menu_add ->
                openAddActivity()
            R.id.menu_home ->
                Toast.makeText(this, item.title.toString(), Toast.LENGTH_SHORT).show()
            R.id.menu_search ->
                openSearchActivity()
            R.id.menu_credit ->
                openCreditActivity()
            R.id.menu_review ->
                openReviewActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getData() {
        db = FirebaseFirestore.getInstance()
        db.collection("Categories")
            .orderBy("category_name", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(p0: QuerySnapshot?, p1: FirebaseFirestoreException?) {
                    if (p1 != null) {
                        Log.e("Firestore Error", p1.message.toString())
                        return
                    }

                    for (dc: DocumentChange in p0?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            categoryArrayList.add(dc.document.toObject(Category::class.java))
                        }

                    }
                    categoryAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun initviewPager() {
        viewPager = findViewById<ViewPager>(R.id.viewPager)
//        sliderDotsPanel = findViewById<LinearLayout>(R.id.SliderDots)
        viewPager.adapter = ViewPagerAdapter(this)
//        dotscount = viewPagerA
    }

    private fun openAddActivity() {
        val intent = Intent(this, AddDataActivity::class.java)
        startActivity(intent)
    }

    private fun openReviewActivity() {
        val intent = Intent(this, ReviewActivity::class.java)
        startActivity(intent)
    }

    private fun openCreditActivity() {
        val intent = Intent(this, CreditActivity::class.java)
        startActivity(intent)
    }

    private fun openSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }
}