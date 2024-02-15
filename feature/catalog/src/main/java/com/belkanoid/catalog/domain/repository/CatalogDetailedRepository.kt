package com.belkanoid.catalog.domain.repository

interface CatalogDetailedRepository {

    fun isProductFavorite(id: String): Boolean

    fun setFavorite(id: String, isFavorite: Boolean)

}