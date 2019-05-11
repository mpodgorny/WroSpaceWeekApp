package com.example.spaceweekapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.MainActivity
import com.example.spaceweekapp.R
import java.lang.Exception

class CurrentEventsFragment : Fragment() {
    lateinit var eventList : HashMap<String, Event>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater!!.inflate(R.layout.current_events, container, false)
    }

    override fun onStart(){
        super.onStart()
        //eventList = (activity as MainActivity).eventList
        //Toast.makeText(activity, (activity as MainActivity).eventList.toString(), Toast.LENGTH_LONG).show()

    }
}