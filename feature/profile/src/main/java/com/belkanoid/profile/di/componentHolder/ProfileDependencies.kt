package com.belkanoid.profile.di.componentHolder

import com.belkanoid.core.util.ImageLoader
import com.belkanoid.di.BaseDependencies
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.profile.domain.repository.FavoriteRepository
import com.belkanoid.profile.domain.repository.ProfileRepository
import com.belkanoid.profile.navigation.ProfileDirections

interface ProfileDependencies: BaseDependencies {

    val navigationApi: NavigationApi<ProfileDirections>

    val profileRepository: ProfileRepository

    val favoriteRepository: FavoriteRepository


    val imageLoader: ImageLoader

}