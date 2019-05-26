package com.example.spaceweekapp.fragments.LecturesElements

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.R
import kotlinx.android.synthetic.main.lecture_row.view.*

class LecturesAdapter(var events: List<Event>) : RecyclerView.Adapter<LecturesViewHolder>() {

    override fun getItemCount(): Int = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.lecture_row, parent, false)
        return LecturesViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: LecturesViewHolder, position: Int) = holder.bind(events[position])


}

class LecturesViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(event: Event) {
        view.titleTextView.text = event.title
        view.textViewDescription.text = event.description
        view.textViewBuilding.text = event.building
        view.textViewBeginning.text = event.beginning_time
        view.textViewEnd.text = event.finish_time
        view.textViewSpeaker.text = event.speakers.toString()
            .replace("[", "")
            .replace("]", "")
        view.textViewRoom.text = event.room
    }

}