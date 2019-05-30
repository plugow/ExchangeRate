package com.plugow.exchangerateapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.focusChanges
import com.jakewharton.rxbinding3.widget.textChanges
import com.plugow.exchangerateapp.R
import com.plugow.exchangerateapp.data.local.ERCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.rate_item.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.collections.ArrayList

class RateAdapter @Inject constructor() : BaseAdapter<ERCurrency>() {
    private val disposables = CompositeDisposable()

    override fun setData(items: List<ERCurrency>) {
        val diffCallback = RateDiffUtilCallback(values, items as ArrayList<ERCurrency>)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        values.clear()
        values = items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ERCurrency> {
        val viewHolder = LaunchpadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rate_item, parent, false))
        viewHolder.currentRate.focusChanges()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (it){
                        val pos =viewHolder.adapterPosition
                        onRecyclerListener.onClick(RecyclerClickType.FOCUS_CHANGED, pos)
                        if (pos != RecyclerView.NO_POSITION){
                            val item = values[pos]
                            values.removeAt(pos)
                            values.add(0, item)
                            notifyItemMoved(pos, 0)
                        }
                    }
                }
            ).addTo(disposables)

        viewHolder.currentRate.textChanges()
            .skip(1)
            .filter { viewHolder.currentRate.isFocused }
            .filter { viewHolder.currentRate.toString() != "" }
            .debounce(200, TimeUnit.MILLISECONDS)
            .subscribeBy(
                onNext = {
                    val pos =viewHolder.adapterPosition
                    if (pos != RecyclerView.NO_POSITION){
                        values[pos].output=it.toString().toDoubleOrNull() ?: values[pos].output
                        onRecyclerListener.onClick(RecyclerClickType.VALUE_CHANGED, pos)
                    }
                }).addTo(disposables)

        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ERCurrency>, position: Int) {
        val currency = values[position]
        holder.bind(currency)
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        disposables.clear()
    }


    class LaunchpadViewHolder(containerView: View) : BaseViewHolder<ERCurrency>(containerView) {
        override fun bind(item: ERCurrency) {
            shortName.text = item.code
            fullName.text = item.displayName
            val output = String.format(Locale.US, "%.2f", item.output)
            currentRate.setText(output)
            flag.setImageResource(item.flagResource)

        }
    }
}