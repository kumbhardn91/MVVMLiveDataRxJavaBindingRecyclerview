package com.example.exercisetwo.repository

import com.example.exercisetwo.model.DataModelClass
import com.example.exercisetwo.retroconnection.RetroInstance
import com.example.exercisetwo.retroconnection.RetroService
import io.reactivex.Observable

object DataRepository {

    fun callApiMethod(): Observable<DataModelClass> {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        return retroInstance.callDataApi()
    }

}