package com.belkanoid.catalog.di.componentHolder

import com.belkanoid.catalog.presentation.catalog.CatalogFragment
import com.belkanoid.catalog.presentation.catalogItemDetailed.CatalogItemDetailedFragment
import com.belkanoid.di.BaseApi

interface CatalogApi: BaseApi {

    fun inject(catalogFragment: CatalogFragment)

    fun inject(catalogItemDetailedFragment: CatalogItemDetailedFragment)
}