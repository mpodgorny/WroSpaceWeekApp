package com.example.spaceweekapp.drawerElements

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.*
import com.example.spaceweekapp.R
import com.example.spaceweekapp.fragments.*
import com.example.spaceweekapp.fragments.CalendarElements.CalendarFragment
import com.example.spaceweekapp.fragments.CurrentEventsElements.CurrentEventsFragment
import com.example.spaceweekapp.fragments.LecturesElements.LecturesFragment
import com.example.spaceweekapp.fragments.SpeakerElement.SpeakerFragment
import com.example.spaceweekapp.fragments.StandsElements.StandsFragment
import com.example.spaceweekapp.fragments.mapElements.MapFragment
import java.util.*

class DrawerFragment : Fragment() {

    private var views: View? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var drawerAdapter: DrawerAdapter? = null
    private lateinit var containerView: View
    private var recyclerView: RecyclerView? = null
    private val names = arrayOf("Wydarzenia", "Mapa", "Prelekcje", "Stoiska", "Prelegenci", "Mój kalendarz", "O wydarzeniu")

    private val images = intArrayOf(
        R.drawable.current_events,
        R.drawable.map,
        R.drawable.lecture,
        R.drawable.company,
        R.drawable.lecturer,
        R.drawable.calendar,
        R.drawable.info
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        views = inflater!!.inflate(R.layout.fragment_drawer, container, false)
        recyclerView = views!!.findViewById<View>(R.id.listview) as RecyclerView
        drawerAdapter =
            DrawerAdapter(activity!!.applicationContext, populateList())
        recyclerView!!.adapter = drawerAdapter
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.addOnItemTouchListener(
            RecyclerTouchListener(
                activity!!.applicationContext,
                recyclerView!!,
                object : ClickListener {
                    override fun onClick(view: View, position: Int) {
                        openFragment(position)
                        mDrawerLayout!!.closeDrawer(containerView)
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                })



        )

        openFragment(0)

        return views
    }





    private fun openFragment(position: Int) {

        when (position) {
            0 -> removeAllFragment(CurrentEventsFragment(), "Aktualności")
            1 -> removeAllFragment(MapFragment(), "Mapa")
            2 -> removeAllFragment(LecturesFragment(), "Prelekcje")
            3 -> removeAllFragment(StandsFragment(), "Stoiska")
            4 -> removeAllFragment(SpeakerFragment(), "Prelegenci")
            5 -> removeAllFragment(CalendarFragment(), "Mój kalendarz")
            6 -> removeAllFragment(AboutEventFragment(), "O wydarzeniu")

            else -> {
            }
        }
    }

    fun removeAllFragment(replaceFragment: Fragment, tag: String) {
        val manager = activity!!.supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        ft.replace(R.id.container_body, replaceFragment)
        ft.commitAllowingStateLoss()
    }

    fun setUpDrawer(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(
            activity, drawerLayout, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                activity!!.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }

    }

    private fun populateList(): ArrayList<DrawerModel> {

        val list = ArrayList<DrawerModel>()

        for (i in names.indices) {
            val drawerModel = DrawerModel()
            drawerModel.name = names[i]
            drawerModel.image = images[i]
            list.add(drawerModel)
        }
        return list
    }

    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                    }
                }
            })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

}