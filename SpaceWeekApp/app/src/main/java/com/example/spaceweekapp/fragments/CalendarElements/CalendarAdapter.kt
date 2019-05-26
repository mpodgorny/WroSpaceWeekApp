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
import com.example.spaceweekapp.MainActivity.Companion.calendar
import java.security.AccessController.getContext


class CalendarAdapter(val context: Context, private val list: List<HashMap<String, Any>>) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CalendarViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        var event: HashMap<String, Any> = list[position]
        holder.bind(event, position, context, this)
    }

    override fun getItemCount(): Int = list.size


}

class CalendarViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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
    fun bind(event: HashMap<String, Any>, position: Int, context: Context, calendarAdapter: CalendarAdapter) {
        pos = position
        ev = event
        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("events", ev)
            bundle.putInt("test", 1)
        }
        itemView.setOnLongClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Usunąć z kalendarza?")
            builder.setPositiveButton("Tak") { _, _ ->
                calendar.remove(ev)
                calendarAdapter.notifyDataSetChanged()

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