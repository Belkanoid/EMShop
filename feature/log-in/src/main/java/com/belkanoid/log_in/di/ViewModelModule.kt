package com.belkanoid.log_in.di

import androidx.lifecycle.ViewModel
import com.belkanoid.core.di.ViewModelKey
import com.belkanoid.log_in.presentation.viewModel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @[Binds IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindLoginViewModel(impl: LoginViewModel): ViewModel

}