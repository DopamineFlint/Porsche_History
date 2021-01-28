package com.example.porschehistory.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.porschehistory.R
import com.example.porschehistory.data.Event
import com.example.porschehistory.data.relations.YearWithEvent
import kotlinx.android.synthetic.main.event_item.view.*

class EventRecyclerViewAdapter() :
    RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    private var yearWithEventList = emptyList<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.event_item,
                parent,
                false
            )
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = yearWithEventList[position]

        holder.itemView.event_text_view_card_item.text = currentItem.eventName
    }

    override fun getItemCount() = yearWithEventList.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.event_text_view_card_item
    }

    fun setData(eventsInYear: List<YearWithEvent>) {
        val eventsList = eventsInYear[0].events
        this.yearWithEventList = eventsList
        notifyDataSetChanged()
    }
}