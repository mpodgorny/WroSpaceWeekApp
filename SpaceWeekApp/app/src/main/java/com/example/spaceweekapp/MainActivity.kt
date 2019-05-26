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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.current_events.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(
            OnCompleteListener { task->
                if(!task.isSuccessful){
                    println("failed")
                    return@OnCompleteListener
                }
                val token=task.result?.token
                println("---------------------------------------------------------------------------")
                println(token)



            })

        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        var drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment!!.setUpDrawer(
            R.id.fragment_navigation_drawer,
            findViewById<View>(R.id.drawer_layout) as DrawerLayout,
            toolbar!!
        )

    }

}
