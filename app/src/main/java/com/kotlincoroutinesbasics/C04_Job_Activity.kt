package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
private const val TAG = "JOBActivity"
class C04_Job_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c04_job)

        lifecycleScope.launch{
            execute()
        }

/*
* When we cancelled the parent coroutine child coroutine will automatically cancelled
* */

    }
    private suspend fun execute(){
        val  parentJob = GlobalScope.launch (Dispatchers.Main){
            Log.i(TAG,"Paren Job Started")
            val childJob = launch(Dispatchers.IO) {
                Log.i(TAG,"Child Job Started")
                delay(3000)
                Log.i(TAG,"Child Job Ended")
            }
            delay(2000)
            Log.i(TAG,"Child Job Cancelled")
            childJob.cancel()
            Log.i(TAG,"Paren Job End")

        }
       /* delay(1000)
        parentJob.cancel()*/
        parentJob.join()
        Log.i(TAG,"Paren Job Completed")
    }
}