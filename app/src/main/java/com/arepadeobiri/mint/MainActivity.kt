package com.arepadeobiri.mint

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.arepadeobiri.mint.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MintViewModel>()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


//        viewModel.cardDetails.observe(this, {
//
//            when (it) {
//                is MintViewModel.Response.Error -> {
//
//                }
//                is MintViewModel.Response.Success -> {
//                    Snackbar.make(
//                        binding.root,
//                        "The card make is ${it.response.scheme?.capitalize(Locale.getDefault())}",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//            }
//
//        })



        binding.cvvEditText.setOnEditorActionListener { textView, id, keyEvent ->
            if ((keyEvent != null && (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) || (id == EditorInfo.IME_ACTION_DONE)) {
                //do what you want on the press of 'done'
                when (viewModel.cardDetails.value) {
                    is MintViewModel.Response.Error -> {
                        Snackbar.make(
                            binding.root,
                            (viewModel.cardDetails.value as MintViewModel.Response.Error).message,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    is MintViewModel.Response.Success -> {
                        binding.apply {
                            (viewModel.cardDetails.value as MintViewModel.Response.Success).response.also {
                                brand.text = "Brand: ${it.brand}"
                                bank.text = "Bank: ${it.bank?.name}"
                                type.text = "Type: ${it.type}"
                                country.text = "Country:  ${it.country?.name}"

                            }


                        }
                    }
                    null -> {
                    }

                }
            }
            return@setOnEditorActionListener false
        }



        binding.digitsEditText.addTextChangedListener(CreditCardWatcher {
            try {
                viewModel.getCardDetails(it.filter { it != ' ' })
            } catch (t: Throwable) {

            }
        })





        binding.firstMonthDigitEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.firstMonthDigitEditText.text.isNullOrBlank()) {
                    return
                } else {
                    if (binding.firstMonthDigitEditText.text.toString().toInt() > 1) {
                        binding.firstMonthDigitEditText.setText("")
                    } else {
                        binding.secondMonthDigitEditText.requestFocus()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })




        binding.secondMonthDigitEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.secondMonthDigitEditText.text.isNullOrBlank()) {
                    return
                } else {
                    binding.firstYearDigitEditText.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })




        binding.firstYearDigitEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.firstYearDigitEditText.text.isNullOrBlank()) {
                    return
                } else {
                    binding.secondYearDigitEditText.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })




        binding.secondYearDigitEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.secondYearDigitEditText.text.isNullOrBlank()) {
                    return
                } else {
                    binding.cvvEditText.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })


    }
}