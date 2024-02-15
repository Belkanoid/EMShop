package com.belkanoid.catalog.navigation

import com.belkanoid.product.Product

sealed interface CatalogDirections {

    data class ToProductDetailed(val product: Product): CatalogDirections

}