package com.epis.lab05_idnp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BarChartActivity : AppCompatActivity() {

    // Variables para controlar el color de las barras
    private var color = Color.rgb(0, 0, 0)
    private lateinit var seekBarR: SeekBar
    private lateinit var seekBarG: SeekBar
    private lateinit var seekBarB: SeekBar
    private lateinit var viewColor: View
    // RecyclerView para controlar los Bar Chart Items
    private var data: MutableList<BarChartItem> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var xEditText: EditText
    private lateinit var yEditText: EditText
    private lateinit var btnReset: Button
    private lateinit var btnAdd: Button
    // Bar Chart personalizado que hereda de View
    private lateinit var barChart: BarChart
    private lateinit var btnDraw: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        // Agrega titulo y boton de retroceder
        val actionbar = supportActionBar
        actionbar!!.title = "Bar Chart"
        actionbar.setDisplayHomeAsUpEnabled(true)

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter

        // EditText para controlar valores nuevos de X e Y
        xEditText = findViewById(R.id.newX)
        yEditText = findViewById(R.id.newY)

        // Controladores de color
        seekBarR = findViewById(R.id.rValue)
        seekBarG = findViewById(R.id.gValue)
        seekBarB = findViewById(R.id.bValue)
        viewColor = findViewById(R.id.previewColor)
        initColorControls()

        // BarChart personalizado
        barChart = findViewById(R.id.barChart)

        // Botones para controlar acciones
        btnReset = findViewById(R.id.resetList)
        btnAdd = findViewById(R.id.addToList)
        btnDraw = findViewById(R.id.btn_draw)
        setButtonActions()
    }

    private fun initColorControls() {
        // Indicamos el valor maximo de los seekBar
        val step = 1
        val min = 0
        val max = 255
        seekBarR.max = (max - min) / step
        seekBarG.max = (max - min) / step
        seekBarB.max = (max - min) / step
        // Agrega listeners a los seekBars
        seekBarR.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) { }
            override fun onStartTrackingTouch(seek: SeekBar) { }
            override fun onStopTrackingTouch(seek: SeekBar) {
                updateColor()
            }
        })
        seekBarG.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) { }
            override fun onStartTrackingTouch(seek: SeekBar) { }
            override fun onStopTrackingTouch(seek: SeekBar) {
                updateColor()
            }
        })
        seekBarB.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) { }
            override fun onStartTrackingTouch(seek: SeekBar) { }
            override fun onStopTrackingTouch(seek: SeekBar) {
                updateColor()
            }
        })
    }

    private fun updateColor() {
        // Actualiza el valor del color actual
        color = Color.rgb(seekBarR.progress, seekBarG.progress, seekBarB.progress)
        viewColor.setBackgroundColor(color)
        barChart.setColor(color)
    }

    private fun setButtonActions() {
        // Limpia la data
        btnReset.setOnClickListener {
            data.clear()
            barChart.resetData()
            adapter.notifyDataSetChanged()
        }
        // Agrega valores nuevos
        btnAdd.setOnClickListener {
            val xValue = xEditText.text.toString().toIntOrNull()
            val yValue = yEditText.text.toString().toIntOrNull()
            if (xValue != null || yValue != null) {
                val bar = BarChartItem(xValue!!, yValue!!)
                data.add(bar)
                barChart.addBar(bar)
                xEditText.text.clear()
                yEditText.text.clear()
                adapter.notifyDataSetChanged()
            }
        }
        // Grafica la data
        btnDraw.setOnClickListener {
            barChart.drawBarChart()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}