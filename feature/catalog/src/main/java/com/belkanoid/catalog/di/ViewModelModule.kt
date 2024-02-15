package com.belkanoid.catalog.di

import androidx.lifecycle.ViewModel
import com.belkanoid.catalog.presentation.catalog.viewModel.CatalogViewModel
import com.belkanoid.catalog.presentation.catalogItemDetailed.viewModel.CatalogDetailedViewModel
import com.belkanoid.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @[Binds IntoMap ViewModelKey(CatalogViewModel::class)]
    fun bindCatalogViewModel(impl: CatalogViewModel): ViewModel

    @[Binds IntoMap ViewModelKey(CatalogDetailedViewModel::class)]
    fun bindCatalogDetailedViewModel(impl: CatalogDetailedViewModel): ViewModel

}