package com.main.testcustomview.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomTestView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    val paint = Paint()
    val path = Path()

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        //canvas?.drawColor(getRandomColor())
        changeText()
        canvas?.drawText("Some", 100F, 100F, paint)
    }

    private fun getRandomColor(): Int {
        val alpha = 255
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.argb(alpha, red, green, blue)
    }

    fun changeText() {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = 5F
        paint.textSize = 60F
    }

    fun changeColor() {
        val bitmap = Bitmap.createBitmap(
            100,
            100,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        draw(canvas)
        invalidate()
    }

}