package com.example.porschehistory.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.porschehistory.R
import com.example.porschehistory.data.YearViewModel
import com.example.porschehistory.recycler.EventRecyclerViewAdapter
import com.example.porschehistory.recycler.HistoryItemDecoration
import com.example.porschehistory.recycler.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlinx.android.synthetic.main.history_item_concept.view.*

class HistoryFragment : Fragment() {

    private lateinit var mYearViewModel: YearViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        mYearViewModel = ViewModelProvider(this).get(YearViewModel::class.java)

        //RecyclerView for timeline
        val adapter = RecyclerViewAdapter()
        val recyclerView = view.recycler_view_history_timeline
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        recyclerView.addItemDecoration(HistoryItemDecoration())

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    val centerView = snapHelper.findSnapView(recyclerView.layoutManager)

                    if (centerView != null) {
                        val pos: Int? = recyclerView.layoutManager?.getPosition(centerView)
                        val year = centerView.history_text_year.text.toString().toInt()
                        mYearViewModel.setCurrentYearEvents(year)
                        Log.d("PositionLog", "$pos")
                    } else {
                        Log.d("PositionLog", "null")
                    }
                }
            }
        })

        //RecyclerView for events
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

        //will be removed later
        //view.button_test_add.setOnClickListener {
        //    insertDataToDatabase()
        //}

        return view
    }

    //will be removed later
    private fun insertDataToDatabase() {
        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CheckLog", "HistoryFragment created")
    }

}