package com.plugow.exchangerateapp.ui.adapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    protected var values: ArrayList<T> = arrayListOf()
    protected lateinit var onRecyclerListener:OnRecyclerListener

    abstract fun setData(items:List<T>)

    fun setListener(listener: OnRecyclerListener){
        onRecyclerListener = listener
    }


    override fun getItemCount(): Int {
        return values.size
    }

}