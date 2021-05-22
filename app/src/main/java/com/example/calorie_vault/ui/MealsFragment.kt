package com.example.calorie_vault.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.calorie_vault.R
import com.example.calorie_vault.data.MealsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment(R.layout.fragment_today) {

    private val viewModel: MealsViewModel by viewModels()



}