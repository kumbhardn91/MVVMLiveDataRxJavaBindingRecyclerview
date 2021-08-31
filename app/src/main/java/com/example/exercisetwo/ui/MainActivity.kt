package com.example.exercisetwo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.adapter.CountryModelAdapter
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.utils.showToast
import com.example.exercisetwo.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialization()
        observeCountryLiveData()
        updateListData()

    }

    private fun initialization() {

        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)
    }

    private fun observeCountryLiveData() {

        countryViewModel.countryLiveData.observe(this, {
            if (it != null) {
                val dataList = it.rows
                dataList.let {
                    binding.recyclerView.apply {
                        adapter = CountryModelAdapter(dataList)
                        layoutManager = manager
                    }
                }
            } else {
                showToast(this, getString(R.string.no_data))
            }
        })

    }

    private fun updateListData() {
        countryViewModel.getCountryData()
    }
}