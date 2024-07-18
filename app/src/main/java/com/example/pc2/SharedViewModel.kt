package com.example.pc2

import ProteinCostData
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedViewModel : ViewModel() {
    private val TAG = "ViewModel"
    // Define a LiveData for the list of ProteinCostData
    var proteinCostList = mutableListOf<ProteinCostData>()
    val proteinCostLiveData: MutableLiveData<List<ProteinCostData>> = MutableLiveData()
    // Define a map to store the ascending flags for each sorting function
    private val sortAscendingFlags = mutableMapOf(
        R.id.FoodButton to false,
        R.id.CostPer50Button to false,
        R.id.CostPer1Button to false,
        R.id.ServingCostButton to false,
        R.id.ServingsButton to false,
        R.id.GramsButton to false,
        R.id.PriceButton to false,
        R.id.CalPercentButton to false
    )


    //Return the size of ProteinCostList
    fun size(): Int{ return proteinCostList.size }

    //Remove Items from the ProteinCostList
    fun remove(itemsToRemove: List<ProteinCostData>){
        proteinCostList.removeAll(itemsToRemove)
        //Update the live data
        Log.d(TAG, "Live Data Content: ${proteinCostLiveData.value}")
        proteinCostLiveData.value = proteinCostList.toMutableList()
    }

    fun updateProteinCostList(newData: List<ProteinCostData>) {
        proteinCostList.clear()
        proteinCostList.addAll(newData)
        proteinCostLiveData.value = proteinCostList.toMutableList()
    }

    fun updateProteinCostListFromSharedPreferences(context: Context) {
        proteinCostList.clear()
        proteinCostList = loadSavedDataFromSharedPreferences(context).toMutableList()
        proteinCostLiveData.value = proteinCostList
    }

    fun updateDataToSharedPreferences(context: Context, mergedData: List<ProteinCostData>) {
        // Load existing data from shared preferences
        val existingData = loadSavedDataFromSharedPreferences(context).toMutableList()
        // Append new data to existing data
        existingData.addAll(mergedData)
        val proteinCostListJson = Gson().toJson(existingData)
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("proteinCostList", proteinCostListJson)
        editor.apply()
    }

    fun replaceDataToSharedPreferences(context: Context, mergedData: List<ProteinCostData>) {
        // Load existing data from shared preferences
        // Append new data to existing data
        val proteinCostListJson = Gson().toJson(mergedData)
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("proteinCostList", proteinCostListJson)
        editor.apply()
    }

    fun loadSavedDataFromSharedPreferences(context: Context): List<ProteinCostData> {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val proteinCostListJson = sharedPrefs.getString("proteinCostList", null)
        val type = object : TypeToken<List<ProteinCostData>>() {}.type
        val loadedData = if (proteinCostListJson != null) {
            Gson().fromJson<List<ProteinCostData>>(proteinCostListJson, type)
        } else {
            emptyList()
        }
        return loadedData
    }

    // General sorting function
    private fun generalSort(buttonId: Int, sortingFunction: (ProteinCostData) -> String) {
        if (!sortAscendingFlags[buttonId]!!) {
            proteinCostList = proteinCostList.sortedBy { sortingFunction(it) }.toMutableList()
        } else {
            proteinCostList = proteinCostList.sortedByDescending { sortingFunction(it) }.toMutableList()
        }
        sortAscendingFlags[buttonId] = !sortAscendingFlags[buttonId]!!
        proteinCostLiveData.value = proteinCostList
    }

    // Specific sorting functions using generalSort
    fun foodSort() = generalSort(R.id.FoodButton) { it.foodSource.lowercase() }
    fun costPer50Sort() = generalSort(R.id.CostPer50Button) { it.fiftyGrams }
    fun unitCostSort() = generalSort(R.id.CostPer1Button) { it.oneGram }
    fun servingCostSort() = generalSort(R.id.ServingCostButton) { it.servingCost }
    fun servingsSort() = generalSort(R.id.ServingsButton) { it.servings }
    fun gramSort() = generalSort(R.id.GramsButton) { it.grams }
    fun priceSort() = generalSort(R.id.PriceButton) { it.price }
    fun calPercentSort() = generalSort(R.id.CalPercentButton) { it.cal }
}
