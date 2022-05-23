package com.mapgis.mapsdk.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import android.os.Build
import androidx.annotation.RequiresApi
import com.sanyao.machin.MachiningApp


import javax.inject.Inject

/**
 *@data 创建时间 2021/7/20
 *@author tang
 *@description 网络检测
 */

class Network {

    companion object {
        val instance: Network by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Network()
        }

    }


    fun isConnected(): Boolean {
        var result = false
        val connectivityManager =
            MachiningApp.CONTEXT
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result

    }
}



