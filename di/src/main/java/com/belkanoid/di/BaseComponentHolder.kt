package com.belkanoid.di

import javax.inject.Provider

abstract class BaseComponentHolder<COMPONENT: BaseApi, DEPENDENCIES: BaseDependencies> {

    private var isDependenciesProvided = false
    private var component: COMPONENT? = null


    private lateinit var dependenciesProvider: Provider<DEPENDENCIES>

    protected abstract fun build(dependencies: DEPENDENCIES): COMPONENT

    fun get(): COMPONENT = component ?: if (isDependenciesProvided) {
        build(dependenciesProvider.get())
    } else {
        build(object : BaseDependencies{} as DEPENDENCIES)
    }.also { newComponent -> component = newComponent}


    fun init(dependencies: Provider<DEPENDENCIES>) {
        if (::dependenciesProvider.isInitialized) {
            throw Error("Component holder must be initialized only once")
        }
        dependenciesProvider = dependencies
        isDependenciesProvided = true
    }


    fun clear() {
        component = null
    }

}