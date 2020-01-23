package com.example.newvivamacho.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newvivamacho.R
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import java.util.*
import android.widget.Button
import android.widget.ListView
import com.example.newvivamacho.MainActivity
import com.example.newvivamacho.ui.add.AddFragment
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.Locale.JAPANESE
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var starttime = ""
    private var selectmenu = ""

    companion object {
        fun newInstance(starttime: CharSequence?, menu: String):AddFragment {
            val addfragment = AddFragment()
            val bundle = Bundle()
            bundle.putCharSequence("starttime",starttime)
            bundle.putString("Menu",menu)
            addfragment.setArguments(bundle)

            Log.d("TAG",starttime.toString())
            return addfragment
        }
    }



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

        val bundle = arguments
        if (bundle != null) {
            starttime = bundle.getString("Starttime")
            selectmenu = bundle.getString("Menu")
        }





        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //日付表示
        val df = SimpleDateFormat("yyyy年MM月dd日(E)")
        val df2 = SimpleDateFormat("yyyy年MM月")
        val df3 = SimpleDateFormat("yyyy/MM/dd/(E)")
        val today = df.format(Date())
        val month = df2.format(Date())
        var date = ""
        val datetext:TextView = view.findViewById(R.id.dateText)
        val datetext2:TextView = view.findViewById(R.id.dateText2)

        datetext.text = "    " + today
        datetext2.text = month


        //カレンダー
        //val compactCalendarView : CompactCalendarView = root.findViewById(R.id.compactcalendar_view)
        val Cal:Calendar = Calendar.getInstance()
        Cal.timeZone  = TimeZone.getTimeZone("Asia/Tokyo")
        compactcalendar_view.setFirstDayOfWeek(Calendar.SUNDAY)

        compactcalendar_view.setLocale( Cal.timeZone, JAPANESE)

        //当日追加パターン
        //val addbutton:Button = root.findViewById(R.id.addButton)
//        addbutton.setOnClickListener{
//            date = df3.format(today)
//            val transaction =  activity?.supportFragmentManager?.beginTransaction()
//            transaction?.replace(R.id.linearlayout, AddFragment.newInstance(date))
//            transaction?.addToBackStack(null)
//            transaction?.commit()
//        }

        compactcalendar_view.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            // 日付がクリックされたときの処理
            override fun onDayClick(dateClicked: Date) {
                //日付表示
                date = df3.format(dateClicked)
                datetext.text = "    " + df.format(dateClicked)

                addButton.setOnClickListener{
                    val transaction =  activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.linearlayout, AddFragment.newInstance(date))
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

        //listview
        //val timelist:ListView = root.findViewById(R.id.timelist)
        //val menulist:ListView = root.findViewById(R.id.menulist)
        val timeArray:ArrayList<String> = arrayListOf()
        val menuArray:ArrayList<String> = arrayListOf()


        if(starttime != ""){
            timeArray.add(starttime)
            menuArray.add(selectmenu)
        }

        val timeadapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, timeArray)
        val menuadapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, menuArray)

        timelist.adapter = timeadapter
        menulist.adapter = menuadapter

    }
}


