package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.R
import kotlinx.android.synthetic.main.about_single_event.view.*
import kotlinx.android.synthetic.main.about_single_event.view.description
import kotlinx.android.synthetic.main.about_single_event.view.*
import kotlinx.android.synthetic.main.about_single_event.view.title


class AboutSingleEventFragment : Fragment() {

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

}

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater!!.inflate(R.layout.about_single_event, container, false)
    val list : HashMap<String, Any> = this.arguments?.get("events") as HashMap<String, Any>
    view.speakers.text= list["speakers"].toString().removePrefix("[").removeSuffix("]")
    view.description.text= list["info"].toString()
    view.title.text= list["title"].toString()
    view.start.text= list["beginning_time"].toString()
    view.finisk.text= list["finish_time"].toString()
    view.place.text=list["room"].toString()

    return view
}

    override fun onStop() {
        super.onStop()
        fragmentManager?.findFragmentByTag("list")
    }
}