package com.example.porschehistory.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.porschehistory.R
import com.example.porschehistory.data.Year
import kotlinx.android.synthetic.main.example_item.view.*

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ExampleViewHolder>() {

    private var yearList = emptyList<Year>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.example_item,
                parent,
                false
            )

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = yearList[position]

        holder.itemView.text_view_card_item.text = currentItem.year.toString()
    }

    override fun getItemCount() = yearList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView.text_view_card_item

    }

    fun setData(year: List<Year>) {
        this.yearList = year
        notifyDataSetChanged()
    }
}