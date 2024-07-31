//package com.example.pc2
//
//import ProteinCostData
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.fragment.app.DialogFragment
//import com.example.pc2.databinding.FragmentEditItemDialogBinding
//
//class EditItemDialogFragment : DialogFragment() {
//
//    private lateinit var binding: FragmentEditItemDialogBinding // Your generated binding class
//    private lateinit var sharedViewModel: SharedViewModel
//    private lateinit var selectedItem: ProteinCostData // Add this property
//    private lateinit var proteinCostAdapter: ProteinCostAdapter
//    private var calPercent: Double = 0.0
//    private var unitCost: Double = 0.0
//    private var costPer50: Double = 0.0
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentEditItemDialogBinding.inflate(inflater, container, false)
//        return binding.root    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val foodDisplay = view.findViewById<EditText>(R.id.foodDisplayEdit)
//        val servingsDisplay = view.findViewById<EditText>(R.id.servingsDisplayEdit)
//        val gramsDisplay = view.findViewById<EditText>(R.id.gramsDisplayEdit)
//        val priceDisplay = view.findViewById<EditText>(R.id.priceDisplayEdit)
//        val caloriesDisplay = view.findViewById<EditText>(R.id.caloriesDisplayEdit)
//
//        foodDisplay.setText(selectedItem.foodSource)
//        servingsDisplay.setText(selectedItem.servings)
//        gramsDisplay.setText(selectedItem.grams)
//        priceDisplay.setText(selectedItem.price)
//        caloriesDisplay.setText(selectedItem.calories)
//
//
//        val saveButton: Button = binding.saveButtonEdit // Replace with your actual save button ID
//        saveButton.setOnClickListener {
//
//            if (foodDisplay.text.isBlank() || servingsDisplay.text.isBlank() || gramsDisplay.text.isBlank() || priceDisplay.text.isBlank() || caloriesDisplay.text.isBlank()) {
//                Toast.makeText(requireContext(), "Incomplete Information", Toast.LENGTH_SHORT).show()
//
//            } else {
//                calc()
//                // Save data to SharedViewModel through ProteinCostList
//                saveData()
//                // Save data to shared preferences
//                sharedViewModel.replaceDataToSharedPreferences(requireContext(), sharedViewModel.proteinCostList)
//                // Tell user the data has been saved
//                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
//                //Notify adapter of the change
//                proteinCostAdapter.notifyDataSetChanged()
//                // Close the dialog
//                dismiss()
//            }
//        }
//
//        //Dismiss the edit pop up
//        val cancelButton: Button = binding.cancelButton
//        cancelButton.setOnClickListener{
//            dismiss()
//        }
//    }
//
//    //Function to save data to proteincostlist
//    private fun saveData() {
//        selectedItem.foodSource = binding.foodDisplayEdit.text.toString()
//        selectedItem.servings = binding.servingsDisplayEdit.text.toString()
//        selectedItem.grams = binding.gramsDisplayEdit.text.toString()
//        selectedItem.price = binding.priceDisplayEdit.text.toString()
//        selectedItem.cal = String.format("%.0f", calPercent)
//        selectedItem.oneGram = String.format("%.2f", unitCost)
//        selectedItem.fiftyGrams = String.format("%.2f", costPer50)
//        selectedItem.servingCost = String.format("%.2f", selectedItem.price.toDoubleOrNull()?.div(selectedItem.servings.toDoubleOrNull()!!) ?: 0.0
//        )
//    }
//
//    //Calculator function
//    private fun calc() {
//        val servingsText = binding.servingsDisplayEdit.text.toString()
//        val gramsText = binding.gramsDisplayEdit.text.toString()
//        val priceText = binding.priceDisplayEdit.text.toString()
//        val calText = binding.caloriesDisplayEdit.text.toString()
//
//        if (servingsText.isBlank() || gramsText.isBlank() || priceText.isBlank() || calText.isBlank()) {
//            Toast.makeText(requireContext(), "Incomplete Information", Toast.LENGTH_SHORT).show()
//        }
//
//        val s: Double = servingsText.toDoubleOrNull() ?: 0.0
//        val g: Double = gramsText.toDoubleOrNull() ?: 0.0
//        val p: Double = priceText.toDoubleOrNull() ?: 0.0
//        val cal: Double = calText.toDoubleOrNull() ?: 0.0
//        unitCost = (p / (g * s)) * 100
//        costPer50 = (p / (g * s)) * 50
//        calPercent = (((g * 4) / cal) * 100)
//    }
//
//    fun setSharedViewModel(sharedViewModel: SharedViewModel) {
//        this.sharedViewModel = sharedViewModel
//    }
//    fun setProteinCostAdapter(adapter: ProteinCostAdapter) {
//        proteinCostAdapter = adapter
//    }
//    fun setSelectedItem(item: ProteinCostData) {
//        selectedItem = item
//    }
//}
