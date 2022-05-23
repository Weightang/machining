package com.sanyao.machin.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import androidx.annotation.ChecksSdkIntAtLeast

import com.sanyao.machin.BuildConfig
import com.sanyao.machin.SystemServices
import timber.log.Timber

import java.text.SimpleDateFormat
import java.util.*


/**
*@data 创建时间 2021/7/23
*@author tang
*@description
*/
object AppUtils {


    fun setTitle(context: Context): String {
        val sb = StringBuilder("infocomApp")
        val date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(Date())

        when {
            date.endsWith("1225") -> {
                sb.append("\uD83C\uDF84")
            }
            date == "20210211" -> {
                sb.append("\uD83C\uDFEE")
            }
            date == "20210212" -> {
                sb.append("\uD83D\uDC2E")
            }
        }
        return sb.toString()
    }

    fun getAppIcon(packageName: String): Drawable {
        return try {
            val pi = SystemServices.packageManager.getPackageInfo(packageName, 0)
            pi?.applicationInfo?.loadIcon(SystemServices.packageManager)!!
        } catch (e: Exception) {
            ColorDrawable(Color.TRANSPARENT)
        }
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun atLeastR(): Boolean {
        return Build.VERSION.SDK_INT >= 30
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun atLeastQ(): Boolean {
        return Build.VERSION.SDK_INT >= 29
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    fun atLeastP(): Boolean {
        return Build.VERSION.SDK_INT >= 28
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    fun atLeastO(): Boolean {
        return Build.VERSION.SDK_INT >= 26
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun atLeastNMR1(): Boolean {
        return Build.VERSION.SDK_INT >= 25
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
    fun atLeastN(): Boolean {
        return Build.VERSION.SDK_INT >= 24
    }


    /**
     * From drakeet
     */
    fun doOnMainThreadIdle(action: () -> Unit, timeout: Long? = null) {
        val handler = Handler(Looper.getMainLooper())

        val idleHandler = MessageQueue.IdleHandler {
            handler.removeCallbacksAndMessages(null)
            action()
            return@IdleHandler false
        }

        fun setupIdleHandler(queue: MessageQueue) {
            if (timeout != null) {
                handler.postDelayed({
                    queue.removeIdleHandler(idleHandler)
                    action()
                    if (BuildConfig.DEBUG) {
                        Timber.d("doOnMainThreadIdle: ${timeout}ms timeout!")
                    }
                }, timeout)
            }
            queue.addIdleHandler(idleHandler)
        }

        if (Looper.getMainLooper() == Looper.myLooper()) {
            setupIdleHandler(Looper.myQueue())
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setupIdleHandler(Looper.getMainLooper().queue)
            } else {
                handler.post { setupIdleHandler(Looper.myQueue()) }
            }
        }
    }
}