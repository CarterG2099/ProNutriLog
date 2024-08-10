package com.example.pc2

import ProteinCostData
import android.content.Context
import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProteinCostViewModel : ViewModel() {

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

    //Sort the list by given attribute
    fun sortProteinCostList(sortAttribute: String, proteinCostList: List<ProteinCostData>): List<ProteinCostData> {
        return when (sortAttribute) {
            "foodSource" -> proteinCostList.sortedBy { it.foodSource }
            "servings" -> proteinCostList.sortedBy { it.servings }
            "grams" -> proteinCostList.sortedBy { it.grams }
            "price" -> proteinCostList.sortedBy { it.price }
            "calories" -> proteinCostList.sortedBy { it.calories }
            else -> proteinCostList
        }
    }

}