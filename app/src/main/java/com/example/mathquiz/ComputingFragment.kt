package com.example.mathquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mathquiz.databinding.FragmentComputingBinding
import com.example.mathquiz.dialogs.DialogInteraction
import com.example.mathquiz.dialogs.GameDialog

class ComputingFragment : Fragment() {

    private var _binding: FragmentComputingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComputingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newGame()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newGame() {
        val xArray: MutableList<Int> = mutableListOf()
        for (i in 1..10) {
            xArray.add(i)
        }
        val yArray: MutableList<Int> = mutableListOf()
        for (i in 0..10) {
            yArray.add(i)
        }
        val signArray: MutableList<String> = mutableListOf("+", "-")

        val x = xArray.random()
        val y = (0 until x).random()
        val sign = signArray.random()

        binding.tvX.text = x.toString()
        binding.tvY.text = y.toString()
        binding.tvSign.text = sign.random().toString()

        binding.btnCheckResult.setOnClickListener {
            if (binding.etResult.text!!.isNotEmpty()) {
                val result = binding.etResult.text.toString().toInt()
                if (checkResult(x, y, result, sign)) {
                    showDialog(getString(R.string.congrats), getString(R.string.next), true)
                } else {
                    showDialog(
                        getString(R.string.try_again),
                        getString(R.string.try_again_game),
                        false
                    )
                }
            } else {
                binding.etResult.error = getString(R.string.enter_number)
            }

        }
    }

    private fun checkResult(x: Int, y: Int, result: Int, sign: String): Boolean {
        return if (sign == "+") {
            x + y == result
        } else {
            x - y == result
        }
    }

    private fun showDialog(title: String, secondOption: String, winner: Boolean) {
        GameDialog.getInstance(requireActivity(),
            title,
            getString(R.string.quit),
            secondOption,
            object : DialogInteraction {
                override fun onFirstOptionClicked() {
                    findNavController().navigate(R.id.action_computingFragment_to_menuFragment)
                }

                override fun onSecondOptionClicked() {
                    if (winner) {
                        binding.etResult.text!!.clear()
                        newGame()
                    } else {
                        binding.etResult.text!!.clear()
                    }
                }
            }).show()
    }
}