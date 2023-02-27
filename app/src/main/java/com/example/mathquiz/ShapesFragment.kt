package com.example.mathquiz

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mathquiz.databinding.FragmentShapesBinding
import com.example.mathquiz.dialogs.DialogInteraction
import com.example.mathquiz.dialogs.GameDialog
import com.example.mathquiz.model.Shape

class ShapesFragment : Fragment() {

    private var _binding: FragmentShapesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShapesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newGame()
    }

    private fun newGame() {

        val shapes: MutableList<Shape> = mutableListOf()
        shapes.addAll(
            listOf(
                Shape("kvadrat", R.drawable.kvadrat),
                Shape("pravokutnik", R.drawable.pravokutnik),
                Shape("trokut", R.drawable.trokut),
                Shape("krug", R.drawable.krug),
                Shape("elipsa", R.drawable.elipsa),
                Shape("peterokut", R.drawable.peterokut),
                Shape("romb", R.drawable.romb),
            )
        )

        val shape = shapes.random()

        val imageView: ImageView = binding.shape
        imageView.setImageResource(shape.drawableResource)

        val buttons: MutableList<Button> = mutableListOf()
        buttons.addAll(
            listOf(
                binding.button1,
                binding.button2,
                binding.button3,
            )
        )

        buttons.forEach { it.isClickable = true }

        buttons.shuffle()
        buttons[0].text = shapes.random().stringResource
        buttons[2].text = shape.stringResource
        while (buttons[0].text.equals(buttons[2].text)){
            buttons[0].text = shapes.random().stringResource
        }
        buttons[1].text = shapes.random().stringResource
        while (buttons[1].text.equals(buttons[0].text) || buttons[1].text.equals(buttons[2].text)){
            buttons[1].text = shapes.random().stringResource
        }


        for (button in buttons) {
            button.setOnClickListener {
                buttons.forEach { it.isClickable = false }

                if (button.text.equals(shape.stringResource)) {
                    button.setBackgroundColor(resources.getColor(R.color.green))
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            button.setBackgroundColor(resources.getColor(R.color.light))
                            newGame()
                        },
                        700 // value in milliseconds
                    )
                } else {
                    button.setBackgroundColor(resources.getColor(R.color.error_red))
                    buttons[2].setBackgroundColor(resources.getColor(R.color.green))
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            button.setBackgroundColor(resources.getColor(R.color.light))
                            buttons[2].setBackgroundColor(resources.getColor(R.color.light))
                            newGame()
                        },
                        1000 // value in milliseconds
                    )
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}