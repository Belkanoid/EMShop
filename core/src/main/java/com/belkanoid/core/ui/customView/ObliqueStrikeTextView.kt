package com.belkanoid.core.ui.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet

class ObliqueStrikeTextView : androidx.appcompat.widget.AppCompatTextView {


    private var dividerColor: Int = 0
    private lateinit var paint: Paint

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        dividerColor = this.textColors.defaultColor

        paint = Paint()
        paint.apply {
            color = dividerColor
            strokeWidth = 2f
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(::paint.isInitialized){
            canvas.drawLine(0.0f, height.toFloat()/1.3f, width.toFloat()/0.5f, 0.0f, paint)
        }
    }
}