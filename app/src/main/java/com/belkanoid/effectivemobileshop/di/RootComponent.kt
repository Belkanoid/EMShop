package com.belkanoid.effectivemobileshop.di

import android.app.Application
import com.belkanoid.effectivemobileshop.EffectiveMobileApp
import com.belkanoid.navigation_impl.NavigationActivityProvider
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [RootModule::class]
)
interface RootComponent {

    fun inject(effectiveMobileApp: EffectiveMobileApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance navigationActivityProvider: NavigationActivityProvider,
            @BindsInstance app: Application,
        ): RootComponent
    }

}