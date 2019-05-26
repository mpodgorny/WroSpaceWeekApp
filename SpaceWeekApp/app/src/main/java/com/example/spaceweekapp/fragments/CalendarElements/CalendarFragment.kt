package com.example.spaceweekapp.fragments.CalendarElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.MainActivity.Companion.calendar
import com.example.spaceweekapp.R
import com.example.spaceweekapp.fragments.CurrentEventsElements.CalendarAdapter
import com.example.spaceweekapp.fragments.CurrentEventsElements.CurrentEventsAdapter


class CalendarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view  = inflater!!.inflate(R.layout.calendar, container, false)
        var calendarRecycler = view.findViewById(R.id.calendar_recycler) as RecyclerView

        calendarRecycler.layoutManager = LinearLayoutManager(context)
        val manager = activity!!.supportFragmentManager
        calendarRecycler.adapter  = CalendarAdapter(context!!,
            calendar?.toList().sortedBy { it["beginning_id"] as Long })

        return view
    }


}