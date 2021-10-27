package com.epis.lab05_idnp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BarChart(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // Data
    private var dataX: MutableList<Int> = ArrayList()
    private var dataY: MutableList<Int> = ArrayList()
    private var color = Color.rgb(0, 0, 0)
    // Lienzo para dibujar
    private lateinit var bitmap: Bitmap
    private lateinit var canvasGraphicBar: Canvas
    // Pincel
    private val brush = Paint()
    // Limites auxiliares
    private var maxAxisY = 0
    private var maxAxisX = 0
    private var widthGraphic = 0
    private var heightGraphic = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Inicializamos el bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        // Asignamos el bitmap a nuestro canvas
        canvasGraphicBar = Canvas(bitmap)
        // Obtenemos el espacio para graficar
        widthGraphic = w - 100
        heightGraphic = h - 100
    }

    override fun onDraw(canvas: Canvas) {
        // Asignamos el bitmap y el pincel
        canvas.drawBitmap(bitmap, 0f, 0f, brush)
        // Dibujamos los ejes X e Y
        drawAxis()
    }

    fun drawBarChart() {
        if (dataX.size != dataY.size || dataX.size == 0) return
        // Limpiamos el canvas antes de dibujar
        clearCanvas()
        // Calculamos unidad equivalente
        val unitX = kotlin.math.ceil((widthGraphic / (maxAxisX + 2)).toDouble())
        val unitY = kotlin.math.ceil((heightGraphic / maxAxisY).toDouble())
        // Dibujamos par por par
        for(i in 0 until dataX.size) {
            // Esquina superior izquierda
            val x1 = (dataX[i] * unitX).toFloat()
            val y1 = (heightGraphic - (dataY[i] * unitY)).toFloat()
            // Esquina superior derecha
            val x2 = (x1 + unitX).toFloat()
            val y2 = heightGraphic.toFloat()
            // Definimos el estilo del pincel para la barra
            brush.style = Paint.Style.FILL
            brush.color = color
            // Graficamos la barra
            canvasGraphicBar.drawRect(x1 + 100F, y1, x2 + 100F, y2, brush)
            // Definimos el estilo del pincel para los labels
            brush.color = Color.BLACK
            brush.textSize = 50F
            brush.textAlign = Paint.Align.LEFT
            // Graficamos los labels
            canvasGraphicBar.drawText(dataY[i].toString(), 25F, y1 + 50F, brush)
            canvasGraphicBar.drawText(dataX[i].toString(), x2, (heightGraphic + 50).toFloat(), brush)
        }
    }

    private fun clearCanvas() {
        // Limpia el canvas
        canvasGraphicBar.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        drawAxis()
    }

    private fun drawAxis() {
        // Dibuja los ejes X e Y
        brush.color = Color.BLACK
        brush.style = Paint.Style.STROKE
        brush.strokeWidth = 4F
        val p = Path()
        // Eje Y
        p.moveTo(100F, 0F)
        p.lineTo(100F, heightGraphic.toFloat())
        p.moveTo(80F, 20F)
        p.lineTo(100F, 0F)
        p.moveTo(100F, 0F)
        p.lineTo(120F, 20F)
        // Eje X
        p.moveTo(100F, heightGraphic.toFloat())
        p.lineTo(width.toFloat(), heightGraphic.toFloat())
        p.moveTo((width - 20).toFloat(), (heightGraphic - 20).toFloat())
        p.lineTo(width.toFloat(), heightGraphic.toFloat())
        p.moveTo(width.toFloat(), heightGraphic.toFloat())
        p.lineTo((width - 20).toFloat(), (heightGraphic + 20).toFloat())
        // Dibujamos los ejes X e Y en el canvas
        canvasGraphicBar.drawPath(p, brush)
    }

    fun addBar(bar: BarChartItem) {
        // Agrega nuevos valores
        if (bar.x < 0 || bar.y < 0) throw Exception("No se admiten valores negativos.")
        dataX.add(bar.x)
        dataY.add(bar.y)
        if (bar.x > maxAxisX)
            maxAxisX = bar.x
        if (bar.y > maxAxisY)
            maxAxisY = bar.y
    }

    fun setColor(colorBars: Int) {
        // Cambia de color
        color = colorBars
    }

    fun resetData() {
        // Reinicia la data
        dataX.clear()
        dataY.clear()
        maxAxisX = 0
        maxAxisY = 0
        clearCanvas()
    }

}