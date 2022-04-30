package com.jayc.openfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jayc.openfinder.R
import com.jayc.openfinder.models.Category
import com.jayc.openfinder.models.NewsHeadlines

class NewsHeadlineAdapter(private val context: Context, private val headlines: List<NewsHeadlines>): RecyclerView.Adapter<NewsHeadlineAdapter.NewsHeadlineViewHolder>() {



    inner class NewsHeadlineViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val newsHeadlineTextview: TextView = itemview.findViewById(R.id.news_headline_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHeadlineViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.news_headline_list_item, parent, false)
        return NewsHeadlineViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHeadlineViewHolder, position: Int) {
        holder.newsHeadlineTextview.text = headlines[position].title
    }

    override fun getItemCount(): Int {
        return headlines.size
    }

}