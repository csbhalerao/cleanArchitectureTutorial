package com.chetan.coroutinetutorial.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun hasNetwork(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        // for other device how are able to connect with Ethernet
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        // for check internet over Bluetooth
        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}
