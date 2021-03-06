package com.example.exercisetwo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercisetwo.model.CountryModel
import com.example.exercisetwo.repository.DataRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CountryViewModel : ViewModel() {

    var countryLiveData: MutableLiveData<CountryModel> = MutableLiveData()

    fun getCountryData() {
        DataRepository.getCountryInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getDataListObserverRx())
    }

    private fun getDataListObserverRx(): Observer<CountryModel> {
        return object : Observer<CountryModel> {

            override fun onComplete() {
                //hide progressbar
            }

            override fun onError(e: Throwable) {
                countryLiveData.value = null
                Log.e("onError", e.toString())
            }

            override fun onNext(t: CountryModel) {
                countryLiveData.value = t
                Log.e("onNext", t.toString())
            }

            override fun onSubscribe(d: Disposable) {
                //start showing progressbar
            }
        }
    }

}

