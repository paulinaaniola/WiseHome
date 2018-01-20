package com.example.paulina.wisehome.model.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.TextView


class VerticalTextView(context: Context, attrs: AttributeSet) : TextView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun onDraw(canvas: Canvas) {
        val textPaint = paint
        textPaint.color = currentTextColor
        textPaint.drawableState = drawableState

        canvas.save()
        canvas.translate(0f, height.toFloat())
        canvas.rotate(-90f)

        canvas.translate(compoundPaddingLeft.toFloat(), extendedPaddingTop.toFloat())

        layout.draw(canvas)
        canvas.restore()
    }
}