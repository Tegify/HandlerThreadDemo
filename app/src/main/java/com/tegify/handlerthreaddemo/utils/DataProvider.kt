@file:Suppress("UNNECESSARY_NOT_NULL_ASSERTION")

package com.tegify.handlerthreaddemo.utils

import android.content.Context
import com.tegify.handlerthreaddemo.Profile
import java.util.*

/**
 * @author thuannv
 * @since 18/09/2018
 */

object DataProvider {

    @Volatile private var isInitialized = false

    private lateinit var profiles: List<Profile>

    private val random = Random()

    fun init(context: Context) {
        if (isInitialized) { return }

        isInitialized = true
        Thread(Runnable { syncInit(context) }).start()
    }


    private fun syncInit(context: Context) {
        val jsonData = readAssetFile(context.assets, "profiles.json")
        profiles = transformJsonInToProfileList(jsonData)
        /* dump() */
    }

    /*
    private fun dump() {
        if (isInitialized) {
            System.out.println("\tprofiles:")
            profiles.forEach { System.out.println("\n\t\t" + it) }
        }
    }
    */

    fun random(): Profile {
        return profiles[random.nextInt(profiles.size)]
    }
}