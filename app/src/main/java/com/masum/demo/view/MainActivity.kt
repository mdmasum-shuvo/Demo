package com.masum.demo.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.masum.demo.common.BaseActivity
import com.masum.demo.R
import com.masum.demo.common.Constant
import com.masum.demo.common.ItemClickListener
import com.masum.demo.data.All
import com.masum.demo.data.DataResource
import com.masum.demo.databinding.ActivityMainBinding
import com.masum.demo.di.ViewModelProviderFactory
import com.masum.demo.utils.MyDividerItemDecoration
import com.masum.demo.viewmodel.FactDataViewModel
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var factDataViewModel: FactDataViewModel
    private var factDataList = ArrayList<All>()
    private lateinit var factAdapter: FactAdapter

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_main
    }

    override fun initComponent() {
        binding = getBinding() as ActivityMainBinding
        initLoader()

        factDataViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(FactDataViewModel::class.java)

        binding.rv.setHasFixedSize(true)
        val layoutManagerV = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding.rv.layoutManager = layoutManagerV
        binding.rv.addItemDecoration(
            MyDividerItemDecoration(
                this!!,
                LinearLayoutManager.VERTICAL,
                16
            )
        )

        factAdapter = FactAdapter(this, factDataList)
        binding.rv.adapter = factAdapter
        if (!isNetworkAvailable()){
            showErrorDialog(getString(R.string.no_internet),getString(R.string.internet_not_available))
            return
        }
        factDataViewModel.getFactList()

    }

    override fun initFunctionality() {
        observeSubjectData()
    }

    override fun initListener() {
        factAdapter.setOnItemClickListener(object : ItemClickListener {
            override fun onClick(position: Int, view: View?) {
                showToast("item click:" + position)
                var bundle = Bundle()
                bundle.putSerializable(Constant.KEY, factDataList[position])
                startActivity(DetailActivity::class.java, false, bundle)
            }
        })
    }

    private fun observeSubjectData() {
        factDataViewModel.allFactList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        showLoader()

                    }
                    DataResource.DataStatus.ERROR -> {
                        showEmptyView()
                    }

                    DataResource.DataStatus.SUCCESS -> {

                        if (dataResource.data!!.all != null) {
                            if (!factDataList.isEmpty()) {
                                factDataList.clear()
                            }
                            hideLoader()
                            factDataList.addAll(dataResource.data!!.all!!)
                            factAdapter.notifyDataSetChanged()

                        }
                    }
                }
            }
        })


    }
}