package com.belkanoid.navigation_impl

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference
import javax.inject.Inject

class NavigationActivityProvider @Inject constructor(
    application: Application
) {

    private var activityRef: WeakReference<NavigationActivity>? = null

    fun get(): NavigationActivity? = activityRef?.get()

    init {
        registerNavigationActivity(application)
    }

    private fun registerNavigationActivity(application: Application) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is NavigationActivity) {
                    activityRef = WeakReference(activity)
                }
            }
            override fun onActivityDestroyed(activity: Activity) {
                if (activity is NavigationActivity) {
                    activityRef = null
                }
            }

            override fun onActivityStarted(activity: Activity) {
                //nothing to do here
            }

            override fun onActivityResumed(activity: Activity) {
                //nothing to do here
            }

            override fun onActivityPaused(activity: Activity) {
                //nothing to do here
            }

            override fun onActivityStopped(activity: Activity) {
                //nothing to do here
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                //nothing to do here
            }

        })
    }

}