package com.epis.lab05_idnp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class BarGraphic(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var drawPath: Path = Path()
    private var drawPaint: Paint = Paint()
    private var canvasPaint: Paint = Paint()
    private var textPaint: Paint = Paint()

    init {
        drawPaint.color = Color.BLACK
        drawPaint.textSize = 70F
    }

    override fun onDraw(canvas: Canvas) {

    }

}