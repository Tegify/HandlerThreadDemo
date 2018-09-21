package com.tegify.handlerthreaddemo

import com.google.gson.annotations.SerializedName

/**
 * @author thuannv
 * @since 18/09/2018
 */
data class Profile(
        @SerializedName("userId") val id: Int,
        @SerializedName("displayName") val displayName: String,
        @SerializedName("avatar") val avatar: String?)