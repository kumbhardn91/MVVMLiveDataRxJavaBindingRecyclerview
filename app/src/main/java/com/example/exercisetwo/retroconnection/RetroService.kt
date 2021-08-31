package com.example.exercisetwo.retroconnection

import com.example.exercisetwo.model.DataModelClass
import io.reactivex.Observable
import retrofit2.http.GET


interface RetroService {

    @GET("facts.json")
    fun callDataApi(): Observable<DataModelClass>
}