package com.kotlincoroutinesbasics.viewmodel_livedata_fragmet_communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutinesbasics.R
/*
* All about View Model and  LiveData
* how to declare View Model And How to get the data from View Model and how to call livedata
* and how to update data using view model and livedata
* */
class A99_Activity_FragmentCommunication : AppCompatActivity() {
    val viewModel: A99_ViewModel  by lazy { ViewModelProvider(this).get(A99_ViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a99_fragment_communication)

      //  val viewModel = ViewModelProvider(this).get(A99_ViewModel::class.java)

        findViewById<Button>(R.id.btnUpdateFromActivity).setOnClickListener(){
            viewModel.setData("This is Activity Updating Automatically")
        }

        viewModel.getData().observe(this, Observer {
            findViewById<TextView>(R.id.tvActivityInfo).text = it
        })
    }
}