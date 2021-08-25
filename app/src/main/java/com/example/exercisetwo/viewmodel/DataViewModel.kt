package com.example.exercisetwo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetwo.model.ModelClass
import com.example.exercisetwo.repository.Repository

class DataViewModel : ViewModel() {

    val dataResultList: MutableLiveData<ModelClass> = Repository.makeApiCall()

}

