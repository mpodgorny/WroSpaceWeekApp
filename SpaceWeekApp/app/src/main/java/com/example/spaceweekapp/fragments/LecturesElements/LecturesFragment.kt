package com.example.spaceweekapp.fragments.LecturesElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import kotlinx.android.synthetic.main.lectures.view.*


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


                    //titleTextView.text = tempMap.toString()

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })


        var view = inflater!!.inflate(R.layout.lectures, container, false)


        /////////////MOCK EVENTS
        var speakers = listOf("Andrzej Duda")
        var events = mutableListOf<Event>()
        val event1 = Event(
            "10:00",
            "budynek xyz",
            1, "kamien papier nozyce kamien papier nozyce kamien papier nozyce kamien papier nozyce " +
                    "kamien papier nozyce kamien papier nozyce kamien " +
                    "papier nozyce kamien papier nozyce kamien papier nozyce kamien papier nozyce " +
                    "kamien papier nozyce kamien papier nozyce kamien papier nozyce ",
            "12:00", "1", 0, "404", speakers, "prelekcja",
            "RAMBO 2 a zmysł równowagi u słoneczników", 100
        )
        events.add(event1)

        /////////////////

        //view.lecturesRecyclerView.setBackgroundColor(Color.LTGRAY)
        view.lecturesRecyclerView.layoutManager = LinearLayoutManager(context)
        view.lecturesRecyclerView.adapter = LecturesAdapter()






        return view
    }

}