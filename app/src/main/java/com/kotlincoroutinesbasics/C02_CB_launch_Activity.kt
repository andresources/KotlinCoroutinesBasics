package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/*
* Launch
*
* It doest return anything
* it just execute and disable(just Like Fire Forgot)
*
* Here we have One Object -> JOB
*
* By using job we will handle the coroutine like cancel the coroutine or check the status and wait for another coroutine
*
*  */

private const val TAG = "LaunchActivity"

class C02_CB_launch_Activity : AppCompatActivity() {
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c02_cb_launch)
       /* GlobalScope.launch(Dispatchers.IO) {
            functionF1()
        }
*/
        findViewById<Button>(R.id.btnStart).setOnClickListener() {
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    test()
                }

                withContext(Dispatchers.Main){
                    findViewById<TextView>(R.id.tvText).text = number.toString()
                }

            }
        }

    }

    private suspend fun functionF1() {
        val job: Job = CoroutineScope(Dispatchers.IO).launch {
            number = fun1()
        }
        job.join() // in this job object we are waiting the coroutine until its job is done
        Log.d(TAG, "This is functionF1  $number")
    }

    private suspend fun fun1(): Int {
        delay(4000)
        Log.d(TAG, "This is fun1")
        return 11
    }

    suspend fun test() {
        for (number in 1..10) {
            delay(2000)
            Log.d(TAG, "This is test $number")
        }
    }
}