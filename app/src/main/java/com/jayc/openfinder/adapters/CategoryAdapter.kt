package com.jayc.openfinder.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jayc.openfinder.R
import com.jayc.openfinder.SoftwareActivity
import com.jayc.openfinder.models.Category


class CategoryAdapter(private val context: Context, private val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = categoryList[position]

        holder.bind(category)
    }

    override fun getItemCount() = categoryList.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var argToPass: String
        fun bind(category: Category) {
            categoryName.text = category.category_name
            Glide.with(context).load(category.category_image).into(categoryImage)
            argToPass = category.argToPass.toString()
        }

        init{
            itemView.setOnClickListener{
                val intent = Intent(context, SoftwareActivity::class.java)
                var heading: String = categoryName.text as String
                intent.putExtra("heading", heading)
                intent.putExtra("argToPass", argToPass)
                itemView.context.startActivity(intent)
            }
        }

        val categoryName: TextView = itemView.findViewById(R.id.category_name)
        val categoryImage: ImageView = itemView.findViewById(R.id.category_image)
    }



}