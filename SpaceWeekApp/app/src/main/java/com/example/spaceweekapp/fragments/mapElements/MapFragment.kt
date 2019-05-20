package com.example.spaceweekapp.fragments.mapElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*


class MapFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(com.example.spaceweekapp.R.layout.map, container, false)


        return view
    }

}