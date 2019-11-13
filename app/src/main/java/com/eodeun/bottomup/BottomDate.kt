package com.eodeun.bottomup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.eodeun.bottomup.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.date_dialog.view.*

import java.text.SimpleDateFormat


class BottomDate : BottomSheetDialogFragment() {

    private var mOnSelectorDateListener: ((Year: Int, Month: Int, DayOfMonth: Int) -> Unit)? = null
    fun addOnSelectorDateListener(callback: (Year: Int, Month: Int, DayOfMonth: Int) -> Unit) {
        mOnSelectorDateListener = callback
    }

    var DefautDate : Long = SimpleDateFormat("yyyy.MM.dd.").parse("2019.10.23.")!!.time
    fun setDefaultDate (value:String) {DefautDate = SimpleDateFormat("yyyy.MM.dd.").parse(value)!!.time }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BootomSelectDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = inflater.inflate(R.layout.date_dialog, container, false)
        mView.calendarView.setDate(DefautDate)

        mView.calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(
                p0: CalendarView, year: Int, month: Int, dayOfMonth: Int
            ) {
                if (mOnSelectorDateListener != null) mOnSelectorDateListener!!(
                    year,
                    month,
                    dayOfMonth
                )
                dismiss()
            }
        })
        return mView
    }
}
