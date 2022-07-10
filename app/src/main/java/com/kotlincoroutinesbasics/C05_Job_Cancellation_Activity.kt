package com.kotlincoroutinesbasics

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

private const val TAG = "JOBActivity"

/*
* in this when we cancel the coroutine even though coroutine is not cancelled
*
* in this type of situations we need to check weather coroutines is active or nor
* */

class C05_Job_Cancellation_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c04_job)

        lifecycleScope.launch {
            execute()
        }
    }

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..101010) {
                if(isActive){
                executeLongRunningTask()
                Log.i(TAG, "Parent Coroutine $i")
            }
            }
        }
        delay(100)
        Log.i(TAG, "Parent Coroutine has been Cancelled")

        parentJob.cancel()
        parentJob.join()

        Log.i(TAG, "Parent Coroutine has been Completed")
    }

    private fun executeLongRunningTask() {
        for (i in 1..1011010101) {
            //Log.i(TAG, "Long Running Coroutine $i")

        }
    }
}