package com.belkanoid.product

sealed interface ProductComposition {

    data class Header(
        val id: String,
        val tittle: String,
        val subtitle: String,
        val price: Price,
        val feedback: Feedback,
        val available: Int,
    ) : ProductComposition

    data class Description(
        val brand: String,
        val text: String,
    ) : ProductComposition

    data class Attribute(
        val value: List<Info>
    ) : ProductComposition

    data class Ingredient(
        val value: String,
    ) : ProductComposition

}