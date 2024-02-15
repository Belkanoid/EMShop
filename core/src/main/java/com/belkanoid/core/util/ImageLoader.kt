package com.belkanoid.core.util

import com.belkanoid.product.ProductImage


interface ImageLoader {

    fun loadImage(url: String): List<ProductImage>

}