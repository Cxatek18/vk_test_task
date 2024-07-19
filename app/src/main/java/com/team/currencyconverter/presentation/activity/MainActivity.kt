package com.team.currencyconverter.presentation.activity

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.team.currencyconverter.app.App
import com.team.currencyconverter.databinding.ActivityMainBinding
import com.team.currencyconverter.presentation.viewModel.MainViewModel
import com.team.currencyconverter.presentation.viewModel.MainViewModelFactory
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var vmFactory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var listNameCurrency: List<String>

    private lateinit var listNameCurrencyConvert: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        enableEdgeToEdge()
        setContentView(binding.root)
        viewModel.getCurrencyItemList()
        observeViewModel()
        listenerOnClickBtnConvert()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.currencyItemList.flowWithLifecycle(lifecycle)
                .collect { data ->
                    formingTitleListCurrencyInWhat(
                        data,
                        getString(
                            com.team.currencyconverter.R.string.text_currency_my
                        )
                    )
                    formingTitleListCurrencyConvert(
                        data,
                        getString(
                            com.team.currencyconverter.R.string.text_convert_spiner_first
                        )
                    )
                    createSpinner(
                        binding.currencyListChoiceInWhat,
                        listNameCurrency
                    )
                    createSpinner(
                        binding.currencyListChoiceConvert,
                        listNameCurrencyConvert
                    )
                }
        }

        lifecycleScope.launch {
            viewModel.currencyConvertString.flowWithLifecycle(lifecycle)
                .filter { it.isNotEmpty() }
                .collect { resultConvert ->
                    with(binding) {
                        convertResult.visibility = View.VISIBLE
                        val currencyResultTitle = currencyListChoiceConvert
                            .selectedItem
                            .toString()
                        convertResult.text = "Результат - $resultConvert $currencyResultTitle"
                    }
                }
        }
    }

    private fun checkIsEmptyCurrencyValue(): Boolean {
        val editTextCurrencyValue = binding.currencyValue.text.toString()
        if (editTextCurrencyValue.isEmpty()) {
            Toast.makeText(
                this,
                getString(
                    com.team.currencyconverter.R.string.text_error_empty_currency_value
                ),
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            return true
        }
    }

    private fun checkCurrencyInWhat(): Boolean {
        if (
            binding.currencyListChoiceInWhat.selectedItem.toString() == getString(
                com.team.currencyconverter.R.string.text_currency_my
            )
        ) {
            Toast.makeText(
                this,
                getString(
                    com.team.currencyconverter.R.string.text_error_currency_in_what
                ),
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            return true
        }
    }

    private fun checkCurrencyConvert(): Boolean {
        if (
            binding.currencyListChoiceConvert.selectedItem.toString() == getString(
                com.team.currencyconverter.R.string.text_convert_spiner_first
            )
        ) {
            Toast.makeText(
                this,
                getString(
                    com.team.currencyconverter.R.string.text_error_currency_convert
                ),
                Toast.LENGTH_LONG
            ).show()
            return false
        } else {
            return true
        }
    }

    private fun listenerOnClickBtnConvert() {
        with(binding) {
            btnStartConvert.setOnClickListener {
                if (
                    checkIsEmptyCurrencyValue() && checkCurrencyInWhat() && checkCurrencyConvert()
                ) {
                    val valueC1 = currencyListChoiceInWhat.selectedItem
                    val valueC2 = currencyListChoiceConvert.selectedItem
                    val countOfCurrency = currencyValue.text.toString().toInt()
                    viewModel.convertCurrency(
                        baseCurrency = valueC1.toString(),
                        convertCurrency = valueC2.toString(),
                        amountOfCurrency = countOfCurrency
                    )
                }
            }
        }
    }

    private fun formingTitleListCurrencyInWhat(
        listCurrency: List<String>,
        initialText: String
    ) {
        val listNameCurrencyNew = mutableListOf<String>()
        listNameCurrencyNew.add(0, initialText)
        listNameCurrencyNew.addAll(listCurrency)
        listNameCurrency = listNameCurrencyNew
    }

    private fun formingTitleListCurrencyConvert(
        listCurrency: List<String>,
        initialText: String
    ) {
        val listNameCurrencyNew = mutableListOf<String>()
        listNameCurrencyNew.add(0, initialText)
        listNameCurrencyNew.addAll(listCurrency)
        listNameCurrencyConvert = listNameCurrencyNew
    }

    private fun createSpinner(
        spinner: Spinner,
        listNameCurrency: List<String>
    ) {
        val adapter = ArrayAdapter(
            this@MainActivity,
            R.layout.simple_spinner_item,
            listNameCurrency
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}