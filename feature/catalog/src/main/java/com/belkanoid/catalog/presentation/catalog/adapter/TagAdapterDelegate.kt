package com.belkanoid.catalog.presentation.catalog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.belkanoid.catalog.databinding.TagListItemChosenBinding
import com.belkanoid.catalog.databinding.TagListItemDefaultBinding
import com.belkanoid.catalog.domain.entity.Tag
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class TagAdapter(
    private val onDefaultTagClick: (Tag) -> Unit,
    private val onChosenTagClick: (Tag) -> Unit,
) : AsyncListDifferDelegationAdapter<Tag>(TagDiffer) {

    init {
        delegatesManager
            .addDelegate(defaultAdapterDelegate{onDefaultTagClick(it)})
            .addDelegate(chosenAdapterDelegate{onChosenTagClick(it)})
    }


    private fun defaultAdapterDelegate(onClick: (Tag.Default) -> Unit) =
        adapterDelegateViewBinding<Tag.Default, Tag, TagListItemDefaultBinding>(
        {layoutInflater, parent ->  TagListItemDefaultBinding.inflate(layoutInflater, parent, false)}
    ) {

        binding.root.setOnClickListener{
            onClick(item)
        }


        bind {
            binding.tagTvName.text = item.value.name
        }
    }


    private fun chosenAdapterDelegate(onClick: (Tag.Chosen) -> Unit) =
        adapterDelegateViewBinding<Tag.Chosen, Tag, TagListItemChosenBinding>(
        {layoutInflater, parent ->  TagListItemChosenBinding.inflate(layoutInflater, parent, false)}
    ) {

        binding.root.setOnClickListener{
            onClick(item)
        }

        bind {
            binding.tagTvName.text = item.value.name
        }
    }


    private object TagDiffer : DiffUtil.ItemCallback<Tag>(){
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem.value.name == newItem.value.name
        }

        override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
            return oldItem == newItem
        }

    }

}





