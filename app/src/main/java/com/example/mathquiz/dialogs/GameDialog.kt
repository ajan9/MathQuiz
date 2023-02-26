package com.example.mathquiz.dialogs

import android.content.Context
import android.os.Bundle
import com.example.mathquiz.databinding.AlertDialogBinding


class GameDialog(
    context: Context,
    private val title: String,
    private val firstOption: String?,
    private val secondOption: String?,
    private val listener: DialogInteraction
) : BaseDialog(context) {
    private var bindingSetup: AlertDialogBinding? = null

    private val binding get() = bindingSetup!!

    companion object {
        @Volatile
        private var INSTANCE: GameDialog? = null

        @Synchronized
        fun getInstance(
            context: Context,
            title: String,
            firstOption: String?,
            secondOption: String?,
            listener: DialogInteraction
        ): GameDialog = INSTANCE
            ?: GameDialog(
                context,
                title,
                firstOption,
                secondOption,
                listener
            ).also { INSTANCE = it }.also {
                it.window?.setBackgroundDrawableResource(android.R.color.transparent)
                it.show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSetup = AlertDialogBinding.inflate(layoutInflater)
        val view = bindingSetup!!.root
        setContentView(view)

        initViews()
    }

    private fun initViews() {
        binding.tvGameResult.text = title
        binding.btnFirstOption.text = firstOption
        binding.btnSecondOption.text = secondOption

        binding.btnFirstOption.setOnClickListener {
            listener.onFirstOptionClicked()
            dismiss()
        }

        binding.btnSecondOption.setOnClickListener {
            listener.onSecondOptionClicked()
            dismiss()
        }
    }

    override fun onDetachedFromWindow() {
        INSTANCE = null
    }

    override fun onBackPressed() {
        dismiss()
    }
}
