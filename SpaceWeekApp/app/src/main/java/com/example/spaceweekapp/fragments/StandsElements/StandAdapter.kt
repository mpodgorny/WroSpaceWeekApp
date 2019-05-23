package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import android.view.View.OnLongClickListener
import android.widget.ImageView
import com.example.spaceweekapp.R
import java.security.AccessController.getContext


class StandAdapter (val context: Context, private val list: List<HashMap<String, Any>>,val manager: FragmentManager) : RecyclerView.Adapter<StandViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StandViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StandViewHolder, position: Int) {
        var stand: HashMap<String, Any> = list[position]
        holder.bind(stand, position, manager, context)
    }

    override fun getItemCount(): Int = list.size

}

class StandViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(com.example.spaceweekapp.R.layout.one_stand_element, parent, false)) {
    var moreInfo: MutableList<String>? = null
    private var long_place: TextView? = null
    private var description: TextView? = null
    private var organisation: TextView? = null
    private var title: TextView? = null
    private var photo: ImageView?=null
    private var place: TextView? = null
    private  var pos : Int = 0
    private lateinit var ev : HashMap<String, Any>
    init {
        title = itemView.findViewById(com.example.spaceweekapp.R.id.stand_name)
        description = itemView.findViewById(com.example.spaceweekapp.R.id.stand_description)
        place = itemView.findViewById(com.example.spaceweekapp.R.id.stand_place)
        long_place = itemView.findViewById(com.example.spaceweekapp.R.id.stand_long_place)
        organisation = itemView.findViewById(com.example.spaceweekapp.R.id.stand_organisation)
        photo=itemView.findViewById(com.example.spaceweekapp.R.id.stand_photo)


    }

    @SuppressLint("SetTextI18n")
    fun bind(stand: HashMap<String, Any>, position: Int, manager: FragmentManager, context : Context)  {
        title?.text=stand.getOrDefault("title", " ") as String
        description?.text=stand.getOrDefault("description", " ") as String
        place?.text=stand.getOrDefault("place", " ") as String
        long_place?.text=stand.getOrDefault("long_place", " ") as String
        organisation?.text=stand.getOrDefault("organisation", " ") as String
        photo?.setImageResource(R.drawable.stands)

    }

}