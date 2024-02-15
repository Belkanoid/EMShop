package com.belkanoid.catalog.presentation.catalog.state

import com.belkanoid.catalog.domain.entity.Tag
import com.belkanoid.product.Product


sealed class CatalogScreenState {

    data object Empty: CatalogScreenState()

    data object Loading: CatalogScreenState()

    data class Success(val products: List<Product>, val tags: List<Tag>): CatalogScreenState()

}