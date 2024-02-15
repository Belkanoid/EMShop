package com.belkanoid.core.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.belkanoid.core.databinding.ProductImagesPagerBinding
import com.belkanoid.product.ProductImage
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import javax.inject.Inject

class ProductImageAdapter @Inject constructor(

) : AsyncListDifferDelegationAdapter<ProductImage>(ProductImageDiffer) {

    init {
        delegatesManager.addDelegate(defaultAdapterDelegate())
    }

    private fun defaultAdapterDelegate() =
        adapterDelegateViewBinding<ProductImage, ProductImage, ProductImagesPagerBinding>(
            { inflater, parent -> ProductImagesPagerBinding.inflate(inflater, parent, false) }
        ) {
            bind {
                binding.productImage.setImageResource(item.value)
            }
        }


    private object ProductImageDiffer : DiffUtil.ItemCallback<ProductImage>() {
        override fun areItemsTheSame(oldItem: ProductImage, newItem: ProductImage): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(oldItem: ProductImage, newItem: ProductImage): Boolean {
            return oldItem == newItem
        }
    }
}