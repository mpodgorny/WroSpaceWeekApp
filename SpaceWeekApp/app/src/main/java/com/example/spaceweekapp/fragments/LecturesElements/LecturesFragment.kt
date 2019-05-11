package com.example.spaceweekapp.fragments.LecturesElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.DataClasses.Speaker
import com.example.spaceweekapp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.current_events.*
import kotlinx.android.synthetic.main.lectures.*


class LecturesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseDatabase.getInstance().getReference("speakers")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var tempMap: Map<String, Speaker> = dataSnapshot.value as Map<String, Speaker>
                    testSpeakers.text = tempMap.toString()

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

        return inflater!!.inflate(R.layout.lectures, container, false)
    }

}