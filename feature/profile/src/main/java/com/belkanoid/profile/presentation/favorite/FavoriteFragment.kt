package com.belkanoid.profile.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.core.util.SwitchController
import com.belkanoid.profile.R
import com.belkanoid.profile.databinding.FragmentFavoriteBinding
import com.belkanoid.profile.di.componentHolder.ProfileComponentHolder
import com.belkanoid.profile.presentation.favorite.state.FavoriteScreenState
import com.belkanoid.profile.presentation.favorite.viewModel.FavoriteViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel by viewModels<FavoriteViewModel>{factory}

    private val productAdapter get() = viewModel.productAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProfileComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleSwitch(view)
        binding.rvFavoriteProducts.adapter = productAdapter
        viewModel.state
            .onEach { state ->
                binding.favoriteProgressBar.visibility = View.INVISIBLE
                when(state) {
                    FavoriteScreenState.Empty -> {}
                    FavoriteScreenState.Loading -> {
                        binding.favoriteProgressBar.visibility = View.VISIBLE
                    }
                    is FavoriteScreenState.Success -> {
                        productAdapter.items = state.products
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFavoriteProducts()
    }


    private fun handleSwitch(view: View) {
        val mySwitch = SwitchController(view.findViewById(R.id.favorite_switch))
        mySwitch.setOnChangeListener(object : SwitchController.OnSelectedChangeListener {
            override fun OnSelectedChange(sender: SwitchController?) {
                if (sender!!.checkedIndex == 0) {
                    viewModel.getFavoriteProducts()
                } else if (sender.checkedIndex == 1) {
                    viewModel.getFavoriteBrands()
                }
            }
        })


        mySwitch.setSelected(0)
    }


    override fun onDetach() {
        super.onDetach()
        ProfileComponentHolder.clear()
    }

}





