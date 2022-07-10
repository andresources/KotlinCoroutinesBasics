package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

/*
* it will return some value in form of Differed<T>
* we can handle the coroutine
* */
private const val TAG = "AsyncActivity"

class C03_CB_async_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c03_cb_async)
        CoroutineScope(Dispatchers.IO).launch {
           // printFollowersWithLaunch()

            printFollowersWithAsync()

        }
    }
    /* Same below functions we will launch using async */

    private suspend fun printFollowersWithAsync() {
        CoroutineScope(Dispatchers.IO).launch {

            val fun1result = async { mediumFollowers() }
            val function2result = async { twitterFollowers() }

            Log.i(TAG, "${fun1result.await()}  ${function2result.await()}")

        }
    }

    /*We are Launching the Coroutine By Using Launch
    *
    * as compare to launch coroutine this is best approach to write the code in cleaner manner
    * if we want return something then will go for async
    * if we don't want to written anything then will go for launch
    *
    * */
    private suspend fun printFollowersWithLaunch() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            mediumFollowers()
        }

        val job1 = CoroutineScope(Dispatchers.IO).launch {
            twitterFollowers()
        }

        //Log.i(TAG,"${mediumFollowers()}  ${twitterFollowers()}")
        Log.i(TAG, "${job.join()}  ${job1.join()}")

    }

    private suspend fun mediumFollowers(): Int {
        delay(2000)
        Log.i(TAG, "This is Medium Followers")
        return 112

    }

    private suspend fun twitterFollowers(): Int {
        delay(2000)
        Log.i(TAG, "This is Twitter Followers")
        return 222
    }
}