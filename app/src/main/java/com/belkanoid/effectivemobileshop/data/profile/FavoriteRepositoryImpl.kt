package com.belkanoid.effectivemobileshop.data.profile

import com.belkanoid.core.mapper.toProductList
import com.belkanoid.local.LocalDataStore
import com.belkanoid.product.Product
import com.belkanoid.profile.domain.repository.FavoriteRepository
import com.belkanoid.remote.EffectiveMobileShopApi
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore,
    private val serviceApi: EffectiveMobileShopApi
): FavoriteRepository {
    override suspend fun getFavoriteProducts(): List<Product> {
        val productsId = localDataStore.getFavoriteProductsId()
        val products = try {
            serviceApi.getProducts().toProductList()
        }catch (e: Exception) {
            listOf()
        }
        return products.filter { product ->
            productsId.contains(product.id)
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
}