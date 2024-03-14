package com.belkanoid.core.util

object TextValidate {
    fun isCyrillic(value: String): Boolean {
        if (value.isEmpty()) return true

        value.forEach {
            if (Character.UnicodeBlock.of(it) != Character.UnicodeBlock.CYRILLIC) {
                return false
            }
        }
        return true
    }
}