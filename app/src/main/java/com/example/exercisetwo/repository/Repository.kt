package com.example.exercisetwo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.exercisetwo.retroconnection.RetroInstance
import com.example.exercisetwo.retroconnection.RetroService
import com.example.exercisetwo.model.ModelClass
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

object Repository {

    var dataResultList: MutableLiveData<ModelClass> = MutableLiveData()

    fun makeApiCall(): MutableLiveData<ModelClass> {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        retroInstance.getDataList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDataListObserverRx())

        return dataResultList
    }


    private fun getDataListObserverRx(): Observer<ModelClass> {
        return object : Observer<ModelClass> {

            override fun onComplete() {
                //hide progressbar
            }

            override fun onError(e: Throwable) {
                dataResultList.value = null
                Log.e("onError", e.toString())
            }

            override fun onNext(t: ModelClass) {
                dataResultList.value = t
                Log.e("onNext", t.toString())
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progressbar
            }
        }
    }

}