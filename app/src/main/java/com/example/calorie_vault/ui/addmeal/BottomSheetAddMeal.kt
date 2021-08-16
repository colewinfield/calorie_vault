package com.example.calorie_vault.ui.addmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calorie_vault.R
import com.example.calorie_vault.databinding.BottomSheetAddMealBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAddMeal : BottomSheetDialogFragment() {

    private val viewModel: BottomSheetAddMealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.bottom_sheet_add_meal, container, false)
        val binding = BottomSheetAddMealBinding.bind(view)

        binding.apply {

        }

        return view
    }


}