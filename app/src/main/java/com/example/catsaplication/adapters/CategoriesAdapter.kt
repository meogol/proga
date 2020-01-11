package com.example.catsaplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.catsaplication.CategoriesActivity
import com.example.catsaplication.Networking.Category
import com.example.catsaplication.R

class CategoriesAdapter(categories : MutableList<Category>): RecyclerView.Adapter<CategoriesAdapter.Holder>()  {

    private var categoriesList : MutableList<Category> = categories
    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout
            .fragment_categories_item, parent, false)

        this.parent = parent

        return Holder(view)
    }

    override fun getItemCount(): Int = categoriesList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view){
        fun bind() {
            val tvCategory =  itemView.findViewById<TextView>(
                R.id.tvCategories)
            tvCategory.text = categoriesList[position].title

            val bNext = itemView.findViewById<Button>(R.id.bNext)

            bNext.setOnClickListener {
                (parent.context as CategoriesActivity).presenter.loadView(categoriesList[position].id)
            }
        }
    }
}