package com.example.spaceweekapp.fragments.mapElements

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.provider.SyncStateContract.Helpers.update
import com.example.spaceweekapp.R
import kotlinx.android.synthetic.main.map.view.*
import uk.co.senab.photoview.PhotoViewAttacher


class MapFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(com.example.spaceweekapp.R.layout.map, container, false)
        val pAttacher: PhotoViewAttacher
        pAttacher = PhotoViewAttacher(view.findViewById(R.id.mapIamge))
        pAttacher.update()

        return view
    }

}