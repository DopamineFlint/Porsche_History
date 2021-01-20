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
import com.example.porschehistory.data.Year
import com.example.porschehistory.data.YearViewModel
import com.example.porschehistory.recycler.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_history.view.*
import kotlin.random.Random

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
        mYearViewModel.readAllData.observe(viewLifecycleOwner, Observer { year ->
            adapter.setData(year)
        })

        view.button_test_add.setOnClickListener {
            insertDataToDatabase()


        }

        return view
    }

    private fun insertDataToDatabase() {
        val randomYear = Random.nextInt(0, 2021)

        val year = Year(0, randomYear)
        mYearViewModel.addYear(year)

        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("CheckLog", "HistoryFragment created")
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(
            requireContext(),
            "You've pressed item in position $position",
            Toast.LENGTH_SHORT
        ).show()
    }

}