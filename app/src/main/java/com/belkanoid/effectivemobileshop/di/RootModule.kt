package com.belkanoid.effectivemobileshop.di

import com.belkanoid.catalog.di.componentHolder.CatalogDependencies
import com.belkanoid.catalog.domain.repository.CatalogDetailedRepository
import com.belkanoid.catalog.domain.repository.CatalogRepository
import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.effectivemobileshop.data.ImageLoaderImpl
import com.belkanoid.effectivemobileshop.data.catalog.CatalogDetailedRepositoryImpl
import com.belkanoid.effectivemobileshop.data.catalog.CatalogRepositoryImpl
import com.belkanoid.effectivemobileshop.data.login.LoginRepositoryImpl
import com.belkanoid.effectivemobileshop.data.navigation.NavigationUseCaseImpl
import com.belkanoid.effectivemobileshop.data.profile.FavoriteRepositoryImpl
import com.belkanoid.effectivemobileshop.data.profile.ProfileRepositoryImpl
import com.belkanoid.log_in.di.LoginDependencies
import com.belkanoid.log_in.domain.LoginRepository
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.navigation_impl.NavigationActivityProvider
import com.belkanoid.navigation_impl.di.componentHolder.NavigationComponentHolder
import com.belkanoid.navigation_impl.di.componentHolder.NavigationDependencies
import com.belkanoid.navigation_impl.domain.NavigationUseCase
import com.belkanoid.profile.di.componentHolder.ProfileDependencies
import com.belkanoid.profile.domain.repository.FavoriteRepository
import com.belkanoid.profile.domain.repository.ProfileRepository
import com.belkanoid.profile.navigation.ProfileDirections
import com.belkanoid.remote.EffectiveMobileShopApi
import com.belkanoid.remote.di.RemoteSourceComponentHolder
import dagger.Module
import dagger.Provides

@Module
class RootModule {

    @Provides
    fun provideNavigationDependencies(
        activityProvider: NavigationActivityProvider,
        impl: NavigationUseCaseImpl,
    ): NavigationDependencies = object : NavigationDependencies {

        override val navigationActivityProvider =
            activityProvider
        override val navigationUseCase: NavigationUseCase =
            impl

    }

    @Provides
    fun provideProfileDependencies(
        profileImpl: ProfileRepositoryImpl,
        favoriteImpl: FavoriteRepositoryImpl
    ): ProfileDependencies = object : ProfileDependencies {

        override val navigationApi: NavigationApi<ProfileDirections> =
            NavigationComponentHolder.get().profileNavigationApi
        override val profileRepository: ProfileRepository =
            profileImpl
        override val favoriteRepository: FavoriteRepository =
            favoriteImpl
        override val imageLoader: ImageLoader =
            ImageLoaderImpl
    }

    @Provides
    fun provideRemoteDataSource(): EffectiveMobileShopApi = RemoteSourceComponentHolder.get().effectiveMobileShopApi


    @Provides
    fun provideCatalogDependencies(
        catalogImpl: CatalogRepositoryImpl,
        detailedImpl: CatalogDetailedRepositoryImpl,
    ) : CatalogDependencies = object : CatalogDependencies {

        override val catalogRepository: CatalogRepository =
            catalogImpl
        override val catalogDetailedRepository: CatalogDetailedRepository =
            detailedImpl
        override val navigationApi: NavigationApi<CatalogDirections> =
            NavigationComponentHolder.get().catalogNavigationApi
        override val imageLoader: ImageLoader =
            ImageLoaderImpl

    }

    @Provides
    fun provideLoginDependencies(impl: LoginRepositoryImpl) : LoginDependencies = object : LoginDependencies {

        override val loginRepository: LoginRepository =
            impl
        override val navigationApi: NavigationApi<LoginDirections> =
            NavigationComponentHolder.get().loginNavigationApi

    }

}