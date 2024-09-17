package com.proNutriLog.proteinCalculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProteinCostViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _proteinCostLiveData = MutableLiveData<List<ProteinCostData>>()
    val proteinCostLiveData: LiveData<List<ProteinCostData>> get() = _proteinCostLiveData

    private val collectionRef = db.collection("proteinCostData")

    // Function to save or update food items in Firestore
    fun updateFoodItem(newItem: ProteinCostData, action: Action) {
        Log.d("SharedViewModel", "newItem: $newItem")

        val docRef = collectionRef.document(newItem.id)

        if (action == Action.UPDATE) {
            // Update specific fields in the document
            docRef.update(
                "foodSource", newItem.foodSource,
                "servings", newItem.servings,
                "grams", newItem.grams,
                "price", newItem.price,
                "calories", newItem.calories
            )
                .addOnSuccessListener {
                    Log.d("Firestore", "DocumentSnapshot successfully updated!")
                    loadSavedData()
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error updating document", e)
                }
        } else if (action == Action.ADD) {
            docRef.set(newItem)
                .addOnSuccessListener {
                    Log.d("Firestore", "DocumentSnapshot successfully written!")
                    loadSavedData()
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error writing document", e)
                }
        }
        else {
            // Delete the document if it exists
            docRef.delete()
                .addOnSuccessListener {
                    Log.d("Firestore", "DocumentSnapshot successfully deleted!")
                    loadSavedData()
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error deleting document", e)
                }
        }
    }

    // Function to load saved data from Firestore
    fun loadSavedData() {
        collectionRef.get()
            .addOnSuccessListener { result ->
                val dataList = result.mapNotNull { document ->
                    document.toObject(ProteinCostData::class.java)
                }
                val sortedData = dataList.sortedBy { it.foodSource }
                updateProteinCostList(sortedData)
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error getting documents.", e)
                _proteinCostLiveData.value = emptyList()
            }
    }

    // Update protein cost list
    private fun updateProteinCostList(newData: List<ProteinCostData>) {
        _proteinCostLiveData.value = newData
    }

    fun sortProteinCostList(sortAttribute: String, isAscending: Boolean) {
        val currentList = _proteinCostLiveData.value ?: return

        val sortedList = when (sortAttribute) {
            "Name" -> if (isAscending) currentList.sortedBy { it.foodSource } else currentList.sortedByDescending { it.foodSource }
            "Serv" -> if (isAscending) currentList.sortedBy { it.servings } else currentList.sortedByDescending { it.servings }
            "Grams" -> if (isAscending) currentList.sortedBy { it.grams } else currentList.sortedByDescending { it.grams }
            "Price" -> if (isAscending) currentList.sortedBy { it.price } else currentList.sortedByDescending { it.price }
            "Cals" -> if (isAscending) currentList.sortedBy { it.calories } else currentList.sortedByDescending { it.calories }
            else -> currentList
        }

        _proteinCostLiveData.value = sortedList
    }
}



//package com.proNutriLog.proteinCalculator
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import org.json.JSONObject
//import java.io.File
//import java.io.FileOutputStream
//import java.io.OutputStreamWriter
//
//class ProteinCostViewModel : ViewModel() {
//
//    // Define a LiveData for the list of ProteinCostData
//    private val _proteinCostLiveData = MutableLiveData<List<ProteinCostData>>()
//    val proteinCostLiveData: LiveData<List<ProteinCostData>> get() = _proteinCostLiveData
//
//    // Function to save or update food items in SharedPreferences
//    fun updateFoodItem(context: Context, newItem: ProteinCostData, edit: Boolean) {
//        Log.d("SharedViewModel", "newItem: $newItem")
//        // Retrieve the existing items from SharedPreferences
//        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val json = sharedPrefs.getString("proteinCostList", null)
//        // Define the type token for List<ProteinCostData>
//        val type = object : TypeToken<List<ProteinCostData>>() {}.type
//
//        // Deserialize JSON to List<ProteinCostData> or create an empty list if no data
//        val existingItems: MutableList<ProteinCostData> = if (json != null) {
//            // Deserialize the JSON to List<ProteinCostData>
//            Gson().fromJson(json, type)
//        } else {
//            mutableListOf()
//        }
//
//        // Determine whether to update, add, or remove the item
//        if (edit) {
//            val index = existingItems.indexOfFirst { it.id == newItem.id }
//            if (index != -1) {
//                existingItems[index] = newItem
//            }
//        } else {
//            val index = existingItems.indexOfFirst { it.id == newItem.id }
//            if (index != -1) {
//                existingItems.removeAt(index)
//            } else {
//                existingItems.add(newItem)
//            }
//        }
//
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
//
//
//    // Function to load saved data from SharedPreferences
//    private fun loadSavedDataFromSharedPreferences(context: Context): List<ProteinCostData> {
//        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val json = sharedPrefs.getString("proteinCostList", null)
//        val type = object : TypeToken<List<ProteinCostData>>() {}.type
//
//        return if (json != null) {
//            Gson().fromJson(json, type) ?: emptyList()
//        } else {
//            emptyList()
//        }
//    }
//
//    // Load data, sort by food source by default, and update LiveData
//    fun loadSavedData(context: Context) {
//        val data: List<ProteinCostData> = loadSavedDataFromSharedPreferences(context)
//        val sortedData = data.sortedBy { it.foodSource } // Capture the sorted list
//        updateProteinCostList(sortedData)
//    }
//
//    // Update protein cost list
//    private fun updateProteinCostList(newData: List<ProteinCostData>) {
//        _proteinCostLiveData.value = newData
//    }
//
//    fun sortProteinCostList(sortAttribute: String, isAscending: Boolean) {
//        val currentList = _proteinCostLiveData.value ?: return
//
//        val sortedList = when (sortAttribute) {
//            "Name" -> if (isAscending) currentList.sortedBy { it.foodSource } else currentList.sortedByDescending { it.foodSource }
//            "Serv" -> if (isAscending) currentList.sortedBy { it.servings } else currentList.sortedByDescending { it.servings }
//            "Grams" -> if (isAscending) currentList.sortedBy { it.grams } else currentList.sortedByDescending { it.grams }
//            "Price" -> if (isAscending) currentList.sortedBy { it.price } else currentList.sortedByDescending { it.price }
//            "Cals" -> if (isAscending) currentList.sortedBy { it.calories } else currentList.sortedByDescending { it.calories }
//            else -> currentList
//        }
//
//        _proteinCostLiveData.value = sortedList
//    }
//
//    fun saveSharedPreferencesToExternalStorage(context: Context) {
//        val sharedPreferences =
//            context.getSharedPreferences("your_prefs_name", Context.MODE_PRIVATE)
//        val allEntries = sharedPreferences.all
//
//        // Convert allEntries to JSON
//        val jsonObject = JSONObject(allEntries)
//
//        // Define the file path in external storage
//        val externalFilesDir = context.getExternalFilesDir(null) // or specify a directory
//        val file = File(externalFilesDir, "shared_prefs_backup.json")
//
//        // Write JSON data to the file
//        try {
//            val fileOutputStream = FileOutputStream(file)
//            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
//            outputStreamWriter.write(jsonObject.toString())
//            outputStreamWriter.close()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//}