package com.belkanoid.remote.di

import dagger.Component

@Component(
    modules = [RemoteSourceModule::class]
)
interface RemoteSourceComponent : RemoteSourceApi {

    @Component.Factory
    interface Factory {
        fun create(): RemoteSourceComponent
    }
}