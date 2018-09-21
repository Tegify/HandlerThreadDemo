package com.tegify.handlerthreaddemo

/**
 * @author thuannv
 * @since 19/09/2018
 */
interface DataListener<in T> {
    fun onData(data: T?)
}