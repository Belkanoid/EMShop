package com.belkanoid.product

import java.io.Serializable

data class Feedback(
    val count: Int,
    val rating: Double
) : Serializable