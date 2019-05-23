package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.MainActivity
import com.example.spaceweekapp.R
import com.example.spaceweekapp.fragments.LecturesElements.LecturesAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.current_events.*
import kotlinx.android.synthetic.main.current_events.view.*
import kotlinx.android.synthetic.main.lectures.view.*
import java.lang.Exception

class CurrentEventsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FirebaseDatabase.getInstance().getReference("events")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var tempMap: Map<String, HashMap<String, Any>> = dataSnapshot.value as Map<String, HashMap<String, Any>>

                    my_recycler_view.layoutManager = LinearLayoutManager(context)
                    val manager = activity!!.supportFragmentManager
                    my_recycler_view.adapter  = CurrentEventsAdapter(context!!, tempMap.values.toList().sortedBy { it["beginning_id"] as Long }, manager)

                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        return inflater!!.inflate(R.layout.current_events, container, false)
    }
}