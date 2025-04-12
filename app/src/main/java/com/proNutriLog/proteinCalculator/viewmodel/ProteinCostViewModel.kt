package com.proNutriLog.proteinCalculator.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.proNutriLog.proteinCalculator.Action
import com.proNutriLog.proteinCalculator.data.model.ProteinCostData
import com.proNutriLog.proteinCalculator.data.remote.RetrofitInstance
import com.proNutriLog.proteinCalculator.data.repository.KrogerRepository

class ProteinCostViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _proteinCostLiveData = MutableLiveData<List<ProteinCostData>>()
    val proteinCostLiveData: LiveData<List<ProteinCostData>> get() = _proteinCostLiveData

    private val collectionRef = db.collection("proteinCostData")

    // Function to save or update food items in Firestore
    fun updateFoodItem(foodItem: ProteinCostData, action: Action) {
        Log.d("SharedViewModel", "foodItem: $foodItem")

        val docRef = collectionRef.document(foodItem.id)
        Log.d("SharedViewModel", "docRef: $docRef")

        if (action == Action.UPDATE) {
            // Update specific fields in the document
            docRef.update(
                "foodSource", foodItem.foodSource,
                "servings", foodItem.servings,
                "grams", foodItem.grams,
                "price", foodItem.price,
                "calories", foodItem.calories
            )
                .addOnSuccessListener {
                    Log.d("Firestore", "DocumentSnapshot successfully updated!")
                    loadSavedData()
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error updating document", e)
                }
        } else if (action == Action.ADD) {
            docRef.set(foodItem)
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