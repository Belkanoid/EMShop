package com.belkanoid.profile.di.componentHolder

import com.belkanoid.di.BaseComponentHolder
import com.belkanoid.profile.di.DaggerProfileComponent

object ProfileComponentHolder: BaseComponentHolder<ProfileApi, ProfileDependencies>() {
    override fun build(dependencies: ProfileDependencies): ProfileApi =
        DaggerProfileComponent.factory().create(dependencies)
}