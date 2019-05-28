package com.plugow.exchangerateapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.plugow.exchangerateapp.R
import com.plugow.exchangerateapp.data.local.ERCurrency
import kotlinx.android.synthetic.main.rate_item.*
import javax.inject.Inject

class RateAdapter @Inject constructor() : BaseAdapter<ERCurrency>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ERCurrency> {
        return LaunchpadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rate_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ERCurrency>, position: Int) {
        val currency = values[position]
        holder.bind(currency)
        holder.containerView.setOnClickListener {
            onRecyclerListener.onClick(RecyclerClickType.MAIN, position)
        }

    }

    class LaunchpadViewHolder(containerView: View) : BaseViewHolder<ERCurrency>(containerView) {
        override fun bind(item: ERCurrency) {
            shortName.text = item.code
            fullName.text = item.displayName
            currentRate.setText(item.rate.toString())
            flag.setImageResource(item.flagResource)

        }
    }

}