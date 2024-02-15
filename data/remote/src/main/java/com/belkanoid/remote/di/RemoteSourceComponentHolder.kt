package com.belkanoid.remote.di

import com.belkanoid.di.BaseComponentHolder
import com.belkanoid.di.BaseDependencies

object RemoteSourceComponentHolder : BaseComponentHolder<RemoteSourceApi, BaseDependencies>() {

    override fun build(dependencies: BaseDependencies): RemoteSourceApi =
        DaggerRemoteSourceComponent.factory().create()
}