package com.example.porschehistory.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.porschehistory.R
import com.example.porschehistory.data.Year
import kotlinx.android.synthetic.main.example_item.view.*

class RecyclerViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.ExampleViewHolder>() {

    private var yearList = emptyList<Year>()

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = yearList[position]

        holder.itemView.text_view_card_item.text = currentItem.year.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.example_item,
                parent,
                false
            )

        return ExampleViewHolder(itemView)
    }

    override fun getItemCount() = yearList.size //exampleList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val textView: TextView = itemView.text_view_card_item

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setData(year: List<Year>) {
        this.yearList = year
        notifyDataSetChanged()
    }
}