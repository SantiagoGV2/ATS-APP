package com.fibrateltec.atsapp.ui.Signature

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View



class SignaturePad(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val path = Path()
    private val signaturePath = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(signaturePath, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(event.x, event.y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                signaturePath.reset()
                signaturePath.addPath(path)
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                signaturePath.lineTo(event.x, event.y)
                invalidate()
                return true
            }

            else -> return false
        }
    }

    fun clearSignature() {
        path.reset()
        signaturePath.reset()
        invalidate()
    }

    fun getSignature(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawPath(signaturePath, paint)
        return bitmap
    }

}