package com.belkanoid.navigation_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_impl.NavigationActivityProvider
import com.belkanoid.navigation_impl.R
import com.belkanoid.navigation_impl.di.componentHolder.NavigationComponentHolder
import com.belkanoid.navigation_impl.domain.NavigationUseCase
import javax.inject.Inject

class NavigationFragment : Fragment(R.layout.fragment_navigation) {

    @Inject lateinit var navigationUseCase: NavigationUseCase
    @Inject lateinit var activityProvider: NavigationActivityProvider
    private val navController by lazy {
        (childFragmentManager.findFragmentById(R.id.navigation_container) as NavHostFragment).navController
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        NavigationComponentHolder.get().inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigationUI.setupActionBarWithNavController(
            activityProvider.get() as AppCompatActivity,
            navController
        )

        if(navigationUseCase.getIsUserLoggedIn()) {
            NavigationComponentHolder.get().loginNavigationApi.navigate(LoginDirections.ToTabs)
        }

    }


    override fun onDetach() {
        super.onDetach()
        NavigationComponentHolder.clear()
    }


}