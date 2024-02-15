package com.belkanoid.effectivemobileshop.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belkanoid.effectivemobileshop.R
import com.belkanoid.log_in.presentation.LoginFragment
import com.belkanoid.navigation_impl.NavigationActivity
import com.belkanoid.navigation_impl.presentation.TabsFragment

class MainActivity : AppCompatActivity(), NavigationActivity {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

    }


    override fun getNavigationTabsFragment(): TabsFragment {
        return supportFragmentManager.fragments
            .filterIsInstance<TabsFragment>()
            .firstOrNull()
            ?: error("Do not call NavigationApi when activity doesn't exist")
    }


    override fun setTabsFragment(): () -> Unit = {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_container, TabsFragment())
            .commit()
    }

    override fun setLoginFragment(): () -> Unit = {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_container, LoginFragment())
            .commit()
    }


    override fun onSupportNavigateUp(): Boolean =
        getNavigationTabsFragment().navController.navigateUp() || super.onSupportNavigateUp()
}