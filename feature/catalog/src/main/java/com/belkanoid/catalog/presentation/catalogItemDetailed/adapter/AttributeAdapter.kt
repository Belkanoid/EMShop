package com.belkanoid.catalog.presentation.catalogItemDetailed.adapter

import androidx.recyclerview.widget.DiffUtil
import com.belkanoid.catalog.databinding.AttributeItemBinding
import com.belkanoid.product.Info
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AttributeAdapter : AsyncListDifferDelegationAdapter<Info>(AttributeDiffer) {

    init {
        delegatesManager.addDelegate(attributeDefaultAdapter())
    }

    private fun attributeDefaultAdapter() =
        adapterDelegateViewBinding<Info, Info, AttributeItemBinding>(
            { inflater, parent ->
                AttributeItemBinding.inflate(inflater, parent, false)
            }
        ) {

            bind {
                binding.attributeItemTvInfo.text = item.title
                binding.attributeItemTvValue.text = item.value
            }
        }


    private object AttributeDiffer : DiffUtil.ItemCallback<Info>() {
        override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem == newItem
        }

    }
}
