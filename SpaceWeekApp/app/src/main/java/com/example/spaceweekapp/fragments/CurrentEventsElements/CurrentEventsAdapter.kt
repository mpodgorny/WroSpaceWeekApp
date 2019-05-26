package com.example.spaceweekapp.fragments.CurrentEventsElements

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.spaceweekapp.MainActivity.Companion.calendar


class CurrentEventsAdapter(
    val context: Context,
    private val list: List<HashMap<String, Any>>,
    val manager: FragmentManager
) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        var event: HashMap<String, Any> = list[position]
        println(event.toString())


        holder.bind(event, position, manager, context)
    }

    override fun getItemCount(): Int = list.size

}

class EventViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            com.example.spaceweekapp.R.layout.one_current_event_element,
            parent,
            false
        )
    ) {
    var moreInfo: MutableList<String>? = null
    private var title: TextView? = null
    private var description: TextView? = null
    private var time: TextView? = null
    private var speaker: TextView? = null
    private var place: TextView? = null
    private var pos: Int = 0
    private lateinit var ev: HashMap<String, Any>

    init {
        title = itemView.findViewById(com.example.spaceweekapp.R.id.title)
        description = itemView.findViewById(com.example.spaceweekapp.R.id.description)
        time = itemView.findViewById(com.example.spaceweekapp.R.id.time)
        speaker = itemView.findViewById(com.example.spaceweekapp.R.id.speaker)
        place = itemView.findViewById(com.example.spaceweekapp.R.id.place)
    }

    @SuppressLint("SetTextI18n")
    fun bind(event: HashMap<String, Any>, position: Int, manager: FragmentManager, context: Context) {
        if (event["type"] == "prelekcja") {

        }
        pos = position
        ev = event

        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("events", ev)
            bundle.putInt("test", 1)
            val ft = manager.beginTransaction()
            val newFragment: AboutSingleEventFragment = AboutSingleEventFragment()
            newFragment.arguments = bundle
            ft.replace(com.example.spaceweekapp.R.id.container_body, newFragment)
            ft.addToBackStack("list")
            ft.commitAllowingStateLoss()
        }
        itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Dodać do kalendarza?")
            builder.setPositiveButton("Tak") { _, _ ->
                calendar.add(ev)

            }
            builder.setNegativeButton("Nie") { _, _ ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            true
        }

        place?.text = event.getOrDefault("room", " ") as String
        title?.text = event.getOrDefault("title", "err") as String
        description?.text = event.getOrDefault("description", "err") as String
        time?.text = event.getOrDefault("beginning_time", "err") as String + "-" + event.getOrDefault(
            "finish_time",
            "err"
        ) as String
        speaker?.text = event.getOrDefault("speakers", "err").toString().removePrefix("[").removeSuffix("]")

    }

}