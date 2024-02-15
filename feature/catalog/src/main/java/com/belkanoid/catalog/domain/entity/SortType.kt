package com.belkanoid.catalog.domain.entity

sealed class SortType {

    data object Fame: SortType()

    data object DecreasePrice: SortType()

    data object IncreasePrice: SortType()

}