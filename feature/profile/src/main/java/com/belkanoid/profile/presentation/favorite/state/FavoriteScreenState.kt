package com.belkanoid.profile.presentation.favorite.state

import com.belkanoid.product.Product

sealed class FavoriteScreenState {

    data object Empty: FavoriteScreenState()

    data object Loading: FavoriteScreenState()

    data class Success(val products: List<Product>): FavoriteScreenState()

}