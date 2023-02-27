package com.example.mathquiz

import android.app.ActionBar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mathquiz.databinding.FragmentCountingBinding
import com.example.mathquiz.model.Fruit

class CountingFragment : Fragment() {

    private var _binding: FragmentCountingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCountingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.tableLayout.layoutParams = ViewGroup.LayoutParams(binding.tableLayout.width, (binding.tableLayout.width * 2) / 5)
        newGame()
    }

    private fun newGame() {
        val imageViews: MutableList<ImageView> = mutableListOf()
        imageViews.addAll(
            listOf(
                binding.fruitOne,
                binding.fruitTwo,
                binding.fruitThree,
                binding.fruitFour,
                binding.fruitFive,
                binding.fruitSix,
                binding.fruitSeven,
                binding.fruitEight,
                binding.fruitNine,
                binding.fruitTen,
            )
        )

        imageViews.forEach {
            it.visibility = View.GONE
        }

        val fruits: MutableList<Fruit> = mutableListOf()
        fruits.addAll(
            listOf(
                Fruit(R.string.banana, R.drawable.banana),
//                Fruit(R.string.cherry, R.drawable.cherry),
                Fruit(R.string.strawberry, R.drawable.strawberry),
                Fruit(R.string.lemon, R.drawable.lemon),
                Fruit(R.string.peach, R.drawable.peach)
            )
        )

        val fruit = fruits.random()

        binding.title.text = String.format(getString(R.string.counting_title), getString(fruit.stringResource))

        val number = (1..10).random()

        repeat(number) {
            val imageView: ImageView = imageViews.random()
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(fruit.drawableResource)
            imageViews.remove(imageView)
        }

        val buttons: MutableList<Button> = mutableListOf()
        buttons.addAll(
            listOf(
                binding.buttonOne,
                binding.buttonTwo,
                binding.buttonThree,
                binding.buttonFour,
                binding.buttonFive,
                binding.buttonSix,
                binding.buttonSeven,
                binding.buttonEight,
                binding.buttonNine,
                binding.buttonTen
            )
        )

        buttons.forEach {
            it.isClickable = true
        }

        for (button in buttons) {
            button.setOnClickListener {
                buttons.forEach { it.isClickable = false }

                if (button.text == number.toString()) {
                    button.background = resources.getDrawable(R.drawable.green_button_backgorund)
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            button.background = resources.getDrawable(R.drawable.gray_button_background)
                            newGame()
                        },
                        700 // value in milliseconds
                    )
                } else {
                    lateinit var tmpButton: Button
                    button.background = resources.getDrawable(R.drawable.red_button)
                    buttons.forEach {
                        if (it.text == number.toString()) {
                            it.background = resources.getDrawable(R.drawable.green_button_backgorund)
                            tmpButton = it
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            button.background = resources.getDrawable(R.drawable.gray_button_background)
                            tmpButton.background = resources.getDrawable(R.drawable.gray_button_background)
                            newGame()
                        },
                        1000 // value in milliseconds
                    )
                }
            }
        }
//
//        for (button in buttons) {
//            button.isClickable = true
//            button.background = resources.getDrawable(R.drawable.blue_button_background)
//
//            button.setOnClickListener {
//                if (button.text == number.toString()) {
//                    newGame()
//                } else {
//                    button.background = resources.getDrawable(R.drawable.red_button)
//                    button.isClickable = false
//                }
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
