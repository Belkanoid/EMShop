package com.belkanoid.effectivemobileshop.data

import com.belkanoid.core.R
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.product.ProductImage

object ImageLoaderImpl : ImageLoader {
    private val image1 = R.drawable.image1
    private val image2 = R.drawable.image2
    private val image3 = R.drawable.image3
    private val image4 = R.drawable.image4
    private val image5 = R.drawable.image5
    private val image6 = R.drawable.image6

    override fun loadImage(url: String): List<ProductImage> {

        return when(url) {
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(ProductImage(image6), ProductImage(image5))
            "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(ProductImage(image1), ProductImage(image2))
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(ProductImage(image5), ProductImage(image6))
            "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(ProductImage(image3), ProductImage(image4))
            "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(ProductImage(image2), ProductImage(image3))
            "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(ProductImage(image6), ProductImage(image1))
            "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(ProductImage(image4), ProductImage(image3))
            "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(ProductImage(image1), ProductImage(image5))
            else -> {
                listOf()
            }
        }
    }
}