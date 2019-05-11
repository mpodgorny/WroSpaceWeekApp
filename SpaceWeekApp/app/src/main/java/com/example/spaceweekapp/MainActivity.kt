package com.example.spaceweekapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.spaceweekapp.DataClasses.Event
import com.example.spaceweekapp.DataClasses.Stand
import com.example.spaceweekapp.DataClasses.Speaker
import com.example.spaceweekapp.drawerElements.DrawerFragment
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.current_events.*


class MainActivity : AppCompatActivity() {

    lateinit var eventList: Map<String, Event>
    lateinit var speakerList : Map<String, Speaker>
    lateinit var standList : Map<String, Stand>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseManagement()

        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        var drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment!!.setUpDrawer(R.id.fragment_navigation_drawer, findViewById<View>(R.id.drawer_layout) as DrawerLayout, toolbar!!)

    }


    private fun firebaseManagement() {

        FirebaseDatabase.getInstance().getReference("events")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    this@MainActivity.eventList = dataSnapshot.value as  Map<String, Event>
                    testTxt.text=eventList.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

        FirebaseDatabase.getInstance().getReference("speakers")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    speakerList = dataSnapshot.getValue() as HashMap<String, Speaker>
                    testTxt2.text=speakerList.toString()

                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        FirebaseDatabase.getInstance().getReference("stands")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    standList = dataSnapshot.getValue() as HashMap<String, Stand>
                    testTxt3.text=standList.toString()

                }
                override fun onCancelled(databaseError: DatabaseError) {

                }
            })




    }
}
