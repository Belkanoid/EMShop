package com.belkanoid.profile.presentation.profile.viewModel

import androidx.lifecycle.ViewModel
import com.belkanoid.core.R
import com.belkanoid.core.util.TextEndingFormatter
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.profile.domain.entity.Profile
import com.belkanoid.profile.domain.repository.ProfileRepository
import com.belkanoid.profile.navigation.ProfileDirections
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private var navigationApi: NavigationApi<ProfileDirections>
): ViewModel() {

    private val formatter = TextEndingFormatter

    fun profileItemList(): List<Profile> {
        val userInfo = repository.getUserInfo()
        val favoriteItem = if(userInfo.favoriteProducts == 0) {
            Profile.Default("Избранное", R.drawable.heart_default) {}
        }else {
            Profile.Subtitled(
                "Избранное",
                formatter.productEndingFormatter(userInfo.favoriteProducts),
                R.drawable.heart_default
            ) {
                navigationApi.navigate(ProfileDirections.ToFavorite)
            }
        }
        return listOf(
            Profile.Subtitled("${userInfo.name} ${userInfo.surname}", userInfo.phone, R.drawable.account_default, R.drawable.logout_default) {} ,
            favoriteItem,
            Profile.Default("Магазины", R.drawable.shop_default) {},
            Profile.Default("Обратная связь", R.drawable.feedback_default) {},
            Profile.Default("Оферта", R.drawable.offer_default) {},
            Profile.Default("Возврат товара", R.drawable.refund_default) {},
        )
    }

    fun logout() {
        repository.clearStorage()
        navigationApi.navigate(ProfileDirections.Logout)
    }
}