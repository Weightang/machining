package com.mapgis.mapsdk.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import android.os.Build
import androidx.annotation.RequiresApi

import dev.DevUtils
import javax.inject.Inject

/**
*@data 创建时间 2021/7/20
*@author tang
*@description 网络检测
*/

class Network {

    companion object{
        val instance: Network by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Network() }

    }




     fun isConnected(): Boolean {
         var result = false
         val connectivityManager =
             DevUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             val networkCapabilities = connectivityManager.activeNetwork ?: return false
             val actNw =
                 connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
             result = when {
                 actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                 actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                 actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                 else -> false
             }
         } else {
             connectivityManager.run {
                 connectivityManager.activeNetworkInfo?.run {
                     result = when (type) {
                         ConnectivityManager.TYPE_WIFI -> true
                         ConnectivityManager.TYPE_MOBILE -> true
                         ConnectivityManager.TYPE_ETHERNET -> true
                         else -> false
                     }

                 }
             }
         }

         return result

    }
}



