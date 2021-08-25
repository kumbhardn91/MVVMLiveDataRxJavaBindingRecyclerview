package com.example.exercisetwo.retroconnection

import com.example.exercisetwo.model.ModelClass
import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {

    @GET("facts.json")
    fun getDataList(): Observable<ModelClass>
}