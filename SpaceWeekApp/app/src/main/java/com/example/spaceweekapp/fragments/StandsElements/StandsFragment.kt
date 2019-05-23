package com.example.spaceweekapp.fragments.StandsElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.DataClasses.Stand
import com.example.spaceweekapp.R
import com.example.spaceweekapp.fragments.CurrentEventsElements.CurrentEventsAdapter
import com.example.spaceweekapp.fragments.CurrentEventsElements.StandAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.current_events.*
import kotlinx.android.synthetic.main.stands.*


class StandsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseDatabase.getInstance().getReference("stands")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    var tempMap: Map<String, HashMap<String, Any>> = dataSnapshot.value as Map<String, HashMap<String, Any>>
                    my_recycler_view_stands.layoutManager = LinearLayoutManager(context)
                    val manager = activity!!.supportFragmentManager
                    my_recycler_view_stands.adapter  = StandAdapter(context!!, tempMap.values.toList().sortedBy { it["title"] as String }, manager)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        return inflater!!.inflate(R.layout.stands, container, false)
    }

}