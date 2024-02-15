package com.belkanoid.remote

import com.belkanoid.product_remote.ProductResponse
import retrofit2.http.GET


interface EffectiveMobileShopApi {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts(): ProductResponse

}