package com.belkanoid.profile.presentation.favorite.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belkanoid.core.ui.adapter.ProductAdapter
import com.belkanoid.core.ui.adapter.ProductImageAdapter
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.profile.domain.repository.FavoriteRepository
import com.belkanoid.profile.navigation.ProfileDirections
import com.belkanoid.profile.presentation.favorite.state.FavoriteScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
    private val productImageAdapterProvider: Provider<ProductImageAdapter>,
    private val imageLoader: ImageLoader,
    private val navigationApi: NavigationApi<ProfileDirections>
): ViewModel() {

    private val _state = MutableStateFlow<FavoriteScreenState>(FavoriteScreenState.Empty)
    val state = _state.asStateFlow()

    init {
        getFavoriteProducts()
    }

    val productAdapter = ProductAdapter(
        productImageAdapter = productImageAdapterProvider,
        imageLoader = imageLoader,
        onDefaultAdapterClick = {navigationApi.navigate(ProfileDirections.ToProductDetailed(it))},
        isProductFavorite = repository::isProductFavorite,
        onHeartIconClick = repository::setFavorite
    )

    fun getFavoriteProducts() {
        _state.value = FavoriteScreenState.Loading

        viewModelScope.launch {
            _state.value = FavoriteScreenState.Success(repository.getFavoriteProducts())
        }
    }

    fun getFavoriteBrands() {
        _state.value = FavoriteScreenState.Loading

        viewModelScope.launch {
            _state.value = FavoriteScreenState.Success(listOf())
        }
    }

}