package com.example.exercisetwo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetwo.model.DataModelClass
import com.example.exercisetwo.repository.DataRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DataViewModel : ViewModel() {

    var dataResultList: MutableLiveData<DataModelClass> = MutableLiveData()

    fun getDataFromRepo() {
        DataRepository.callApiMethod()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDataListObserverRx())
    }

    private fun getDataListObserverRx(): Observer<DataModelClass> {
        return object : Observer<DataModelClass> {

            override fun onComplete() {
                //hide progressbar
            }

            override fun onError(e: Throwable) {
                dataResultList.value = null
                Log.e("onError", e.toString())
            }

            override fun onNext(t: DataModelClass) {
                dataResultList.value = t
                Log.e("onNext", t.toString())
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progressbar
            }
        }
    }


}

