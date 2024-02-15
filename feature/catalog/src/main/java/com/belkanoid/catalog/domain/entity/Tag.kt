package com.belkanoid.catalog.domain.entity

data class TagName(
    val name: String,
    val requestName: String
)

sealed class Tag(val value: TagName) {

    class Default(value: TagName) : Tag(value)

    class Chosen(value: TagName) : Tag(value)

}
