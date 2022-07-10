package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/*
* runBlocking - > It Will Block the MAin Thread
* blocks the current thread until all tasks of the coroutine it creates
*
* */

private const val TAG = "RunBlocking"

class C01_CB_runblocking_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c01_cb_runblocking)

        Log.d(TAG, "Before run-blocking")

        runBlocking {

            Log.d(TAG, "just entered into the runBlocking ")
            example1()             // This will block the Main Thread (we can check in the Logcat)
            Log.d(TAG, "start of the run-blocking")
            Log.d(TAG, "End of the runBlocking")
        }

        Log.d(TAG, "Fret run-blocking")

    }

    private suspend fun example1() {
        delay(5000)
        Log.i(
            TAG,
            " This is Runs on Coroutine Builder RUnblocking _ running on thread : ${Thread.currentThread().name}  TID : ${Thread.currentThread().id}"
        )
    }
}