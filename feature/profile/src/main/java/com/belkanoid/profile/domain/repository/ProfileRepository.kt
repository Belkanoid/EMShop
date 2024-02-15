package com.belkanoid.profile.domain.repository

import com.belkanoid.profile.domain.entity.UserInfo

interface ProfileRepository {

    fun getUserInfo(): UserInfo

    fun clearStorage()

}