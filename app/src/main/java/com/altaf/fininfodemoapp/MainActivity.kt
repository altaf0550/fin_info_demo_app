package com.altaf.fininfodemoapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.altaf.fininfodemoapp.adapter.NumberAdapter
import com.altaf.fininfodemoapp.viewmodel.NumberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NumberViewModel
    private lateinit var numberAdapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[NumberViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.numberRecyclerView)
        numberAdapter = NumberAdapter(emptyList())
        recyclerView.layoutManager = GridLayoutManager(this, 10)
        recyclerView.adapter = numberAdapter

        val spinner: Spinner = findViewById(R.id.ruleSpinner)
        val rules = viewModel.getRules()

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rules)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewModel.updateHighlighting(rules[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        viewModel.numbers.observe(this) { numbers ->
            numberAdapter = NumberAdapter(numbers)
            recyclerView.adapter = numberAdapter
            numberAdapter.notifyDataSetChanged()
        }
    }
}
