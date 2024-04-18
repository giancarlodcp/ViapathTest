package com.gian.viapathtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gian.viapathtest.R
import com.gian.viapathtest.data.model.RecipeResult
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val listener: RecipeClickListener) :
    ListAdapter<RecipeResult, RecipeListAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val titleTextView: TextView = itemView.findViewById(R.id.recipe_text)
        private val image: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(recipe: RecipeResult) {
            titleTextView.text = recipe.title
            Picasso.get().load(recipe.imageUrl).resize(400, 400)
                .centerCrop().error(R.drawable.viapath_technologies_logo)
                .into(image)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val recipe = getItem(position)
                listener.onRecipeClick(recipe)
            }
        }
    }

    interface RecipeClickListener {
        fun onRecipeClick(recipe: RecipeResult)
    }

    class RecipeDiffCallback : DiffUtil.ItemCallback<RecipeResult>() {
        override fun areItemsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem == newItem
        }
    }
}