package com.belkanoid.navigation_impl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.navigation_impl.NavigationActivityProvider
import com.belkanoid.navigation_impl.R
import com.belkanoid.navigation_impl.databinding.FragmentTabsBinding
import com.belkanoid.navigation_impl.di.componentHolder.NavigationComponentHolder
import javax.inject.Inject

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val binding by viewBinding(FragmentTabsBinding::bind)

    val navController by lazy {
        (childFragmentManager.findFragmentById(R.id.tabs_container) as NavHostFragment).navController
    }

    @Inject lateinit var activityProvider: NavigationActivityProvider

    override fun onAttach(context: Context) {
        super.onAttach(context)
        NavigationComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.home_graph,
                R.id.catalog_graph,
                R.id.cart_graph,
                R.id.discount_graph,
                R.id.profile_graph,
            ).build()

        NavigationUI.setupActionBarWithNavController(
            activityProvider.get() as AppCompatActivity,
            navController,
            appBarConfiguration
        )
        binding.bottomNavigationView.setupWithNavController(navController)
    }



    override fun onDetach() {
        super.onDetach()
        NavigationComponentHolder.clear()
    }


    private fun setupBackButton() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!navController.navigateUp()) {
                        activity?.finish()
                    }
                }
            }
        )
    }


}