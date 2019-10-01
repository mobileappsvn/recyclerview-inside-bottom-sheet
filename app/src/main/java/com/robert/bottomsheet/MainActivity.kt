package com.robert.bottomsheet

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button

import java.util.ArrayList

class MainActivity : AppCompatActivity(), MediaItemAdapter.ItemListener {


    private var behavior: BottomSheetBehavior<*>? = null
    private var recyclerView: RecyclerView? = null
    private var mAdapter: MediaItemAdapter? = null
    private var coordinatorLayout: CoordinatorLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        coordinatorLayout = findViewById(R.id.coordinatorLayout)


        val bottomSheet = findViewById<View>(R.id.bottom_sheet)
        behavior = BottomSheetBehavior.from(bottomSheet)
        behavior!!.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })



        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.setHasFixedSize(true)


        val items = ArrayList<String>()
        items.add("https://kucasino.net/wp-content/uploads/mc-mai-ngoc.png")
        items.add("https://we25.vn/media/images/o-anhvong3%20(4).jpg")
        items.add("https://ttol.vietnamnetjsc.vn/images/2018/12/08/09/41/hotgirl-3.jpg")
        items.add("http://a9.vietbao.vn/images/vn899/150/2019/05/20190523-nhung-hotgirl-phong-gym-viet-voi-vong-3-nong-bong-khong-thua-gi-co-kim-4.jpg")
        items.add("https://66.media.tumblr.com/a8b40c82ea3c700542b547c604fb98db/tumblr_pt3l5roG0v1yq3z6so1_500.jpg")
        items.add("https://cdn.24h.com.vn/upload/4-2018/images/2018-11-11/1541939252-661-raver-1541815005-width714height1029.jpg")
        items.add("https://vcdn-ngoisao.vnecdn.net/2018/12/07/5-1544149843_680x0.jpg")
        items.add("https://photo-1-baomoi.zadn.vn/w1000_r1/2017_09_12_180_23257281/ee267d1b8b5d62033b4c.jpg")
        items.add("https://66.media.tumblr.com/a8b40c82ea3c700542b547c604fb98db/tumblr_pt3l5roG0v1yq3z6so1_500.jpg")
        items.add("https://duyendangvietnam.net.vn/public/uploads/files/Chau%20Chau/1(13).jpg")


        mAdapter = MediaItemAdapter(this, items, this)
        recyclerView!!.adapter = mAdapter

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener { behavior!!.state = BottomSheetBehavior.STATE_EXPANDED }

    }

    override fun onItemClick(item: String?) {

        Snackbar.make(coordinatorLayout!!, "$item is selected", Snackbar.LENGTH_LONG).setAction("Action", null).show()


        behavior!!.state = BottomSheetBehavior.STATE_COLLAPSED

    }
}
