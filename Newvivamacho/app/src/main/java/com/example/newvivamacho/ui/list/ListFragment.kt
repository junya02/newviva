package com.example.newvivamacho.ui.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.example.newvivamacho.R
import com.example.newvivamacho.ui.add.AddFragment
import com.example.newvivamacho.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_home.*

class ListFragment : Fragment() {

    private var starttime:String = ""
    private var selectmenu:String = ""


    companion object {
        fun newInstance(starttime: String?, menu: String): ListFragment {
            val listfragment = ListFragment()
            val bundle = Bundle()
            bundle.putString("Starttime",starttime)
            bundle.putString("Menu",menu)
            listfragment.setArguments(bundle)

            Log.d("TAG",starttime)

            return listfragment
        }
    }


    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = arguments
        if (bundle != null) {
            starttime = bundle.getString("Starttime")
            selectmenu = bundle.getString("Menu")
        }

        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timeArray:ArrayList<String> = arrayListOf()
        val menuArray:ArrayList<String> = arrayListOf()
        timeArray.add("     "+starttime)
        menuArray.add(selectmenu)


        val timeadapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, timeArray)
        val menuadapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, menuArray)

        timelist?.adapter = timeadapter
        menulist?.adapter = menuadapter

    }

}
