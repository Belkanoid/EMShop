package com.belkanoid.catalog.di

import com.belkanoid.catalog.di.componentHolder.CatalogApi
import com.belkanoid.catalog.di.componentHolder.CatalogDependencies
import dagger.Component

@Component(
    modules = [ViewModelModule::class],
    dependencies = [CatalogDependencies::class]
)
interface CatalogComponent : CatalogApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: CatalogDependencies
        ) : CatalogComponent
    }

}