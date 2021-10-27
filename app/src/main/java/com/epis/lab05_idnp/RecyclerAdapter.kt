package com.epis.lab05_idnp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val bars: MutableList<BarChartItem>): RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bar_chart_item, parent, false)
        return RecyclerAdapterHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterHolder, position: Int) {
        val currentItem = bars[position]
        holder.xTextView.text = currentItem.x.toString()
        holder.yTextView.text = currentItem.y.toString()
    }

    override fun getItemCount(): Int {
        return bars.size
    }

    class RecyclerAdapterHolder(item: View): RecyclerView.ViewHolder(item) {
        val xTextView: TextView = item.findViewById(R.id.xValue)
        val yTextView: TextView = item.findViewById(R.id.yValue)
    }

}