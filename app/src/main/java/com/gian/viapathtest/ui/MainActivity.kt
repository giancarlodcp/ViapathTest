package com.gian.viapathtest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gian.viapathtest.data.model.RecipeResult
import com.gian.viapathtest.databinding.ActivityMainBinding
import com.gian.viapathtest.ui.adapter.RecipeListAdapter
import com.gian.viapathtest.ui.viewmodel.RecipeListViewModel
import com.gian.viapathtest.ui.viewmodel.Resource
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), RecipeListAdapter.RecipeClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeListAdapter: RecipeListAdapter
    private lateinit var viewModel: RecipeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeListAdapter = RecipeListAdapter(this)
        binding.recyclerView.adapter = recipeListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // Init viewmodel
        viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)


        with(viewModel){
            recipes.observe(this@MainActivity) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    //TODO
                    }
                    is Resource.Success -> {
                        recipeListAdapter.submitList(resource.data)
                    }

                    is Resource.Error -> {
                        //TODO
                    }
                }
            }

            errorMessage.observe(this@MainActivity) { message ->
                Snackbar.make(binding.recyclerView, message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchRecipes(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    override fun onRecipeClick(recipe: RecipeResult) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("RECIPE_ID", recipe.id)
        startActivity(intent)
    }
}