package com.belkanoid.profile.di

import androidx.lifecycle.ViewModel
import com.belkanoid.core.di.ViewModelKey
import com.belkanoid.profile.presentation.favorite.viewModel.FavoriteViewModel
import com.belkanoid.profile.presentation.profile.viewModel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @[Binds IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindProfileViewModel(impl: ProfileViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(FavoriteViewModel::class)]
    fun bindFavoriteViewModel(impl: FavoriteViewModel): ViewModel

}