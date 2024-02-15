package com.belkanoid.profile.domain.repository

import com.belkanoid.product.Product

interface FavoriteRepository {

    suspend fun getFavoriteProducts(): List<Product>

    fun isProductFavorite(id: String): Boolean

    fun setFavorite(id: String, isFavorite: Boolean)

}