package com.belkanoid.profile.domain.entity

import com.belkanoid.core.R

sealed class Profile{

    data class Default(
        val tittle: String,
        val icon: Int,
        val iconAction: Int = R.drawable.right_arrow_default,
        val onClick: () -> Unit
    ) : Profile()

    data class Subtitled(
        val tittle: String,
        val subtitle: String,
        val icon: Int,
        val iconAction: Int = R.drawable.right_arrow_default,
        val onClick: () -> Unit
    ) : Profile()


}
