package com.plugow.exchangerateapp.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.plugow.exchangerateapp.data.local.ERCurrency
import com.plugow.exchangerateapp.data.remote.ApiService
import com.plugow.exchangerateapp.util.Event
import com.plugow.exchangerateapp.ui.adapter.ClickType
import com.plugow.exchangerateapp.ui.adapter.RecyclerClickType
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(private val service: ApiService, private val ctx:Context): ViewModel() {
    lateinit var rates:ArrayList<ERCurrency>
    var items: MutableLiveData<ArrayList<ERCurrency>> = MutableLiveData()
    private val disposables= CompositeDisposable()
    private val mEvent:MutableLiveData<Event<Any>> = MutableLiveData()
    val event : LiveData<Event<Any>>
        get() = mEvent

    fun onResume(){
        if (!disposables.isDisposed){
            Observable.interval(200, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {loadItems()},
                    onError = {onError()}
                )
                .addTo(disposables)
        }
    }

    fun stopFethingRates(){
        if (!disposables.isDisposed){
            disposables.dispose()
        }
    }

    fun loadItems() {
        service.getRates()
            .map { mapToCurrencyList(it.rates) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    rates= it
                    items.value = it
                }
            )
            .addTo(disposables)


    }

    fun onRecyclerClick(type: ClickType, pos: Int) {
        when(type){
            RecyclerClickType.FOCUS_CHANGED -> stopFethingRates()
            RecyclerClickType.VALUE_CHANGED -> {}
        }
    }

    fun updateRates(){

    }

    fun onError(){}

    fun mapToCurrencyList(rates: Map<String, Double>):ArrayList<ERCurrency>{
        val erCurrency = ArrayList<ERCurrency>()
        erCurrency.add(ERCurrency(ctx, "EUR", 1.0))
        rates.forEach { (code, rate) -> erCurrency.add(ERCurrency(ctx, code, rate))}
        return erCurrency
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


}