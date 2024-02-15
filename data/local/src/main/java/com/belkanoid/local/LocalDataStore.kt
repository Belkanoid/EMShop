package com.belkanoid.local

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import javax.inject.Inject


class LocalDataStore @Inject constructor(
    private val app: Application
) {

    private val sharedPref = app.getSharedPreferences("pref", Context.MODE_PRIVATE)


    suspend fun saveUserToLocalStore(name: String, surname: String, phone: String) {
        with(sharedPref.edit()) {
            putString("name", name)
            putString("surname", surname)
            putString("phone", phone)
            putBoolean("isSignedIn", true)
            apply()
        }
    }

    fun isUserStored(): Boolean {
        return sharedPref.getBoolean("isSignedIn", false)
    }

    @SuppressLint("MutatingSharedPrefs")
    fun saveProductId(id: String) {
        val allFavoriteProducts = sharedPref.getStringSet("favorite", mutableSetOf<String>()) ?: mutableSetOf()
        allFavoriteProducts.add(id)
        with(sharedPref.edit()) {
            remove("favorite")
            apply()
            putStringSet("favorite", allFavoriteProducts)
            apply()
        }
    }

    @SuppressLint("MutatingSharedPrefs")
    fun removeProductId(id: String) {
        val allFavoriteProducts = sharedPref.getStringSet("favorite", mutableSetOf<String>()) ?: mutableSetOf()
        allFavoriteProducts.remove(id)
        with(sharedPref.edit()) {
            remove("favorite")
            apply()
            putStringSet("favorite", allFavoriteProducts)
            apply()
        }
    }

    fun isProductFavorite(id: String): Boolean {
        val products = getFavoriteProductsId()
        return products.contains(id)
    }

    fun getFavoriteProductsId() = sharedPref.getStringSet("favorite", emptySet()) ?: emptySet()


    fun getUserName() = sharedPref.getString("name", "") ?: ""
    fun getUserSurname() = sharedPref.getString("surname", "") ?: ""
    fun getUserPhone() = sharedPref.getString("phone", "") ?: ""

    fun clearStorage(){
        sharedPref.edit().clear().apply()
    }

}