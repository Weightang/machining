package com.mapgis.baselib.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

object LifecycleUtil {

    @SuppressLint("StaticFieldLeak")
    private var sApplication: Application? = null

    private val sActivityList = LinkedList<Activity>()

    private val mCallbacks: ActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            setTopActivity(activity)
        }

        override fun onActivityStarted(activity: Activity) {
          setTopActivity(activity)
        }

        override fun onActivityResumed(activity: Activity) {
            setTopActivity(activity)
        }

        override fun onActivityPaused(activity: Activity) {}
        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
        override fun onActivityDestroyed(activity: Activity) {
            sActivityList.remove(activity)
        }
    }

    var callbacks: FragmentManager.FragmentLifecycleCallbacks =
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentCreated(
                fm: FragmentManager,
                f: Fragment,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentCreated(fm, f, savedInstanceState)
            }

            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            }

            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
            }

            override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                super.onFragmentPaused(fm, f)
            }

            override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
                super.onFragmentStopped(fm, f)
            }

            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentViewDestroyed(fm, f)
            }

            override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentDestroyed(fm, f)
            }
        }


    private fun Utils() {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    /**
     * Init utils.
     *
     * Init it in the class of Application.
     *
     * @param context context
     */
    fun init(application: Application) {
        sApplication =application
        sApplication?.registerActivityLifecycleCallbacks(mCallbacks)
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    fun getApp(): Application? {
        if (sApplication != null) {
            return sApplication
        }
        throw NullPointerException("u should init first")
    }


    fun setTopActivity(activity: Activity) {
//        try {
//            if (activity.getClass() == Class.forName("")) {
//                return;
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        if (sActivityList.contains(activity)) {
            if (sActivityList.getLast() != activity) {
                sActivityList.remove(activity)
                sActivityList.addLast(activity)
            }
        } else {
            sActivityList.addLast(activity)
        }
    }

    fun getActivityList(): LinkedList<Activity> {
        return sActivityList
    }

    fun unActivityLifecycleCallbacks() {
        sApplication?.unregisterActivityLifecycleCallbacks(mCallbacks)
    }


}