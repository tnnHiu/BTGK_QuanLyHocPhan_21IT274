package com.example.btgk_quanlyhocphan_21it274.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
import androidx.recyclerview.widget.RecyclerView
import com.example.btgk_quanlyhocphan_21it274.databinding.ListHpLayoutBinding
import com.example.btgk_quanlyhocphan_21it274.model.HocPhan

class ListHpRVAdapter_21IT274(private val listHp: ArrayList<HocPhan>):
RecyclerView.Adapter<ListHpRVAdapter_21IT274.ViewHolder>()
{

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        mListener = clickListener
    }

    class ViewHolder(val binding: ListHpLayoutBinding, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ListHpLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemView, mListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val onBindView = holder.binding
        onBindView.tvHp.text = listHp[position].tenHP
    }

    override fun getItemCount(): Int {
        return listHp.size
    }
}