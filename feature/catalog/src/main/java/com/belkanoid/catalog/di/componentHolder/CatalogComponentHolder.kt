package com.belkanoid.catalog.di.componentHolder

import com.belkanoid.catalog.di.DaggerCatalogComponent
import com.belkanoid.di.BaseComponentHolder

object CatalogComponentHolder : BaseComponentHolder<CatalogApi, CatalogDependencies>() {
    override fun build(dependencies: CatalogDependencies): CatalogApi =
        DaggerCatalogComponent.factory().create(dependencies)
}