package com.belkanoid.catalog.domain.repository

import com.belkanoid.catalog.domain.entity.SortType
import com.belkanoid.catalog.domain.entity.Tag
import com.belkanoid.catalog.domain.entity.TagName
import com.belkanoid.product.Product

interface CatalogRepository {

    val tags: MutableList<Tag>
        get() = mutableListOf(
            Tag.Chosen(TagName("Смотреть все", "all")),
            Tag.Default(TagName("Лицо", "face")),
            Tag.Default(TagName("Тело", "body")),
            Tag.Default(TagName("Загар", "suntan")),
            Tag.Default(TagName("Маски", "mask"))
        )

    suspend fun getAllProducts(sortType: SortType, tags: List<Tag>): List<Product>

    fun isProductFavorite(id: String): Boolean

    fun setFavorite(id: String, isFavorite: Boolean)

}