package com.omolleapaza.yapetechnicaltest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import com.omolleapaza.yapetechnicaltest.data.remote.RecipeRemoteDataSource
import com.omolleapaza.yapetechnicaltest.data.remote.RemoteApi
import com.omolleapaza.yapetechnicaltest.databinding.FragmentDetailBinding
import com.omolleapaza.yapetechnicaltest.extensions.createFactory
import com.omolleapaza.yapetechnicaltest.viewmodel.MainViewModel
import kotlinx.serialization.json.Json


class DetailFragment : Fragment() {

    lateinit var _binding: FragmentDetailBinding


    private val args: DetailFragmentArgs by navArgs()


    private val recipeRepository: RecipeRepository by lazy {
        RecipeRemoteDataSource(RemoteApi(), Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        })
    }

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel(recipeRepository).createFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        _binding.viewModel =viewModel
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.setupData(args.recipeUI)
        Glide.with(view).load(args.recipeUI.imageUrl).into(_binding.imageView)
        _binding.executePendingBindings()

        _binding.button.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToUbicationFragment(args.recipeUI)
            view.findNavController().navigate(action)
        }

    }


}