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
import androidx.recyclerview.widget.SimpleItemAnimator
import org.jetbrains.anko.toast


class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var adapter:RateAdapter
    private lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            list.adapter = adapter
            list.layoutManager = LinearLayoutManager(this@MainActivity)
            (list.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
//        viewModel.onResume()
        viewModel.event.observe(this, Observer {
            toast(getString(R.string.wrong))
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopFethingRates()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.list.adapter=null
        viewModel.stopFethingRates()
    }

}
