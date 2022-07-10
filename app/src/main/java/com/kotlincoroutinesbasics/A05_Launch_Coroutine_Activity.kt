package com.kotlincoroutinesbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class A05_Launch_Coroutine_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a05_launch_coroutine)

        Log.i("Coroutine","Thread Name  ${Thread.currentThread().name}")

        findViewById<Button>(R.id.btnNextActivity).setOnClickListener(){
          startActivity(Intent(this,A01_Coroutine_Activity::class.java))
        }

        lifecycleScope.launch {                // Here We are using lifecycleScope this lays with in the activity (Once we remove our app from the background it will destroy)
            for (i in 1..10){
                delay(2000)
                Log.i("Coroutine","This is lifecycleScope Scope $i")
            }
        }

        GlobalScope.launch {
            for (i in 1..10){
                delay(2000)
                Log.i("Coroutine","This is GlobalScope Scope $i")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Coroutine","Life Cycle Scope is Done")  // after removing app from background this method will call
    }
}