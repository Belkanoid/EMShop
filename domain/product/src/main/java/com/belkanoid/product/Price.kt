package com.belkanoid.product

import java.io.Serializable

data class Price(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
) : Serializable