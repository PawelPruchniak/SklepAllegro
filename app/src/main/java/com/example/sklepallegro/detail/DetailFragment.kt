package com.example.sklepallegro.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.sklepallegro.databinding.DetailFragmentBinding

/**
 *This [Fragment] will show the detailed information about a selected allegro offer
 */
class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val offer = DetailFragmentArgs.fromBundle(arguments!!).selectedOffer

        // Setting title of ActionBar "offer.name"
        (activity as AppCompatActivity).supportActionBar?.title = offer.name

        val viewModelFactory = DetailViewModelFactory(offer, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}