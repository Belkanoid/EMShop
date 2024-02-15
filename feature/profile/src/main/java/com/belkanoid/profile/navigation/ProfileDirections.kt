package com.belkanoid.profile.navigation

import com.belkanoid.product.Product

sealed interface ProfileDirections {

    data object ToFavorite: ProfileDirections

    data class ToProductDetailed(val product: Product): ProfileDirections

    data object Logout: ProfileDirections

}