package com.tegify.handlerthreaddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private lateinit var profileAdapter: ProfileAdapter


    private lateinit var uiHander: UIHandler

    private lateinit var dataGeneratingThread: DataGeneratingThread

    private fun setupRecyclerView() {
        profileAdapter = ProfileAdapter()
        profileList.adapter = profileAdapter
        profileList.layoutManager = LinearLayoutManager(this)
    }

    private fun bindButtonsClickEvent() {
        buttonStart.setOnClickListener { dataGeneratingThread.startGenerating() }
        buttonStop.setOnClickListener { dataGeneratingThread.stopGenerating() }
    }

    private fun initBackgroundThread() {
        uiHander = UIHandler()
        uiHander.attach(this)

        dataGeneratingThread = DataGeneratingThread(uiHander)
        dataGeneratingThread.start()
    }

    private fun stopBackgroundThread() {
        dataGeneratingThread.stopGenerating()
        dataGeneratingThread.quit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        bindButtonsClickEvent()
    }

    override fun onStart() {
        super.onStart()
        initBackgroundThread()
    }

    override fun onStop() {
        stopBackgroundThread()
        super.onStop()
    }


    fun onNewProfile(profile: Profile?) {
        profileAdapter.add(profile)
        if (profileAdapter.itemCount > 0) {
            profileList.smoothScrollToPosition(profileAdapter.lastPosition())
        }
    }

    /**
     * UIHandler
     */
    class UIHandler : Handler() {

        private var activityRef: WeakReference<MainActivity>? = null

        fun attach(activity: MainActivity) {
            activityRef = WeakReference(activity)
        }

        override fun handleMessage(msg: Message?) {
            val profile = msg?.obj as Profile
            activityRef?.get()?.onNewProfile(profile)
        }
    }
}
