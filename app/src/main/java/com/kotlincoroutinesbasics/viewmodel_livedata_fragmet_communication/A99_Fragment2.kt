package com.kotlincoroutinesbasics.viewmodel_livedata_fragmet_communication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutinesbasics.BuildConfig
import com.kotlincoroutinesbasics.R

class A99_Fragment2 : Fragment() {
    private val viewModel: A99_ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_a99_2, container, false)

        //val viewModel = ViewModelProvider(this).get(A99_ViewModel::class.java)

        view.findViewById<Button>(R.id.btnFragment2).setOnClickListener(){
            viewModel.setData("This is Fragment Two")
        }

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.tvF2Text).text = it
        })

        return view
    }


}