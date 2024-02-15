package com.belkanoid.navigation_impl.directionsImpl

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.belkanoid.di.annotations.SetLoginAction
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.navigation_impl.R
import com.belkanoid.profile.navigation.ProfileDirections
import com.belkanoid.profile.presentation.profile.ProfileFragmentDirections
import javax.inject.Inject
import javax.inject.Provider

class ProfileNavigationImpl @Inject constructor(
    private val navController: Provider<NavController>,
    @SetLoginAction private val openLoginFragment: () -> Unit
) : NavigationApi<ProfileDirections> {

    override fun navigate(direction: ProfileDirections) = when (direction) {

        ProfileDirections.ToFavorite -> {
            navController.get().navigate(
                ProfileFragmentDirections.actionProfileFragmentToFavoriteFragment()
            )
        }

        ProfileDirections.Logout -> {
            openLoginFragment()
        }

        is ProfileDirections.ToProductDetailed -> {
            //у меня почему то не сгенерировался экшн метод для перехода на этот экран :(
            navController.get().navigate(
                R.id.catalogItemDetailedFragment2, bundleOf("product" to direction.product)
            )
        }
    }
}