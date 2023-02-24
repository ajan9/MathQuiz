package com.example.mathquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mathquiz.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonShapes.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_shapesFragment)
        }

        binding.buttonCounting.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_countingFragment)
        }

        binding.buttonComputing.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_computingFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}