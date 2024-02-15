package com.belkanoid.core.ui

import android.text.Editable
import android.text.TextWatcher
import kotlin.math.min

class NumberTextWatcher(private var mask: String) : TextWatcher {


    // simple mutex
    private var isCursorRunning = false
    private var isDeleting = false

    override fun afterTextChanged(s: Editable?) {
        if (isCursorRunning || isDeleting) {
            return
        }
        isCursorRunning = true

        s?.let {
            val onlyDigits = removeMask(it.toString())
            it.clear()
            it.append(applyMask(mask, onlyDigits))
        }

        isCursorRunning = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    private fun applyMask(mask: String, onlyDigits: String): String {
        var newMask = mask
        val maskPlaceholderCharCount = newMask.count { it == MASK_CHAR }
        var maskCurrentCharIndex = 0
        var output = ""

        onlyDigits.take(min(maskPlaceholderCharCount, onlyDigits.length)).forEach { c ->
            for (i in maskCurrentCharIndex until newMask.length) {
                if (c != '7' && output.isEmpty()) {
                    output += "+7"
                    maskCurrentCharIndex += 1
                    newMask = newMask.replace("+#", "")
                }
                if (newMask[i] == MASK_CHAR) {
                    output += c
                    maskCurrentCharIndex += 1
                    break
                } else {
                    output += newMask[i]
                    maskCurrentCharIndex = i + 1
                }
            }
        }
        return output
    }

    private fun removeMask(value: String): String {
        // extract all the digits from the string
        return Regex("\\D+").replace(value, "")
    }

    val MASK_CHAR = '#'
}