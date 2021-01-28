package com.example.porschehistory.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.porschehistory.R
import com.example.porschehistory.data.YearViewModel
import com.example.porschehistory.recycler.EventRecyclerViewAdapter
import com.example.porschehistory.recycler.RecyclerViewAdapter
import kotlinx.android.synthetic.main.example_item.view.*
import kotlinx.android.synthetic.main.fragment_history.view.*

class HistoryFragment : Fragment(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var mYearViewModel: YearViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        mYearViewModel = ViewModelProvider(this).get(YearViewModel::class.java)

        val adapter = RecyclerViewAdapter(this)
        val recyclerView = view.recycler_view_history_timeline
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val eventAdapter = EventRecyclerViewAdapter()
        val eventRecyclerView = view.recycler_view_events
        eventRecyclerView.adapter = eventAdapter
        eventRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        mYearViewModel.readAllData.observe(viewLifecycleOwner, Observer { year ->
            adapter.setData(year)
        })

        mYearViewModel.eventsInCurrentYear.observe(viewLifecycleOwner, Observer { yearWithEvent ->
            eventAdapter.setData(yearWithEvent)
            Log.d("MyLog", "$yearWithEvent")
        })

        view.button_test_add.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CheckLog", "HistoryFragment created")
    }

    override fun onItemClick(position: Int, iv: View) {
        val year = iv.text_view_card_item.text.toString().toInt()
        mYearViewModel.setCurrentYearEvents(year)
    }

}