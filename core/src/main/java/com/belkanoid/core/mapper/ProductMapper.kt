package com.belkanoid.core.mapper

import com.belkanoid.product.Feedback
import com.belkanoid.product.Info
import com.belkanoid.product.Price
import com.belkanoid.product.Product
import com.belkanoid.product_remote.FeedbackDto
import com.belkanoid.product_remote.InfoDto
import com.belkanoid.product_remote.PriceDto
import com.belkanoid.product_remote.ProductResponse


fun ProductResponse.toProductList(): List<Product> {
    val productList = mutableListOf<Product>()
    val products = this.products
    products.forEach { itemDto ->
        val product = Product(
            available = itemDto.available,
            description = itemDto.description,
            feedback = itemDto.feedback.toFeedback(),
            id = itemDto.id,
            info = itemDto.info.toInfo(),
            ingredients = itemDto.ingredients,
            price = itemDto.price.toPrice(),
            subtitle = itemDto.subtitle,
            tags = itemDto.tags,
            title = itemDto.title
        )
        productList.add(product)
    }

    return productList
}

private fun FeedbackDto.toFeedback(): Feedback = Feedback(
    count = this.count,
    rating = this.rating
)

private fun List<InfoDto>.toInfo() = map { infoDto ->
    Info(
        title = infoDto.title,
        value = infoDto.value
    )
}

private fun PriceDto.toPrice() = Price(
    discount = this.discount,
    price = this.price,
    priceWithDiscount = this.priceWithDiscount,
    unit = this.unit
)