package com.mobile.pvnews.utils

import android.content.Context
import android.net.ConnectivityManager
import com.mobile.pvnews.utils.NetworkHelper

class NetworkHelperImpl constructor(private val context: Context) : NetworkHelper {

    override fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }
}