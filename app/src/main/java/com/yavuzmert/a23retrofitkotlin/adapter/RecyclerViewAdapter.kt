package com.yavuzmert.a23retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yavuzmert.a23retrofitkotlin.R
import com.yavuzmert.a23retrofitkotlin.model.CryptoModel


class RecyclerViewAdapter(private val cryptoList : ArrayList<CryptoModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors : Array<String> = arrayOf("#ff0000","#686868","#a0a0a0","#404040")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textName: TextView = view.findViewById(R.id.text_name)
        private val textPrice: TextView = view.findViewById(R.id.text_price)

        fun bind(cryptoModel: CryptoModel, colors : Array<String>, position : Int, listener : Listener) {
            textName.text = cryptoModel.currency
            textPrice.text = cryptoModel.price
            itemView.setBackgroundColor(Color.parseColor(colors[position % 4]))
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    //hangi item ne verisi gösterecek onu yazıyoruz
    override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.bind(cryptoList[position], colors,position, listener )
    }
}