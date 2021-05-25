package com.example.calorie_vault.data.mealdata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calorie_vault.databinding.ItemMealBinding

/**
 * Use a ListAdapter when receiving a flow of lists, which our database is sending. Completely
 * new list. Not single items. Calculates difference between old and new list items and updates.
 * Done in background thread.
 */

class MealsAdapter : ListAdapter<Meal, MealsAdapter.MealViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    /**
     * Instead of using a lot of findViewById's, we use viewBinding. Searches XML file
     * and finds the assets.
     */
    class MealViewHolder(private val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {

        // put the data into the views inside the layout
        fun bind(meal: Meal) {
            binding.apply {
                textViewMealName.text = meal.meal
                textViewMealDescription.text = meal.description
                textViewMealCalories.text = meal.calories.toString()
            }
        }
    }


    /**
     * Use this DiffCallBack to differentiate new items in the RecyclerView from old ones.
     * This is what the ListAdapter uses to make its calculations.
     */
    class DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
    }
}