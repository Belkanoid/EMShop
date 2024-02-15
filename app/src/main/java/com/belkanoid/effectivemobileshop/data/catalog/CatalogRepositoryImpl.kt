package com.belkanoid.effectivemobileshop.data.catalog

import com.belkanoid.catalog.domain.entity.SortType
import com.belkanoid.catalog.domain.entity.Tag
import com.belkanoid.catalog.domain.repository.CatalogRepository
import com.belkanoid.core.extension.containsAtLeastOne
import com.belkanoid.core.mapper.toProductList
import com.belkanoid.local.LocalDataStore
import com.belkanoid.product.Product
import com.belkanoid.remote.EffectiveMobileShopApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val serviceApi: EffectiveMobileShopApi,
    private val localDataStore: LocalDataStore,
): CatalogRepository {
    override suspend fun getAllProducts(sortType: SortType, tags: List<Tag>): List<Product>  = withContext(Dispatchers.IO) {
        val products = try {
             serviceApi.getProducts().toProductList()
        }catch (e: Exception) {
            listOf()
        }

        val sortedProducts = products.getSortedProducts(sortType)
        val productsByTag = sortedProducts.getProductsByTag(tags)

        if (tags.isEmpty() || productsByTag.isEmpty()){
             sortedProducts
        }else {
            productsByTag
        }
    }

    override fun isProductFavorite(id: String): Boolean {
        return localDataStore.isProductFavorite(id)
    }

    override fun setFavorite(id: String, isFavorite: Boolean) {
        if(isFavorite) {
            localDataStore.saveProductId(id)
        }else {
            localDataStore.removeProductId(id)
        }
    }

    private fun List<Product>.getSortedProducts(sortType: SortType) = when(sortType) {
        SortType.Fame -> {
            sortedByDescending { it.feedback.rating }
        }
        SortType.DecreasePrice -> {
            sortedByDescending { it.price.priceWithDiscount.toInt() }
        }
        SortType.IncreasePrice -> {
            sortedBy { it.price.priceWithDiscount.toInt() }

        }
    }

    private fun List<Product>.getProductsByTag(tags: List<Tag>): List<Product> {
        val requestTags = tags.filterIsInstance<Tag.Chosen>().map { it.value.requestName }
        return filter { product ->
            requestTags.containsAtLeastOne(product.tags)
        }
    }

}