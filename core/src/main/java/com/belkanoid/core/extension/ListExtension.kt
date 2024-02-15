package com.belkanoid.core.extension


fun <T> List<T>.containsAtLeastOne(elements: List<T>): Boolean {
    elements.forEach { element ->
        if (this.contains(element)) {
            return true
        }
    }
    return false
}