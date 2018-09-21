package com.tegify.handlerthreaddemo

import android.app.Application
import com.tegify.handlerthreaddemo.utils.DataProvider

/**
 * @author thuannv
 * @since 18/09/2018
 */
class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DataProvider.init(applicationContext)

    }

}