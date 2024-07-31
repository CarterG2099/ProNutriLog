//package com.example.pc2.ui.foods
//
//import ProteinCostData
//import com.example.pc2.ProteinCostAdapter
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.Toast
//import androidx.compose.runtime.Composable
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.ItemTouchHelper
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.pc2.EditItemDialogFragment
//import com.example.pc2.MealCostData
//import com.example.pc2.databinding.FragmentFoodsBinding
//import com.example.pc2.R
//import com.example.pc2.SharedViewModel
//import com.example.pc2.SharedViewModelFactory
//
//
//class DashboardFragment : Fragment(){
//
//    private var _binding: FragmentFoodsBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var proteinCostAdapter: ProteinCostAdapter
//    private val TAG = "DashboardFragment"
//    private lateinit var sharedViewModel: SharedViewModel
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentFoodsBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        recyclerView = binding.recyclerView // Use the recyclerView from the binding
//        sharedViewModel = ViewModelProvider(this, SharedViewModelFactory()).get(SharedViewModel::class.java)
//
//        // Load data from SharedPreferences and save it to Protein Cost List
//        sharedViewModel.updateProteinCostListFromSharedPreferences(requireContext())
//
//
//        // Create and set up the adapter
//        proteinCostAdapter = ProteinCostAdapter(sharedViewModel.proteinCostList)
//        recyclerView.adapter = proteinCostAdapter
//
//        // Set up GridLayoutManager with 2 columns
//        val layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView.layoutManager = layoutManager
//
//        // Set up ItemTouchHelper for swipe actions
////        setupItemTouchHelper()
//
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //Observe the changes made to ProteinCostLiveData
//        sharedViewModel.proteinCostLiveData.observe(viewLifecycleOwner) { newList: List<ProteinCostData> ->
//            // Update the RecyclerView adapter with the new list
//            Log.d(TAG, "PC Live Data ObserverCalled, $newList")
//            proteinCostAdapter.updateData(newList.toMutableList())
//        }
//
//        //Delete button
//        val deleteSelectedButton: Button = binding.deleteSelectedButton
//        deleteSelectedButton.setOnClickListener {
//            val selectedItems = mutableListOf<ProteinCostData>()
//                for (item in sharedViewModel.proteinCostList) {
//                    if (item.isSelected) {
//                        selectedItems.add(item)
//                    }
//                }
//            //Remove items from the protein Cost List
//            sharedViewModel.remove(selectedItems)
//            //Update Shared Preferences to match the new Protein Cost List
//            sharedViewModel.replaceDataToSharedPreferences(requireContext(), sharedViewModel.proteinCostList)
//            //Notify adapter of the change
//            proteinCostAdapter.notifyDataSetChanged()
//
//        }
//
//        //Implement Edit Button
//        val editButton: Button = binding.editButton
//        editButton.setOnClickListener{
//            val selectedItems = mutableListOf<ProteinCostData>()
//            for (item in sharedViewModel.proteinCostList) {
//                if (item.isSelected) {
//                    selectedItems.add(item)
//                }
//            }
//            // Check if selectedItems is empty
//            if (selectedItems.size != 1) {
//                Toast.makeText(requireContext(), "Please Select One Item", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            val selectedItem = selectedItems[0] // Assuming you want to edit the first selected item
//            val editDialog = EditItemDialogFragment()
//            editDialog.setSharedViewModel(sharedViewModel)
//            editDialog.setSelectedItem(selectedItem) // Pass the selected item to the dialog
//            editDialog.setProteinCostAdapter(proteinCostAdapter) // Pass the adapter to the dialog
//            editDialog.show(requireActivity().supportFragmentManager, "EditDialog")
//
//        }
//
//        //Implement Sort Button
//        val sortButtons = listOf(
//            binding.FoodButton, binding.CostPer50Button, binding.CostPer1Button,
//            binding.ServingCostButton, binding.ServingsButton, binding.GramsButton,
//            binding.PriceButton, binding.CalPercentButton
//        )
//
//        for (button in sortButtons) {
//            button.setOnClickListener {
//                handleSortClick(it.id)
//            }
//        }
//
//        //Implement Add Button
//        val addButton: Button = binding.addFoodButton
//        addButton.setOnClickListener{
//            val selectedItems = mutableListOf<ProteinCostData>()
//            for (item in sharedViewModel.proteinCostList)
//                if(item.isSelected) {
//                    selectedItems.add(item)
//                }
//            val mealName = ""
//            val newMeal = MealCostData(mealName, selectedItems)
//            }
//    }
//
//    private fun handleSortClick(buttonId: Int) {
//        when (buttonId) {
//            R.id.FoodButton -> sharedViewModel.foodSort()
//            R.id.CostPer50Button -> sharedViewModel.costPer50Sort()
//            R.id.CostPer1Button -> sharedViewModel.unitCostSort()
//            R.id.ServingCostButton -> sharedViewModel.servingCostSort()
//            R.id.ServingsButton -> sharedViewModel.servingsSort()
//            R.id.GramsButton -> sharedViewModel.gramSort()
//            R.id.PriceButton -> sharedViewModel.priceSort()
//            R.id.CalPercentButton -> sharedViewModel.calPercentSort()
//        }
//        proteinCostAdapter.notifyDataSetChanged()
//    }
//
//}
//
