package com.example.newvivamacho.ui.add

import TimePick
import android.app.TimePickerDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker

import com.example.newvivamacho.R
import kotlinx.android.synthetic.main.add_fragment.*
import java.util.*

class AddFragment : Fragment(), TimePickerDialog.OnTimeSetListener {



    companion object {
        fun newInstance() = AddFragment()

        val fragment01: AddFragment = AddFragment()

        val args = Bundle()

    }

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.add_fragment, container, false)

        val startdatetext:TextView = root.findViewById(R.id.startdatetext)
        startdatetext.text = "開始時刻　"

        startdatetext.setOnClickListener{
            //TimePicker
            val newFragment = TimePick()
            fragmentManager?.let { newFragment.show(it, "timepicker") }

        }

        val cancelbutton:Button = root.findViewById(R.id.cancelbutton)
        cancelbutton.setOnClickListener{
            val transaction =  activity?.supportFragmentManager?.beginTransaction()
            transaction?.remove(this)
            transaction?.commit()
        }
        return root

    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        val str = String.format(Locale.US, "%d:%d", hourOfDay, minute)

        // use the plug in of Kotlin Android Extensions
        startdatetext.text = "開始時刻　" + str

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
