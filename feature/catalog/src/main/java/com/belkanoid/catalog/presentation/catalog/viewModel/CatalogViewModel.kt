package com.belkanoid.catalog.presentation.catalog.viewModel

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belkanoid.catalog.R
import com.belkanoid.catalog.domain.entity.SortType
import com.belkanoid.catalog.domain.entity.Tag
import com.belkanoid.catalog.domain.repository.CatalogRepository
import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.catalog.presentation.catalog.adapter.TagAdapter
import com.belkanoid.catalog.presentation.catalog.state.CatalogScreenState
import com.belkanoid.core.ui.adapter.ProductAdapter
import com.belkanoid.core.ui.adapter.ProductImageAdapter
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.navigation_api.NavigationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class CatalogViewModel @Inject constructor(
    private val productImageAdapterProvider: Provider<ProductImageAdapter>,
    private val imageLoader: ImageLoader,
    private val repository: CatalogRepository,
    private val navigationApi: NavigationApi<CatalogDirections>
) : ViewModel() {

    val productAdapter = ProductAdapter(
        productImageAdapter = productImageAdapterProvider,
        imageLoader = imageLoader,
        onDefaultAdapterClick = {navigationApi.navigate(CatalogDirections.ToProductDetailed(it))},
        isProductFavorite = repository::isProductFavorite,
        onHeartIconClick = repository::setFavorite
    )
    val tagAdapter = TagAdapter(
        onDefaultTagClick = ::setProductsTag,
        onChosenTagClick = ::setProductsTag
    )

    fun spinnerAdapter(context: Context) = ArrayAdapter.createFromResource(
        context,
        R.array.filter_arrays,
        R.layout.spinner_item_main,
    ).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    val onSpinnerItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
           when(position) {
               1 -> setProductsSort(SortType.DecreasePrice)
               2 -> setProductsSort(SortType.IncreasePrice)
               else -> setProductsSort(SortType.Fame)
           }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) { /*nothing to do here*/ }

    }

    private val tags = repository.tags
    val state = MutableStateFlow<CatalogScreenState>(CatalogScreenState.Empty)

    fun setProductsSort(sortType: SortType) {
        getProducts(tags, sortType)
    }

    private fun setProductsTag(newTag: Tag) {
        tags.replaceAll { tag ->
            if (tag.value == newTag.value) {
                if (tag is Tag.Default) Tag.Chosen(tag.value) else Tag.Default(tag.value)
            } else {
                tag
            }
        }
        getProducts(tags)
    }

    private fun getProducts(tags: List<Tag>, sortType: SortType = SortType.Fame) {
        viewModelScope.launch {
            state.value = CatalogScreenState.Loading
            val newProducts = repository.getAllProducts(sortType, tags)
            state.value = CatalogScreenState.Success(newProducts, tags.sortedBy { it is Tag.Default })
        }
    }


}