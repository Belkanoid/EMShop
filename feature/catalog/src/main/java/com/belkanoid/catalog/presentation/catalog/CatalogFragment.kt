package com.belkanoid.catalog.presentation.catalog

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.catalog.R
import com.belkanoid.catalog.databinding.FragmentCatalogBinding
import com.belkanoid.catalog.di.componentHolder.CatalogComponentHolder
import com.belkanoid.catalog.presentation.catalog.state.CatalogScreenState
import com.belkanoid.catalog.presentation.catalog.viewModel.CatalogViewModel
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.core.util.SpaceItemDecoration
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val binding by viewBinding(FragmentCatalogBinding::bind)
    private val viewModel by viewModels<CatalogViewModel> { viewModelFactory }

    private val productAdapter get() = viewModel.productAdapter
    private val tagAdapter get() = viewModel.tagAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CatalogComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.catalogRvProducts.apply {
            adapter = productAdapter
            addItemDecoration(SpaceItemDecoration(8, 8, 0, 16))
        }
        binding.catalogRvTags.apply {
            adapter = tagAdapter
            addItemDecoration(SpaceItemDecoration(0, 16, 0, 0))
        }
        binding.catalogSpinnerSort.adapter = viewModel.spinnerAdapter(requireContext())

        binding.catalogSpinnerSort.onItemSelectedListener = viewModel.onSpinnerItemSelectedListener

        observeState()

    }

    private fun observeState() {
        viewModel.state
            .onEach { state ->
                binding.catalogProgressBar.visibility = View.INVISIBLE
                when (state) {
                    is CatalogScreenState.Empty -> {}
                    is CatalogScreenState.Loading -> {
                        binding.catalogProgressBar.visibility = View.VISIBLE
                    }

                    is CatalogScreenState.Success -> {
                        productAdapter.items = state.products
                        tagAdapter.items = state.tags
                    }
                }

            }
            .launchIn(lifecycleScope)
    }


    override fun onDetach() {
        super.onDetach()
        CatalogComponentHolder.clear()
    }
}
