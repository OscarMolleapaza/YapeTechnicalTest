package com.omolleapaza.yapetechnicaltest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omolleapaza.yapetechnicaltest.R
import com.omolleapaza.yapetechnicaltest.RecipeEntity

class RecipeAdapter(var recipes: List<RecipeEntity>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvLocationName: TextView = view.findViewById(R.id.tvLocationName)
        private val tvScore: TextView = view.findViewById(R.id.tvScore)
        private val imgRecipe: ImageView = view.findViewById(R.id.imgRecipe)

        fun bind(recipeEntity: RecipeEntity) {
            with(recipeEntity) {
                tvTitle.text = recipeEntity.title
                tvLocationName.text = recipeEntity.locationName
                tvScore.text = recipeEntity.score.toString()
            }
        }
    }

    fun update(recipes: List<RecipeEntity>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}