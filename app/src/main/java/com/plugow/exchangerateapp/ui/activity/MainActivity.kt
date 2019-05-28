package com.plugow.exchangerateapp.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.plugow.exchangerateapp.R
import com.plugow.exchangerateapp.databinding.ActivityMainBinding
import com.plugow.exchangerateapp.ui.adapter.RateAdapter
import com.plugow.exchangerateapp.viewModel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var adapter:RateAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            list.adapter = adapter
            list.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModel.onResume()
        viewModel.event.observe(this, Observer {
            when(val event = it.getContentIfNotHandled()){
            }
        })
    }

}
