package com.belkanoid.remote.di

import com.belkanoid.di.BaseApi
import com.belkanoid.remote.EffectiveMobileShopApi

interface RemoteSourceApi: BaseApi {

    val effectiveMobileShopApi: EffectiveMobileShopApi
}