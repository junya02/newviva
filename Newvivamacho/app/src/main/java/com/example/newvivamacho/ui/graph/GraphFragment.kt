package com.example.newvivamacho.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newvivamacho.R

class graphFragment : Fragment() {

    private lateinit var graphViewModel: graphViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        graphViewModel =
            ViewModelProviders.of(this).get(graphViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_graph, container, false)
        val textView: TextView = root.findViewById(R.id.text_graph)
        graphViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}