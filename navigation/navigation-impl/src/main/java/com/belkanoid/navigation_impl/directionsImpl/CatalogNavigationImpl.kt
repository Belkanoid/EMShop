package com.belkanoid.navigation_impl.directionsImpl

import androidx.navigation.NavController
import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.catalog.presentation.catalog.CatalogFragmentDirections
import com.belkanoid.navigation_api.NavigationApi
import javax.inject.Inject
import javax.inject.Provider

class CatalogNavigationImpl @Inject constructor(
    private val navController: Provider<NavController>,
): NavigationApi<CatalogDirections> {

    override fun navigate(direction: CatalogDirections) = when(direction) {
        is CatalogDirections.ToProductDetailed -> {
            navController.get().navigate(
                CatalogFragmentDirections.actionCatalogFragmentToCatalogItemDetailedFragment(
                    product = direction.product
                )
            )
        }
    }
}