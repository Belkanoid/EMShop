package com.belkanoid.catalog.presentation.catalogItemDetailed

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.belkanoid.catalog.R
import com.belkanoid.catalog.databinding.FragmentCatalogDetailedBinding
import com.belkanoid.catalog.di.componentHolder.CatalogComponentHolder
import com.belkanoid.catalog.presentation.catalogItemDetailed.viewModel.CatalogDetailedViewModel
import com.belkanoid.core.ui.factory.ViewModelFactory
import com.belkanoid.product.Product
import com.belkanoid.product.extensions.toComposition
import javax.inject.Inject


class CatalogItemDetailedFragment : Fragment(R.layout.fragment_catalog_detailed) {

    private val product get() = requireArguments().getSerializable("product") as Product
    @Inject lateinit var factory: ViewModelFactory
    private val viewModel by viewModels<CatalogDetailedViewModel>{factory}
    private val binding by viewBinding(FragmentCatalogDetailedBinding::bind)
    private val catalogDetailedAdapter get() = viewModel.catalogDetailedAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CatalogComponentHolder.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailedRvDescription.adapter = catalogDetailedAdapter
        catalogDetailedAdapter.items = product.toComposition()
    }

    override fun onDetach() {
        super.onDetach()
        CatalogComponentHolder.clear()

    }
}