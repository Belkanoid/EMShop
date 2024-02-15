package com.belkanoid.core.util

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.EventListener

class SwitchController(container: View) {
    private val buttons: Array<Button?>
    var checkedIndex = 0
    private val that: SwitchController
    private val listeners: MutableList<OnSelectedChangeListener> = ArrayList()
    fun setOnChangeListener(l: OnSelectedChangeListener) {
        listeners.clear()
        listeners.add(l)
    }

    fun setSelected(index: Int) {
        buttons[index]!!.isSelected = true
    }

    fun setTextLeft(text: String?) {
        buttons[0]!!.text = text
    }

    fun setTextRight(text: String?) {
        buttons[1]!!.text = text
    }

    init {
        that = this
        buttons = arrayOfNulls(2)
        var btnCounter = 0
        var i = 0
        var j = 0
        while (i < (container as ViewGroup).childCount && j < 2) {
            val nextChild = container.getChildAt(i)
            if (nextChild is Button) {
                buttons[j] = nextChild
                j++
                btnCounter = j
            }
            i++
        }
        val customSwitchListener =
            View.OnClickListener { view ->
                if (view as Button === buttons[0]) {
                    buttons[0]!!.isSelected = true
                    buttons[1]!!.isSelected = false
                    checkedIndex = 0
                } else {
                    buttons[0]!!.isSelected = false
                    buttons[1]!!.isSelected = true
                    checkedIndex = 1
                }
                for (hl in listeners) {
                    hl.OnSelectedChange(that)
                }
            }
        buttons[0]!!.setOnClickListener(customSwitchListener)
        buttons[1]!!.setOnClickListener(customSwitchListener)
    }

    interface OnSelectedChangeListener : EventListener {
        fun OnSelectedChange(sender: SwitchController?)
    }
}