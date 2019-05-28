package com.plugow.exchangerateapp.trait

import androidx.lifecycle.MutableLiveData
import com.plugow.exchangerateapp.ui.adapter.ClickType

interface RefreshableList<T> {
    var items:MutableLiveData<List<T>>
    var isLoadingRefresh:MutableLiveData<Boolean>

    fun loadItems()
    fun onRecyclerClick(type: ClickType, pos:Int)

    fun getItems(){
        if(items.value==null){
            isLoadingRefresh.value=true
            loadItems()
        }
    }

    fun onRefreshItems(){
        loadItems()
    }

    fun initValues(id:Int = 0){
        getItems()
    }

}