package com.belkanoid.product.extensions

import com.belkanoid.product.Product
import com.belkanoid.product.ProductComposition

fun Product.toHeader() = ProductComposition.Header(
    id = this.id,
    tittle = this.title,
    subtitle = this.subtitle,
    price = this.price,
    feedback = this.feedback,
    available = this.available,
)

fun Product.toDescription() = ProductComposition.Description(
    brand = this.title,
    text = this.description,
)

fun Product.toAttribute() = ProductComposition.Attribute(
    value = this.info
)

fun Product.toIngredient() = ProductComposition.Ingredient(
    value = this.ingredients
)

fun Product.toComposition() = listOf(this.toHeader(), this.toDescription(), this.toAttribute(), this.toIngredient())