package com.plugow.exchangerateapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.plugow.exchangerateapp.data.local.ERCurrency

class RateDiffUtilCallback(private val newList: ArrayList<ERCurrency>, private val oldList: ArrayList<ERCurrency>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code == newList[newItemPosition].code
    }

    override fun getOldListSize(): Int {
         return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].rate == newList[newItemPosition].rate
    }
}