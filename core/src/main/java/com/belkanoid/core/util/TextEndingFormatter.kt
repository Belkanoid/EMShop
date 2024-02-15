package com.belkanoid.core.util

object TextEndingFormatter {

    fun productEndingFormatter(num: Int): String {
        val number = num.toString()
        val lastChar = number.last()

        return if (lastChar == '1' && num != 11) {
            "$number товар"
        }
        else if (lastChar == '2' && num != 12) {
            "$number товара"
        }
        else if (lastChar == '3' && num != 13) {
            "$number товара"
        }
        else if (lastChar == '4' && num != 14) {
            "$number товара"
        }
        else {
            "$number товаров"
        }
    }



    fun productAvailableEndingFormatter(num: Int): String {
        val number = num.toString()
        val lastChar = number.last()

        return if (lastChar == '1' && num != 11) {
            "Доступно для заказа ${number} штука"
        }
        else if (lastChar == '2' && num != 12) {
            "Доступно для заказа ${number} штуки"
        }
        else if (lastChar == '3' && num != 13) {
            "Доступно для заказа ${number} штуки"
        }
        else if (lastChar == '4' && num != 14) {
            "Доступно для заказа ${number} штуки"
        }
        else {
            "Доступно для заказа ${number} штук"
        }
    }



    fun productSurveyEndingFormatter(num: Int, rating: Double): String {
        val number = num.toString()
        val lastChar = number.last()

        return if (lastChar == '1' && num != 11) {
            "$rating • $num отзыв"
        }
        else if (lastChar == '2' && num != 12) {
            "$rating • $num отзыва"
        }
        else if (lastChar == '3' && num != 13) {
            "$rating • $num отзыва"
        }
        else if (lastChar == '4' && num != 14) {
            "$rating • $num отзыва"
        }
        else {
            "$rating • $num отзывов"
        }
    }



}