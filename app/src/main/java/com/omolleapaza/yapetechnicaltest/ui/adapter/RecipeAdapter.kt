package com.omolleapaza.yapetechnicaltest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omolleapaza.yapetechnicaltest.R
import com.omolleapaza.yapetechnicaltest.RecipeEntity
import com.omolleapaza.yapetechnicaltest.databinding.ItemRecipeBinding
import com.omolleapaza.yapetechnicaltest.model.RecipeModel

class RecipeAdapter(var recipes: List<RecipeEntity>, private val listener: (RecipeModel) -> Unit) :
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
                Glide.with(itemView).load(recipeEntity.imageUrl).into(imgRecipe)
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

        val item = RecipeModel(
            title = recipes[position].title,
            imageUrl = recipes[position].imageUrl,
            description = recipes[position].description,
            author = recipes[position].author,
            latitude = recipes[position].latitude,
            longitude = recipes[position].longitude,
            locationName = recipes[position].locationName,
            score = recipes[position].score
        )

        holder.itemView.rootView.setOnClickListener {
            listener.invoke(item)
        }

    }

    override fun getItemCount(): Int = recipes.size


}