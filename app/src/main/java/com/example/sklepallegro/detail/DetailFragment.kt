package com.example.sklepallegro.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sklepallegro.databinding.DetailFragmentBinding

/**
 *This [Fragment] will show the detailed information about a selected allegro offer
 */
class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater    )
        binding.setLifecycleOwner(this)
        return binding.root
    }
}
