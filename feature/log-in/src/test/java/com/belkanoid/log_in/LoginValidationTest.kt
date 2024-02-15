package com.belkanoid.log_in

import org.junit.Assert.assertEquals
import org.junit.Test

class LoginValidationTest {

    @Test
    fun validate1() {
        val value = "sdafadsfa"
        var isValueCyrillic = true
        var index = 0
        for (i in value.indices) {
            index = i
            if (Character.UnicodeBlock.of(value[i]) != Character.UnicodeBlock.CYRILLIC) {
                isValueCyrillic = false
                println("$i - ${value[i]}")

            }
        }

        assertEquals(false, isValueCyrillic)
    }

    @Test
    fun validate2() {
        val value = "ыв.фавыа sdfasdfads"
        var isValueCyrillic = true
        var index = 0
        for (i in value.indices) {
            index = i
            if (Character.UnicodeBlock.of(value[i]) != Character.UnicodeBlock.CYRILLIC) {
                isValueCyrillic = false
                println("$i - ${value[i]}")
            }
        }

        assertEquals(false, isValueCyrillic)
    }


    @Test
    fun validate3() {
        val value = "вывфавы.авыфа"
        var isValueCyrillic = true
        var index = 0
        for (i in value.indices) {
            index = i
            if (Character.UnicodeBlock.of(value[i]) != Character.UnicodeBlock.CYRILLIC) {
                isValueCyrillic = false
                println("$i - ${value[i]}")

            }
        }

        assertEquals(false, isValueCyrillic)
    }

    @Test
    fun validate4() {
        val value = "вывфавыавыфа"
        var isValueCyrillic = true
        var index = 0
        for (i in value.indices) {
            index = i
            if (Character.UnicodeBlock.of(value[i]) != Character.UnicodeBlock.CYRILLIC) {
                isValueCyrillic = false
                println("$i - ${value[i]}")

            }
        }

        assertEquals(true, isValueCyrillic)
    }


}