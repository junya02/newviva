package com.example.newvivamacho.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newvivamacho.R
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import java.util.*
import android.widget.Button
import com.example.newvivamacho.ui.add.AddFragment
import java.text.SimpleDateFormat
import java.util.Locale.JAPANESE


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            //textView.text = it
        })


        //日付表示
        val df = SimpleDateFormat("yyyy年MM月dd日(E)")
        val df2 = SimpleDateFormat("yyyy年MM月")
        val today = df.format(Date())
        val month = df2.format(Date())
        val datetext:TextView = root.findViewById(R.id.dateText)
        val datetext2:TextView = root.findViewById(R.id.dateText2)

        datetext.text = "    " + today
        datetext2.text = month

        //当日追加パターン
        val addbutton:Button = root.findViewById(R.id.addButton)
        addbutton.setOnClickListener{

        }

        //カレンダー
        val compactCalendarView : CompactCalendarView = root.findViewById(R.id.compactcalendar_view)
        val Cal:Calendar = Calendar.getInstance()
        Cal.timeZone  = TimeZone.getTimeZone("Asia/Tokyo")
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY)

        compactCalendarView.setLocale( Cal.timeZone, JAPANESE)
        compactCalendarView.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            // 日付がクリックされたときの処理
            override fun onDayClick(dateClicked: Date) {
                //日付表示
                datetext.text = "    " + df.format(dateClicked)

                addbutton.setOnClickListener{
                    val transaction =  activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.linearlayout, AddFragment.newInstance())
                    transaction?.addToBackStack(null)
                    transaction?.commit()
                }
            }

            // 表示する月が変わったときの処理
            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                datetext.text = "    " + df.format(firstDayOfNewMonth)
                datetext2.text = df2.format(firstDayOfNewMonth)
            }
        })

        return root
    }
}


