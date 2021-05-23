package com.example.calorie_vault.data

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calorie_vault.databinding.ItemMealBinding

// use a ListAdapter when receiving a "flow" of lists, which our database is sending. Completely new
// list. Not single items. Calculates difference between old and new list and updates the list;
// done in the background thread.
class MealsAdapter : ListAdapter<Meal, MealsAdapter.MealViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    // Instead of using a lot of "findViewByID's, we use viewBinding here. It searches the
    // XML file and finds the assets itself.
    //ViewHolder is how the views interact with the data.
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


    // ListAdapter doesn't know how to directly tell the difference between a new list and an old
    // list, so it needs this callback to determine that.
    class DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
    }
}