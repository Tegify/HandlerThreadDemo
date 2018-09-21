package com.tegify.handlerthreaddemo.utils

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tegify.handlerthreaddemo.Profile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder


/**
 * @author thuannv
 * @since 18/09/2018
 */

fun readAssetFile(assets: AssetManager, filename: String): String {
    val sb = StringBuilder()
    BufferedReader(InputStreamReader(assets.open(filename))).useLines { lines -> lines.forEach { sb.append(it) } }
    return sb.toString()
}

inline fun<reified T> Gson.parseJSON(json: String): T? = this.fromJson<T>(json, object: TypeToken<T>(){}.type )

fun transformJsonInToProfileList(json: String?): List<Profile> {
    return try { Gson().parseJSON<List<Profile>>(json!!) ?: listOf() } catch (e: Exception) { listOf() }
}