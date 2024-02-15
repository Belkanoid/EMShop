package com.belkanoid.catalog.presentation.catalogItemDetailed.viewModel

import androidx.lifecycle.ViewModel
import com.belkanoid.catalog.domain.repository.CatalogDetailedRepository
import com.belkanoid.catalog.presentation.catalogItemDetailed.adapter.CatalogDetailedAdapter
import com.belkanoid.core.util.ImageLoader
import javax.inject.Inject

class CatalogDetailedViewModel @Inject constructor(
    private val repository: CatalogDetailedRepository,
    private val imageLoader: ImageLoader
): ViewModel() {

    val catalogDetailedAdapter = CatalogDetailedAdapter(
        imageLoader = imageLoader,
        isProductFavorite = repository::isProductFavorite,
        onHeartIconClick = repository::setFavorite
    )

}