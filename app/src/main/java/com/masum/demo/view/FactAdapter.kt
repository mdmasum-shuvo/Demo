package com.masum.demo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.demo.R
import com.masum.demo.common.ItemClickListener
import com.masum.demo.data.All
import com.masum.demo.databinding.ItemFactBinding


class FactAdapter  constructor(private val context: Context?, private val list: List<All>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var listener: ItemClickListener

    init {
        layoutInflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dashboardBinding: ItemFactBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_fact, parent, false
        )
        return  ViewFilesHolder(
            dashboardBinding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewFilesHolder) {
            holder.binding.data=list!!.get(position)
            holder.itemView.setOnClickListener {
                listener.onClick(position,holder.itemView)
            }
        }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class ViewFilesHolder(itemBinding: ItemFactBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val binding: ItemFactBinding

        init {
            binding = itemBinding
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}