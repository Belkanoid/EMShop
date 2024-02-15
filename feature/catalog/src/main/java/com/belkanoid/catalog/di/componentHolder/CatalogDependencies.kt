package com.belkanoid.catalog.di.componentHolder

import com.belkanoid.catalog.domain.repository.CatalogDetailedRepository
import com.belkanoid.catalog.domain.repository.CatalogRepository
import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.di.BaseDependencies
import com.belkanoid.navigation_api.NavigationApi

interface CatalogDependencies : BaseDependencies{

    val catalogRepository: CatalogRepository

    val catalogDetailedRepository: CatalogDetailedRepository

    val navigationApi: NavigationApi<CatalogDirections>

    val imageLoader: ImageLoader

}