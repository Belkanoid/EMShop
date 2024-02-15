package com.belkanoid.navigation_impl

import com.belkanoid.navigation_impl.presentation.TabsFragment


interface NavigationActivity {

    fun getNavigationTabsFragment(): TabsFragment

    fun setTabsFragment(): () -> Unit

    fun setLoginFragment(): () -> Unit

}