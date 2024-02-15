package com.belkanoid.profile.presentation.profile.adapter

import com.belkanoid.profile.databinding.ProfileListItemBinding
import com.belkanoid.profile.databinding.ProfileSubtitledListItemBinding
import com.belkanoid.profile.domain.entity.Profile
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object ProfileAdapterDelegate {

    fun defaultAdapterDelegate() = adapterDelegateViewBinding<Profile.Default, Profile, ProfileListItemBinding>(
        {layoutInflater, parent ->  ProfileListItemBinding.inflate(layoutInflater, parent, false)}
    ) {

        bind {
            with(binding) {
                profileItemImg.setImageResource(item.icon)
                profileItemTvTittle.text = item.tittle
                profileItemImgArrowRight.setImageResource(item.iconAction)
            }
        }
    }

    fun subtitledAdapterDelegate() = adapterDelegateViewBinding<Profile.Subtitled, Profile, ProfileSubtitledListItemBinding>(
        {layoutInflater, parent ->  ProfileSubtitledListItemBinding.inflate(layoutInflater, parent, false)}
    ) {

        binding.root.setOnClickListener{
            item.onClick.invoke()
        }

        bind {
            with(binding) {
                profileItemImg.setImageResource(item.icon)
                profileItemTvTittle.text = item.tittle
                profileItemTvSubtitle.text = item.subtitle
                profileItemImgArrowRight.setImageResource(item.iconAction)
            }
        }
    }

}