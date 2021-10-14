package com.jayc.openfinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jayc.openfinder.R
import com.jayc.openfinder.models.Software
import org.w3c.dom.Text


class SoftwareAdapter(private val context: Context, private val softwareList: ArrayList<Software>) : RecyclerView.Adapter<SoftwareAdapter.SoftwareViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SoftwareAdapter.SoftwareViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.software_list_item, parent, false)
        return SoftwareViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SoftwareAdapter.SoftwareViewHolder, position: Int) {
        val software: Software = softwareList[position]
        holder.heading.text = software.heading
        holder.description.text = software.description
        holder.downloadlink.text = software.downloadlink
        holder.sourcecode.text = software.sourcecode
        holder.rating.rating = software.rating!!.toFloat()
        val imageUrl = software.descriptionImage
        if (imageUrl != null) {
            holder.bind(software, imageUrl)
        }
    }

    override fun getItemCount() = softwareList.size

    inner class SoftwareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(software: Software, imageUrl: String) {
            Glide.with(context).load(imageUrl).into(descriptionImage)
        }

        val heading: TextView = itemView.findViewById(R.id.software_name)
        val descriptionImage: ImageView = itemView.findViewById(R.id.software_image)
        val description: TextView = itemView.findViewById(R.id.software_description)
        val downloadlink: TextView = itemView.findViewById(R.id.software_download_link)
        val sourcecode: TextView = itemView.findViewById(R.id.software_source_link)
        val rating: RatingBar = itemView.findViewById(R.id.software_rating_stars)

    }
}