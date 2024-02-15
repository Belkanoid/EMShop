package com.belkanoid.catalog.presentation.catalogItemDetailed.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.belkanoid.catalog.databinding.CatalogDetailedItemAttributeBinding
import com.belkanoid.catalog.databinding.CatalogDetailedItemDescriptionBinding
import com.belkanoid.catalog.databinding.CatalogDetailedItemHeaderBinding
import com.belkanoid.catalog.databinding.CatalogDetailedItemIngredientBinding
import com.belkanoid.catalog.presentation.catalogItemDetailed.adapter.util.slideDownWith
import com.belkanoid.catalog.presentation.catalogItemDetailed.adapter.util.slideUpWith
import com.belkanoid.core.R
import com.belkanoid.core.ui.adapter.ProductImageAdapter
import com.belkanoid.core.util.ImageLoader
import com.belkanoid.core.util.TextEndingFormatter
import com.belkanoid.product.ProductComposition
import com.google.android.material.tabs.TabLayoutMediator
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class CatalogDetailedAdapter(
    private val imageLoader: ImageLoader,
    private val isProductFavorite: (String) -> Boolean,
    private val onHeartIconClick: (String, Boolean) -> Unit,
) : AsyncListDifferDelegationAdapter<ProductComposition>(ProductDetailedDiffer) {

    init {
        delegatesManager
            .addDelegate(catalogDetailedHeader(ProductImageAdapter()))
            .addDelegate(catalogDetailedDescription())
            .addDelegate(catalogDetailedAttribute(AttributeAdapter))
            .addDelegate(catalogDetailedIngredient())
    }

    private fun catalogDetailedHeader(productImageAdapter: ProductImageAdapter) =
        adapterDelegateViewBinding<ProductComposition.Header, ProductComposition, CatalogDetailedItemHeaderBinding>(
            { layoutInflater, parent ->
                CatalogDetailedItemHeaderBinding.inflate(layoutInflater, parent, false).also {
                    it.detailedVpImage.adapter = productImageAdapter
                }
            }
        ) {


            binding.detailedImgHeart.setOnClickListener {
                if (isProductFavorite(item.id)) {
                    onHeartIconClick(item.id, false)
                    it.setBackgroundResource(R.drawable.heart_default)
                } else {
                    onHeartIconClick(item.id, true)
                    it.setBackgroundResource(R.drawable.heart_filled)
                }
            }


            bind {
                productImageAdapter.items = imageLoader.loadImage(item.id)


                with(binding) {
                    detailedImgStar1.setBackgroundResource(setStar(1, item.feedback.rating))
                    detailedImgStar2.setBackgroundResource(setStar(2, item.feedback.rating))
                    detailedImgStar3.setBackgroundResource(setStar(3, item.feedback.rating))
                    detailedImgStar4.setBackgroundResource(setStar(4, item.feedback.rating))
                    detailedImgStar5.setBackgroundResource(setStar(5, item.feedback.rating))

                    detailedImgHeart.setBackgroundResource(
                        if (isProductFavorite(item.id)) {
                            R.drawable.heart_filled
                        } else {
                            R.drawable.heart_default
                        }
                    )

                    detailedHeaderTvTittle.text = item.tittle
                    detailedHeaderTvSubtitle.text = item.subtitle
                    detailedHeaderTvAvailable.text =
                        TextEndingFormatter.productAvailableEndingFormatter(item.available)
                    detailedHeaderTvFeedback.text =
                        TextEndingFormatter.productSurveyEndingFormatter(item.feedback.count, item.feedback.rating)
                    detailedHeaderTvPriceWithDiscount.text =
                        "${item.price.priceWithDiscount} ${item.price.unit}"
                    detailedHeaderTvPrice.text = "${item.price.price} ${item.price.unit}"
                    detailedHeaderTvDiscount.text = "-${item.price.discount}%"

                    TabLayoutMediator(detailedVpTab, detailedVpImage) { _, _ -> }.attach()
                }
            }
        }

    private fun catalogDetailedDescription() =
        adapterDelegateViewBinding<ProductComposition.Description, ProductComposition, CatalogDetailedItemDescriptionBinding>(
            { layoutInflater, parent ->
                CatalogDetailedItemDescriptionBinding.inflate(layoutInflater, parent, false)
            }
        ) {

            var isShowing = true
            val toggleButton = binding.detailedDescriptionTvToggle

            binding.detailedDescriptionTvToggle.setOnClickListener {
                if (isShowing) {
                    binding.detailedDescriptionHolder.slideUpWith(toggleButton)
                    toggleButton.text = "Показать"
                } else {
                    binding.detailedDescriptionHolder.slideDownWith(toggleButton)
                    toggleButton.text = "Скрыть"
                }
                isShowing = !isShowing
            }

            bind {
                binding.detailedDescriptionBtnBrand.text = item.brand
                binding.detailedDescriptionTvText.text = item.text
            }

        }


    private fun catalogDetailedAttribute(attributeAdapter: AttributeAdapter) =
        adapterDelegateViewBinding<ProductComposition.Attribute, ProductComposition, CatalogDetailedItemAttributeBinding>(
            { layoutInflater, parent ->
                CatalogDetailedItemAttributeBinding.inflate(layoutInflater, parent, false)
            }
        ) {

            binding.detailedAttributeRvItem.adapter = attributeAdapter
            binding.detailedAttributeRvItem.addItemDecoration(
                DividerItemDecoration(binding.detailedAttributeRvItem.context, DividerItemDecoration.VERTICAL)
            )

            bind {
                attributeAdapter.items = item.value
            }
        }


    private fun catalogDetailedIngredient() =
        adapterDelegateViewBinding<ProductComposition.Ingredient, ProductComposition, CatalogDetailedItemIngredientBinding>(
            { layoutInflater, parent ->
                CatalogDetailedItemIngredientBinding.inflate(layoutInflater, parent, false)
            }
        ) {

            var isShowing = false
            val toggleButton = binding.detailedIngredientTvToggle
            val twoLinesHeight = binding.detailedIngredientTvText.height.toFloat()
            binding.detailedIngredientTvToggle.setOnClickListener {
                if (isShowing) {
                    binding.detailedIngredientTvText.slideUpWith(toggleButton, twoLinesHeight, false) {
                        binding.detailedIngredientTvText.maxLines = 2
                        toggleButton.text = "Показать"
                    }
                } else {
                    binding.detailedIngredientTvText.slideDownWith(toggleButton, false) {
                        binding.detailedIngredientTvText.maxLines = 10
                        toggleButton.text = "Скрыть"
                    }

                }
                isShowing = !isShowing
            }

            bind {
                with(binding) {
                    detailedIngredientTvText.text = item.value
                }
            }

        }


    private fun setStar(star: Int, rating: Double): Int {
        return if (star < rating) {
            R.drawable.small_star_default
        } else if (star - 1 < rating && rating < star + 1) {
            R.drawable.small_star_half
        } else {
            R.drawable.small_star_empty
        }
    }

    object ProductDetailedDiffer : DiffUtil.ItemCallback<ProductComposition>() {
        override fun areItemsTheSame(
            oldItem: ProductComposition,
            newItem: ProductComposition
        ): Boolean {
            return true
        }

        override fun areContentsTheSame(
            oldItem: ProductComposition,
            newItem: ProductComposition
        ): Boolean {
            return true
        }
    }

}






