package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.current_events.*

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
                    var tempMap: MutableMap<String, HashMap<String, Any>> = dataSnapshot.value as MutableMap<String, HashMap<String, Any>>
                    my_recycler_view.layoutManager = LinearLayoutManager(context)
                    val manager = activity!!.supportFragmentManager

                    with(tempMap.iterator()) {
                        forEach { if (it.value["type"] == "prelekcja") remove() }
                    }

                    my_recycler_view.adapter  = CurrentEventsAdapter(context!!, tempMap.values.toList().sortedBy { it["beginning_id"] as Long }, manager)

                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        return inflater!!.inflate(R.layout.current_events, container, false)
    }
}