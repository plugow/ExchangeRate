package com.plugow.exchangerateapp.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.plugow.exchangerateapp.ui.adapter.BaseAdapter
import com.plugow.exchangerateapp.ui.adapter.OnRecyclerListener
import java.util.*


@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerData(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is BaseAdapter<*>) {
        items?.let {
            (recyclerView.adapter as BaseAdapter<T>).setData(it)
        }
    }
}

@BindingAdapter("onRecyclerClick")
fun setRecyclerListener(recyclerView: RecyclerView, onRecyclerListener: OnRecyclerListener){
    if (recyclerView.adapter is BaseAdapter<*>) {
        (recyclerView.adapter as BaseAdapter<*>).setListener(onRecyclerListener)
    }
}


@DrawableRes
fun Currency.getDrawableRes(context: Context): Int =
    context.resources.getIdentifier("ic_flag_${currencyCode.toLowerCase()}", "drawable", context.packageName)


