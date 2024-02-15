package com.belkanoid.profile.di.componentHolder

import com.belkanoid.di.BaseApi
import com.belkanoid.profile.presentation.favorite.FavoriteFragment
import com.belkanoid.profile.presentation.profile.ProfileFragment

interface ProfileApi: BaseApi {

    fun inject(profileFragment: ProfileFragment)

    fun inject(favoriteFragment: FavoriteFragment)
}