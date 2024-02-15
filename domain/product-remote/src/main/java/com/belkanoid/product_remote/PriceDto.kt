package com.belkanoid.product_remote

data class PriceDto(
    val discount: Int,
    val price: String,
    val priceWithDiscount: String,
    val unit: String
)