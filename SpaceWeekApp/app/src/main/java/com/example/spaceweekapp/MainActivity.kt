package com.example.spaceweekapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.spaceweekapp.drawerElements.DrawerFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() { companion object{
    var calendar : MutableList<HashMap<String, Any>> = emptyList<HashMap<String, Any>>().toMutableList()
}

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
        drawerFragment.setUpDrawer(
            R.id.fragment_navigation_drawer,
            findViewById<View>(R.id.drawer_layout) as DrawerLayout,
            toolbar!!
        )

    }

}
