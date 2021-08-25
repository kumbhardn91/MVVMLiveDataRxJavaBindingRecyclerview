package com.example.exercisetwo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exercisetwo.R
import com.example.exercisetwo.adapter.DataAdapter
import com.example.exercisetwo.databinding.ActivityMainBinding
import com.example.exercisetwo.utils.showToast
import com.example.exercisetwo.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        manager = LinearLayoutManager(this)
        observeData()

    }

    private fun observeData() {

        dataViewModel.dataResultList.observe(this, {
            if (it != null) {
                val dataList = it.rows
                dataList.let {
                    binding.recyclerView.apply {
                        adapter = DataAdapter(dataList)
                        layoutManager = manager
                    }
                }
            } else {
                showToast(this, getString(R.string.no_data))
            }
        })
    }
}