package com.gian.viapathtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.gian.viapathtest.databinding.ActivityRecipeDetailBinding
import com.gian.viapathtest.ui.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var recipeId = intent.getIntExtra("RECIPE_ID", 0)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,recipeId)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

    }
}