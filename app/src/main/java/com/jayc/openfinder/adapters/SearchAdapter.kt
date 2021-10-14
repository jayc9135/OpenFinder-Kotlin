package com.jayc.openfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jayc.openfinder.R
import com.jayc.openfinder.models.Search

class SearchAdapter(
    private val mContext: Context,
    private val mSearchList: List<Search>,
    private val isCheck: Boolean
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val app: TextView = itemView.findViewById(R.id.app_text)
        val alt1: TextView = itemView.findViewById(R.id.alt1_text)
        val alt2: TextView = itemView.findViewById(R.id.alt2_text)
        val alt3: TextView = itemView.findViewById(R.id.alt3_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search: Search = mSearchList[position]
        holder.app.text = search.app
        holder.alt1.text = search.alt1
        holder.alt2.text = search.alt2
        holder.alt3.text = search.alt3
    }

    override fun getItemCount() = mSearchList.size

}