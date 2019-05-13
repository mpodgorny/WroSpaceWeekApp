package com.example.spaceweekapp.fragments.LecturesElements

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spaceweekapp.R

class LecturesAdapter() : RecyclerView.Adapter<LecturesViewHolder>() {

    override fun getItemCount(): Int {
        return 200
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.lecture_row, parent, false)
        return LecturesViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: LecturesViewHolder, position: Int) {
        //holder?.view?.title?.text = "testText"

    }


}

class LecturesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}