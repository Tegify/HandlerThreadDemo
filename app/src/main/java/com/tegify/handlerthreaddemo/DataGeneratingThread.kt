package com.tegify.handlerthreaddemo

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import com.tegify.handlerthreaddemo.utils.DataProvider

/**
 * @author thuannv
 * @since 21/09/2018
 */
class DataGeneratingThread(private val uiHandler: MainActivity.UIHandler) : HandlerThread("DataGeneratingThread") {

    @Volatile
    private var isGenerating = false

    private var handler: Handler? = null

    private val generator = object: Runnable {
        override fun run() {
            val msg = handler?.obtainMessage()
            msg?.obj = DataProvider.random()
            msg?.sendToTarget()
            handler?.postDelayed(this, 100)
        }
    }

    private fun getHandler(looper: Looper): Handler {
        return object: Handler(looper) {
            override fun handleMessage(msg: Message?) {
                val message = uiHandler.obtainMessage()
                message.obj = msg?.obj
                message.sendToTarget()
            }
        }
    }

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        handler = getHandler(this.looper)
    }

    public fun startGenerating() {
        handler?.apply {
            if (!isGenerating) {
                post(generator)
                isGenerating = true
            }
        }
    }

    public fun stopGenerating() {
        handler?.apply {
            removeCallbacks(generator)
            isGenerating = false
        }
    }
}