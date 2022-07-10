package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
/*
* In this Activity  we are just checking that our Log Function will be Executed on Which Thread
*
* it Will Execute on Main Thread
*
* */
class A01_Coroutine_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a01_coroutine)
        Log.i(
            "Coroutine",
            this.localClassName + " is running on thread : ${Thread.currentThread().name}  TID : ${Thread.currentThread().id}"
        )

    }
}