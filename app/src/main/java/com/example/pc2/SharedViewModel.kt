package com.example.pc2

import ProteinCostData
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedViewModel : ViewModel() {
    private val TAG = "ViewModel" //Debugging Tag

//    // Define a LiveData for the list of ProteinCostData
//    var proteinCostList = mutableListOf<ProteinCostData>()
//    val proteinCostLiveData: MutableLiveData<List<ProteinCostData>> = MutableLiveData()


    // Define a LiveData for the list of ProteinCostData
    private val _proteinCostLiveData = MutableLiveData<List<ProteinCostData>>()
    val proteinCostLiveData: LiveData<List<ProteinCostData>> get() = _proteinCostLiveData

    // Function to save or update food items in SharedPreferences
    fun updateFoodItem(context: Context, newItem: ProteinCostData, edit: Boolean) {
        Log.d("SharedViewModel", "newItem: $newItem")
        // Retrieve the existing items from SharedPreferences
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPrefs.getString("proteinCostList", null)
        // Define the type token for List<ProteinCostData>
        val type = object : TypeToken<List<ProteinCostData>>() {}.type

        // Deserialize JSON to List<ProteinCostData> or create an empty list if no data
        val existingItems: MutableList<ProteinCostData> = if (json != null) {
            // Deserialize the JSON to List<ProteinCostData>
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }

        // Determine whether to update, add, or remove the item
        if (edit) {
            val index = existingItems.indexOfFirst { it.id == newItem.id }
            if (index != -1) {
                existingItems[index] = newItem
            }
        } else {
            val index = existingItems.indexOfFirst { it.id == newItem.id }
            if (index != -1) {
                existingItems.removeAt(index)
            } else {
                existingItems.add(newItem)
            }
        }


        // Serialize the updated list to JSON
        val updatedJson = Gson().toJson(existingItems, type)

        // Save the updated list back to SharedPreferences
        with(sharedPrefs.edit()) {
            putString("proteinCostList", updatedJson)
            apply()
        }

        // Update the LiveData with the new list
        _proteinCostLiveData.value = existingItems
    }



    // Function to load saved data from SharedPreferences
    private fun loadSavedDataFromSharedPreferences(context: Context): List<ProteinCostData> {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val json = sharedPrefs.getString("proteinCostList", null)
        val type = object : TypeToken<List<ProteinCostData>>() {}.type

        return if (json != null) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }

    // Load data and update LiveData
    fun loadSavedData(context: Context) {
        val data = loadSavedDataFromSharedPreferences(context)
        updateProteinCostList(data)
    }

    // Update protein cost list
    private fun updateProteinCostList(newData: List<ProteinCostData>) {
        _proteinCostLiveData.value = newData
    }

//    fun removeFoodItem(context: Context, itemToRemove: ProteinCostData) {
//        // Retrieve the existing items from SharedPreferences
//        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val json = sharedPrefs.getString("proteinCostList", null)
//        val type = object : TypeToken<List<ProteinCostData>>() {}.type
//
//        // Deserialize JSON to List<ProteinCostData> or create an empty list if no data
//        val existingItems: MutableList<ProteinCostData> = if (json != null) {
//            Gson().fromJson(json, type)
//        } else {
//            mutableListOf()
//        }
//
//        // Remove the specified item from the list
//        existingItems.remove(itemToRemove)
//
//        // Serialize the updated list to JSON
//        val updatedJson = Gson().toJson(existingItems, type)
//
//        // Save the updated list back to SharedPreferences
//        with(sharedPrefs.edit()) {
//            putString("proteinCostList", updatedJson)
//            apply()
//        }
//
//        // Update the LiveData with the new list
//        _proteinCostLiveData.value = existingItems
//    }



//    // Define a map to store the ascending flags for each sorting function
//    private val sortAscendingFlags = mutableMapOf(
//        R.id.FoodButton to false,
//        R.id.CostPer50Button to false,
//        R.id.CostPer1Button to false,
//        R.id.ServingCostButton to false,
//        R.id.ServingsButton to false,
//        R.id.GramsButton to false,
//        R.id.PriceButton to false,
//        R.id.CalPercentButton to false
//    )
//
//    // Function to save food items to SharedPreferences
//    fun saveFoodItems(context: Context, items: List<ProteinCostData>) {
//        val json = Gson().toJson(items)
//        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        with(sharedPrefs.edit()) {
//            putString("proteinCostList", json)
//            apply()
//        }
//    }
//
//    // Function to load saved data from SharedPreferences
//    fun loadSavedDataFromSharedPreferences(context: Context): List<ProteinCostData> {
//        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val json = sharedPrefs.getString("proteinCostList", null)
//        val type = object : TypeToken<List<ProteinCostData>>() {}.type
//        return if (json != null) {
//            Gson().fromJson(json, type) ?: emptyList()
//        } else {
//            emptyList()
//        }
//    }
//
//    // Return the size of ProteinCostList
//    fun size(): Int = proteinCostList.size
//
//    // Remove items from the ProteinCostList
//    fun remove(itemsToRemove: List<ProteinCostData>) {
//        proteinCostList.removeAll(itemsToRemove)
//        // Update the live data
//        Log.d(TAG, "Live Data Content: ${proteinCostLiveData.value}")
//        proteinCostLiveData.value = proteinCostList.toMutableList()
//    }
//
//    fun updateProteinCostList(newData: List<ProteinCostData>) {
//        proteinCostList.clear()
//        proteinCostList.addAll(newData)
//        proteinCostLiveData.value = proteinCostList.toMutableList()
//    }
//
//    fun updateProteinCostListFromSharedPreferences(context: Context) {
//        proteinCostList.clear()
//        proteinCostList.addAll(loadSavedDataFromSharedPreferences(context))
//        proteinCostLiveData.value = proteinCostList
//    }
//
//    fun updateDataToSharedPreferences(context: Context, mergedData: List<ProteinCostData>) {
//        val existingData = loadSavedDataFromSharedPreferences(context).toMutableList()
//        existingData.addAll(mergedData)
//        saveFoodItems(context, existingData)
//    }
//
//    fun replaceDataToSharedPreferences(context: Context, mergedData: List<ProteinCostData>) {
//        saveFoodItems(context, mergedData)
//    }
//
//    // General sorting function
//    private fun generalSort(buttonId: Int, sortingFunction: (ProteinCostData) -> String) {
//        if (!sortAscendingFlags[buttonId]!!) {
//            proteinCostList = proteinCostList.sortedBy { sortingFunction(it) }.toMutableList()
//        } else {
//            proteinCostList = proteinCostList.sortedByDescending { sortingFunction(it) }.toMutableList()
//        }
//        sortAscendingFlags[buttonId] = !sortAscendingFlags[buttonId]!!
//        proteinCostLiveData.value = proteinCostList
//    }
//
//    // Specific sorting functions using generalSort
//    fun foodSort() = generalSort(R.id.FoodButton) { it.foodSource.lowercase() }
//    fun costPer50Sort() = generalSort(R.id.CostPer50Button) { it.fiftyGrams }
//    fun unitCostSort() = generalSort(R.id.CostPer1Button) { it.oneGram }
//    fun servingCostSort() = generalSort(R.id.ServingCostButton) { it.servingCost }
////    fun servingsSort() = generalSort(R.id.ServingsButton) { it.servings }
////    fun gramSort() = generalSort(R.id.GramsButton) { it.grams }
////    fun priceSort() = generalSort(R.id.PriceButton) { it.price }
////    fun calPercentSort() = generalSort(R.id.CalPercentButton) { it.cal }
}
