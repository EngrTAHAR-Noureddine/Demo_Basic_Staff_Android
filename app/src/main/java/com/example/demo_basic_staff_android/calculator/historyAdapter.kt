package com.example.demo_basic_staff_android.calculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_basic_staff_android.R
import com.example.demo_basic_staff_android.database.History

//private val historyList: List<History>
class HistoryAdapter() :
    RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {

    var historyList = listOf<History>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val element = historyList[position]
        holder.txtOperation.text = element.operation
        holder.txtResult.text = element.result

    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtOperation = itemView.findViewById(R.id.operationSyntax) as TextView
        val txtResult = itemView.findViewById(R.id.resultSyntax) as TextView
    }
}