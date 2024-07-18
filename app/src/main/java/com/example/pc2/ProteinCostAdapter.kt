package com.example.pc2

import ProteinCostData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProteinCostAdapter(private var dataList: MutableList<ProteinCostData>) :
    RecyclerView.Adapter<ProteinCostAdapter.ViewHolder>() {
    private val TAG = "ProteinCostAdapter"
    private lateinit var proteinCostAdapter: ProteinCostAdapter // Add this property



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodSourceTextView: TextView = itemView.findViewById(R.id.MealRV)
        val costPer50TextView: TextView = itemView.findViewById(R.id.CostPer50RV)
        val costPer1TextView: TextView = itemView.findViewById(R.id.CostPer1RV)
        val costPerServingTextView: TextView = itemView.findViewById(R.id.ServingCostRV)
        val servingsTextView: TextView = itemView.findViewById(R.id.ServingsRV)
        val gramsTextView: TextView = itemView.findViewById(R.id.GramsRV)
        val priceTextView: TextView = itemView.findViewById(R.id.PriceRV)
        val calTextView: TextView = itemView.findViewById(R.id.PercentRV)

        // Set the background drawable based on the selected state
        fun bind(item: ProteinCostData) {
            itemView.isActivated = item.isSelected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_protein_cost, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = dataList[position]
        // Bind other views and set the selected state background
        holder.bind(currentItem)


        holder.itemView.setOnClickListener {
            currentItem.isSelected = !currentItem.isSelected
            notifyItemChanged(holder.adapterPosition)
        }

        holder.foodSourceTextView.text = currentItem.foodSource
        holder.costPer50TextView.append(currentItem.fiftyGrams)
        holder.costPer1TextView.append(currentItem.oneGram)
        holder.costPerServingTextView.append(currentItem.servingCost.toString())
        holder.servingsTextView.append(currentItem.servings)
        holder.gramsTextView.append(currentItem.grams)
        holder.priceTextView.append(currentItem.price)
        holder.calTextView.append(currentItem.cal)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun updateData(newDataList: MutableList<ProteinCostData>) {
        Log.d(TAG, "Called updateData" )
        dataList = newDataList
        notifyDataSetChanged()
    }
}
