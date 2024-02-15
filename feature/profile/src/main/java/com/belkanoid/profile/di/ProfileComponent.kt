package com.belkanoid.profile.di

import com.belkanoid.profile.di.componentHolder.ProfileApi
import com.belkanoid.profile.di.componentHolder.ProfileDependencies
import dagger.Component

@Component(
    modules = [ViewModelModule::class],
    dependencies = [ProfileDependencies::class]
)
interface ProfileComponent: ProfileApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: ProfileDependencies
        ): ProfileComponent
    }
}