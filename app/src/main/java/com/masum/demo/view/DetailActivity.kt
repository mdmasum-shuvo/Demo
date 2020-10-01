package com.masum.demo.view

import com.masum.demo.R
import com.masum.demo.common.BaseActivity
import com.masum.demo.common.Constant
import com.masum.demo.data.All
import com.masum.demo.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_detail
    }

    override fun initComponent() {
        binding = getBinding() as ActivityDetailBinding
        var bundle = intent.extras
        var data = bundle!!.getSerializable(Constant.KEY)
        binding.data= data as All?

    }

    override fun initFunctionality() {
    }

    override fun initListener() {
    }

}