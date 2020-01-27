package com.example.newvivamacho.ui.add

import TimePick
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.ListFragment

import com.example.newvivamacho.R
import com.example.newvivamacho.ui.home.HomeFragment
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {


    companion object {
        fun newInstance(date: String?): AddFragment {
            val addfragment = AddFragment()
            val bundle= Bundle()
            bundle.putString("Date",date)
            addfragment.setArguments(bundle)

            return addfragment
        }
//
    }

    private lateinit var viewModel: AddViewModel
    private var date:String? = ""
//    private var starttime = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_fragment, container, false)

        val bundle = arguments
//        val bundle2 = arguments
        if (bundle != null) {
            date = bundle.getString("Date")
        }
//        if (bundle2 != null) {
//            starttime = bundle2.getString("Starttime")
//        }


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        //現在時刻
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        val time = String.format("%02d:%02d",hour,minute)


//        val startdatetext:TextView = findViewById(R.id.startdatetext)
//        val settimetext:TextView = root.findViewById(R.id.settimetext)
        startdatetext.text = "開始時刻　       " + date
        settimetext.text = time

        startdatetext.setOnClickListener{
            //TimePicker
            val newFragment = TimePick()
            fragmentManager?.let { newFragment.show(it, "timepicker") }
        }

        //スピナー
        val spinner: Spinner = view.findViewById(R.id.spinner)
        var selectmenu = ""

        ArrayAdapter.createFromResource(
            context,
            R.array.traning_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?, position: Int, id: Long
            ) {
//                val spinnerParent = parent as Spinner
                // Kotlin Android Extensions
                selectmenu = spinner.selectedItem as String
            }
        }

        //キャンセルボタンのあれ
        cancelbutton.setOnClickListener{
            val transaction =  activity?.supportFragmentManager?.beginTransaction()
            transaction?.remove(this)
            transaction?.commit()
        }

        //追加ボタンのあれ
        okbutton.setOnClickListener{
            val settime= settimetext.text.toString()

            val transaction =  activity?.supportFragmentManager?.beginTransaction()
//            HomeFragment.newInstance(settime,selectmenu)
            transaction?.replace(R.id.linearlayout, com.example.newvivamacho.ui.list.ListFragment.newInstance(settime,selectmenu))
            //transaction?.replace(R.id.linearlayout,HomeFragment.newInstance(settime,selectmenu))
            transaction?.commit()
        }

    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val str = String.format(Locale.US,"%d:%d", hourOfDay, minute)

        startdatetext.text = "開始時刻　" + str

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
