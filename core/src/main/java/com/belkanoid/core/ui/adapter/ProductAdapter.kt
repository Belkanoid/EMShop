package com.belkanoid.core.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.belkanoid.core.R
import com.belkanoid.core.databinding.ProductListItemBinding
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.product.Product
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import javax.inject.Provider

class ProductAdapter(
    private val productImageAdapter: Provider<ProductImageAdapter>,
    private val imageLoader: ImageLoader,
    private val isProductFavorite: (String) -> Boolean,
    private val onHeartIconClick: (String, Boolean) -> Unit,
    private val onDefaultAdapterClick: (Product) -> Unit,
) : AsyncListDifferDelegationAdapter<Product>(ProductDiffer) {

    init {
        delegatesManager
            .addDelegate(defaultAdapterDelegate(productImageAdapter))
    }

    private fun defaultAdapterDelegate(productImageAdapter: Provider<ProductImageAdapter>) =
        adapterDelegateViewBinding<Product, Product, ProductListItemBinding>(
            { layoutInflater, parent ->
                ProductListItemBinding.inflate(layoutInflater, parent, false)
            }
        ) {
            binding.root.setOnClickListener{
                onDefaultAdapterClick(item)
            }
            val imageAdapter = productImageAdapter.get()
            binding.productVpImage.adapter = imageAdapter

            binding.productImgHeart.setOnClickListener{
                if(isProductFavorite(item.id)) {
                    onHeartIconClick(item.id, false)
                    it.setBackgroundResource(R.drawable.heart_default)
                }
                else {
                    onHeartIconClick(item.id, true)
                    it.setBackgroundResource(R.drawable.heart_filled)
                }
            }

            bind {
                binding.apply {
                    productImgHeart.setBackgroundResource(
                        if(isProductFavorite(item.id)) {
                            R.drawable.heart_filled
                        }else {
                            R.drawable.heart_default
                        }
                    )
                    productTvPrice.text = "${item.price.price} ${item.price.unit}"
                    productTvPriceWithDiscount.text = "${item.price.priceWithDiscount} ${item.price.unit}"
                    productTvTittle.text = item.title
                    productTvSubtitle.text = item.subtitle
                    productTvRating.text = item.feedback.rating.toString()
                    productTvSurveys.text = "(${item.feedback.count})"
                    productTvDiscount.text = "-${item.price.discount}%"
                }
                imageAdapter.items = imageLoader.loadImage(item.id)
                TabLayoutMediator(binding.productVpTab, binding.productVpImage) { _, _ -> }.attach()
            }
        }

    private object ProductDiffer : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

}