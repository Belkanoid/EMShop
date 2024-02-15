package com.belkanoid.effectivemobileshop

import android.app.Application
import com.belkanoid.effectivemobileshop.di.ComponentHolderInitializer
import com.belkanoid.effectivemobileshop.di.DaggerRootComponent
import com.belkanoid.navigation_impl.NavigationActivityProvider
import javax.inject.Inject

class EffectiveMobileApp : Application() {
    @Inject lateinit var componentHolderInitializer: ComponentHolderInitializer

    private val component by lazy {
        DaggerRootComponent.factory().create(
            NavigationActivityProvider(this),
            this
        )
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        componentHolderInitializer.init()
    }


}