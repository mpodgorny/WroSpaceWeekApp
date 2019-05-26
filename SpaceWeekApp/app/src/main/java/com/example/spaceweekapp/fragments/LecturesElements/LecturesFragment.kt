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
import kotlinx.android.synthetic.main.about_single_event.*
import kotlinx.android.synthetic.main.lectures.view.*
import kotlinx.android.synthetic.main.one_current_event_element.*


class LecturesFragment : Fragment() {

    var events = mutableListOf<Event>()
    var speakerToShow = String()
    var specificSpeaker = false;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (arguments?.containsKey("speakerToShow") == true) {
            speakerToShow = arguments?.getString("speakerToShow").toString()
            specificSpeaker = true
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        FirebaseDatabase.getInstance().getReference("events")
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    var tempMap = dataSnapshot.value as Map<String, HashMap<String, Any>>
                    var count =0

                    for ((eventId, value) in tempMap) {

                        if (specificSpeaker) {
                            if (!value["speakers"].toString().contains(speakerToShow, true)) {
                                continue;
                            }
                        }
                        count++


                        events.add(
                            Event(
                                value[("beginning_time")].toString(),
                                value["building"].toString(),
                                value["day"].toString().toInt(),
                                value["description"].toString(),
                                value["finish_time"].toString(),
                                value["floor"].toString(),
                                value["id"].toString().toInt(),
                                value["room"].toString(),
                                value["speakers"] as List<String>,
                                value["type"].toString(),
                                value["title"].toString(),
                                value["beginning_id"].toString().toInt()
                            )
                        )
                    }
                    if(count==0){
                        getFragmentManager()!!.popBackStack()
                    }
                    events.sortBy { it.beginning_id }

                    view?.lecturesRecyclerView?.adapter = LecturesAdapter(events)

                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })


        var view = inflater!!.inflate(R.layout.lectures, container, false)
        view.lecturesRecyclerView.layoutManager = LinearLayoutManager(context)
        view.lecturesRecyclerView.adapter = LecturesAdapter(events)






        return view
    }


}