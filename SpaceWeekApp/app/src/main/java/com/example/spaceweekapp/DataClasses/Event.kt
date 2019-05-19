package com.example.spaceweekapp.DataClasses

data class Event(
    val beginning_time: String,
    val building: String,
    val day: Int,
    val description: String,
    val finish_time: String,
    val floor: String,
    val id: Int,
    val room: String,
    val speakers: List<String>,
    val type: String,
    val title: String,
    var beginning_id: Int
)