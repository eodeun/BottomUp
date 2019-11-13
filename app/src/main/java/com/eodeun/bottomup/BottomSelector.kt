package com.eodeun.bottomup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eodeun.bottomup.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.selector_dialog.view.*
import kotlinx.android.synthetic.main.selector_dialog_item.view.*

// select item list
data class BottomSelectorItem(
    var name: String = ""
) {}


class BottomSelectorAdapter(
    private val mContext: Context,
    private val lists: ArrayList<BottomSelectorItem>
) : RecyclerView.Adapter<BottomSelectorAdapter.VH>() {
    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var title = v.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(mContext).inflate(R.layout.selector_dialog_item, parent, false))

    override fun getItemCount() = lists.size

    var mOnClickListener: ((Int) -> Unit)? = null
    fun addOnClickListener(callback: (Int) -> Unit) {
        mOnClickListener = callback
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val mPosition = holder.adapterPosition
        if (mPosition != RecyclerView.NO_POSITION) {
            // 리사이클러 뷰 포지션이 맞으면
            val mItem = lists.get(mPosition)

            holder.title.text = mItem.name
            if (mOnClickListener != null) {
                holder.itemView.setOnClickListener {
                    mOnClickListener!!(mPosition)
                }
            }
        }
    }
}

class BottomSelector(val mContext: Context) : BottomSheetDialogFragment() {
    var items = ArrayList<BottomSelectorItem>()
    var mOnClickListener: ((Int) -> Unit)? = null
    fun addOnSelecteItem(callback: (Int) -> Unit) {
        mOnClickListener = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BootomSelectDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.selector_dialog, container, false)
        val adapter = BottomSelectorAdapter(mContext, items)
        adapter.addOnClickListener {
            if (mOnClickListener != null) {
                this@BottomSelector.mOnClickListener!!(it)
                dismiss()
            }
        }

        mView.recyclerSelectorView.layoutManager = LinearLayoutManager(mContext).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        mView.recyclerSelectorView.adapter = adapter

        return mView
    }
}