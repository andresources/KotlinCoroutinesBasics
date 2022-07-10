package com.kotlincoroutinesbasics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
/*
* In this We are Just Checking that project is running on main thread
* as we know basic functionalities like button clicking is runs on main thread
* Now the Question is when we runs long running tasks on the Main thread what is the Solution ?
* */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(
            "Coroutine",
            this.localClassName + " is running on thread : ${Thread.currentThread().name}  TID : ${Thread.currentThread().id}"
        )

        findViewById<Button>(R.id.btnCheckMainThread).setOnClickListener() {
            startActivity(Intent(this, A01_Coroutine_Activity::class.java))
        }
    }
}