package com.gian.viapathtest.ui.ui.main

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gian.viapathtest.R
import com.gian.viapathtest.databinding.FragmentRecipeDetailBinding
import com.gian.viapathtest.ui.viewmodel.Resource
import com.squareup.picasso.Picasso

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private var id_recipe: Int = 0
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        id_recipe = arguments?.getInt(ID)?:0
        page = arguments?.getInt(ARG_SECTION_NUMBER)?: 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        val root = binding.root

        if (page == 1){
            binding.image.isVisible = true
            binding.summary.isVisible = true
            binding.instructions.isGone = true
            binding.ingredients.isGone = true
        }
        if (page == 2){
            binding.image.isVisible = true
            binding.summary.isGone = true
            binding.instructions.isGone = true
            binding.ingredients.isVisible = true
        }
        if (page == 3){
            binding.instructions.isVisible = true
            binding.image.isVisible = true
            binding.summary.isGone = true
            binding.ingredients.isGone = true
        }

        pageViewModel.text.observe(viewLifecycleOwner) {

        }

        pageViewModel.recipe.observe(viewLifecycleOwner){ resource ->
            when (resource) {
                is Resource.Loading -> { }
                is Resource.Success -> {
                    binding.summary.text = Html.fromHtml(resource.data.summary)
                    binding.instructions.text = Html.fromHtml(resource.data.instructions)
                    val ingredientList = resource.data.ingredientList.map { it.title }
                    binding.ingredients.text = (ingredientList.toString())
                    Picasso.get().load(resource.data.imageUrl).resize(400, 400)
                        .centerCrop().error(R.drawable.viapath_technologies_logo)
                        .into(binding.image)
                    //showRecipes(resource.data)
                }

                is Resource.Error -> {


                }
            }
        }
        pageViewModel.getRecipeInformation(id_recipe)

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private val ID = "id_recipe"
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, id_recipe:Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putInt(ID, id_recipe)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}