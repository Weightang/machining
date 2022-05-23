package com.sanyao.machin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.sanyao.machin.utils.timber.ReleaseTree
import com.sanyao.machin.utils.timber.ThreadAwareDebugTree
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MachiningApp: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context

    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT=this
        MMKV.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(ThreadAwareDebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

}