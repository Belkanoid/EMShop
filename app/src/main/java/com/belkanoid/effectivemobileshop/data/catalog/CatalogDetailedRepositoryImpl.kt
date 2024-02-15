package com.belkanoid.effectivemobileshop.data.catalog

import com.belkanoid.catalog.domain.repository.CatalogDetailedRepository
import com.belkanoid.local.LocalDataStore
import javax.inject.Inject

class CatalogDetailedRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore,
) : CatalogDetailedRepository {
    override fun isProductFavorite(id: String): Boolean {
        return localDataStore.isProductFavorite(id)
    }

    override fun setFavorite(id: String, isFavorite: Boolean) {
        if (isFavorite) {
            localDataStore.saveProductId(id)
        } else {
            localDataStore.removeProductId(id)
        }
    }
}