package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.R

class CurrentEventsAdapter(private val list: List<HashMap<String, Any>>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {


        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var event : HashMap<String, Any> = list[position] as HashMap<String, Any>
        holder.bind(event)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.one_current_event_element, parent, false)) {
    private var title: TextView? = null
    private var description: TextView? = null
    private var time: TextView? = null
    private var speaker: TextView? = null
    private var place: TextView? = null



    init {
        Log.d("test","tutaj!XD")
        title = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.description)
        time = itemView.findViewById(R.id.time)
        speaker = itemView.findViewById(R.id.speaker)
        place = itemView.findViewById(R.id.place)

    }

    fun bind(event: HashMap<String, Any>) {
        title?.text=event.getOrDefault("title","err") as String
        description?.text=event.getOrDefault("description", "err") as String
        time?.text=event.getOrDefault("beginning_time","err") as String + "-" + event.getOrDefault("finish_time","err") as String
        speaker?.text=event.getOrDefault("speakers","err").toString().removePrefix("[").removeSuffix("]")

    }

}