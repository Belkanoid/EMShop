package com.belkanoid.product_remote

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("items")
    val products: List<ItemDto>
)