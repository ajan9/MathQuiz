package com.example.mathquiz

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
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
                Fruit(R.string.strawberry, R.drawable.strawberry),
                Fruit(R.string.cherry, R.drawable.cherry),
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
        for (button in buttons) {
            button.isClickable = true
            button.background = resources.getDrawable(R.drawable.blue_button_background)

            button.setOnClickListener {
                if (button.text == number.toString()) {
                    newGame()
                } else {
                    button.background = resources.getDrawable(R.drawable.red_button)
                    button.isClickable = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
