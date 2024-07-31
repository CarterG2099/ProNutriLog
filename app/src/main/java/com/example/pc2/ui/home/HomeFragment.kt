//package com.example.pc2.ui.home
//
//import ProteinCostData
//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
//import com.example.pc2.SharedViewModel
//import com.example.pc2.databinding.FragmentHomeBinding
//
//class HomeFragment : Fragment() {
//
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var sharedPreferences: SharedPreferences // Declare sharedPreferences
//    private lateinit var food: String
//    private lateinit var servings: String
//    private lateinit var grams: String
//    private lateinit var price: String
//    private lateinit var calText: String
//    private var calPercent: Double = 0.0
//    private var unitCost: Double = 0.0
//    private var costPer50: Double = 0.0
//    private val infoList = mutableListOf<String>()
//    private val TAG = "HomeFragment"
//    private val sharedViewModel: SharedViewModel by activityViewModels()
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Initialize ViewModel and SharedPreferences
//        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//
//        //Implement save Button
//        val saveButton: Button = binding.saveButton
//        saveButton.setOnClickListener {
//            if (binding.foodDisplay.text.isBlank() || binding.servingsDisplay.text.isBlank() || binding.gramsDisplay.text.isBlank() || binding.priceDisplay.text.isBlank() || binding.unitCostDisplay.text.isBlank() || binding.costPer50Display.text.isBlank() || binding.caloriesDisplay.text.isBlank()) {
//                val costPer50 = "Please Provide Valid Information"
//                val unitCost = "Please Provide Valid Information"
//                binding.costPer50Display.text = costPer50
//                binding.unitCostDisplay.text = unitCost
//            } else {
//                //Save data to SharedViewModel through ProteinCostList
//                saveData()
//                //Save data to shared preferences
//                sharedViewModel.updateDataToSharedPreferences(requireContext(), sharedViewModel.proteinCostList)
//                //Tell user the data has been saved
//                Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        //Implement Calc Button
//        val calcButton = binding.calcButton // Replace with the ID of your calc button
//        calcButton.setOnClickListener{ calc() } //call calc function
//
//        //Implement Clear Button
//        val clearButton: Button = binding.clearButton
//        clearButton.setOnClickListener {
//            binding.foodDisplay.text.clear()
//            binding.servingsDisplay.text.clear()
//            binding.gramsDisplay.text.clear()
//            binding.priceDisplay.text.clear()
//            binding.caloriesDisplay.text.clear()
//            binding.unitCostDisplay.text = ""
//            binding.costPer50Display.text = ""
//            Toast.makeText(requireContext(), "Cleared", Toast.LENGTH_SHORT).show()
//
//        }
//    }
//
//
//
//    //Function to save data to proteincostlist
//    private fun saveData() {
//        food = binding.foodDisplay.text.toString()
//        servings = binding.servingsDisplay.text.toString()
//        grams = binding.gramsDisplay.text.toString()
//        price = binding.priceDisplay.text.toString()
//        val calPercentString: String = String.format("%.0f", calPercent)
//        val unitCostString: String = String.format("%.2f", unitCost)
//        val costPer50String: String = String.format("%.2f", costPer50)
//        // Update the proteinCostList in the SharedViewModel
//        val newData = mutableListOf(ProteinCostData(food, servings, grams, price, costPer50String, unitCostString, calPercentString, calText))
//        sharedViewModel.updateProteinCostList(newData)
//    }
//
//    //Calculator function
//   private fun calc() {
//        val servingsText = binding.servingsDisplay.text.toString()
//        val gramsText = binding.gramsDisplay.text.toString()
//        val priceText = binding.priceDisplay.text.toString()
//        calText = binding.caloriesDisplay.text.toString()
//
//        if (servingsText.isBlank() || gramsText.isBlank() || priceText.isBlank() || calText.isBlank()) {
//            val costPer50 = "Please Provide Valid Information"
//            val unitCost = "Please Provide Valid Information"
//            binding.costPer50Display.text = costPer50
//            binding.unitCostDisplay.text = unitCost
//            return
//        }
//
//        val s: Double = servingsText.toDoubleOrNull() ?: 0.0
//        val g: Double = gramsText.toDoubleOrNull() ?: 0.0
//        val p: Double = priceText.toDoubleOrNull() ?: 0.0
//        val cal: Double = calText.toDoubleOrNull() ?: 0.0
//        unitCost = (p / (g * s)) * 100
//        costPer50 = (p / (g * s)) * 50
//        calPercent = (((g * 4) / cal) * 100)
//        val  price50 = "50g: $" + String.format("%.2f", costPer50)
//        val  unit = "1g: " + String.format("%.2f", unitCost) + " Cents"
//        binding.costPer50Display.text = price50
//        binding.unitCostDisplay.text = unit
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
