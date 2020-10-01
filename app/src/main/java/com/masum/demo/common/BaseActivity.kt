package com.masum.demo.common

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.masum.demo.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity :DaggerAppCompatActivity() {
    private var binding: ViewDataBinding? = null
    private var loadingView: LinearLayout? = null
    private  var noDataView:LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResourceFile())
        initComponent()
        initFunctionality()
        initListener()

    }

    protected abstract fun getLayoutResourceFile(): Int

    protected abstract fun initComponent()

    protected abstract fun initFunctionality()

    protected abstract fun initListener()

    open fun getBinding(): ViewDataBinding? {
        return binding
    }
    open fun startActivity(
        cls: Class<*>?,
        finishSelf: Boolean,
        bundle: Bundle?
    ) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    open fun initLoader() {
        loadingView = findViewById<View>(R.id.loadingView) as LinearLayout
        noDataView = findViewById<View>(R.id.noDataView) as LinearLayout
    }

    open fun showLoader() {
        if (loadingView != null) {
            loadingView!!.setVisibility(View.VISIBLE)
        }
        if (noDataView != null) {
            noDataView!!.setVisibility(View.GONE)
        }
    }

    open fun hideLoader() {
        if (loadingView != null) {
            loadingView!!.setVisibility(View.GONE)
        }
        if (noDataView != null) {
            noDataView!!.setVisibility(View.GONE)
        }
    }

    open fun showEmptyView() {
        if (loadingView != null) {
            loadingView!!.setVisibility(View.GONE)
        }
        if (noDataView != null) {
            noDataView!!.setVisibility(View.VISIBLE)
        }
    }
    fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
    open fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.activeNetworkInfo
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    open fun showErrorDialog(title: String?, message: String?) {
        val builder: androidx.appcompat.app.AlertDialog.Builder
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            androidx.appcompat.app.AlertDialog.Builder(this, R.style.DialogTheme)
        } else {
            androidx.appcompat.app.AlertDialog.Builder(this)
        }
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which -> dialog.dismiss() }
            .setIcon(R.drawable.sync)
            .show()
    }
}