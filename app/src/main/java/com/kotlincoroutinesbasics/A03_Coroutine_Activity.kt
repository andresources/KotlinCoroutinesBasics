package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/*
* Here we are handling the coroutines
* if we want to stop a coroutine we can able to stop
* if we want to start the coroutine we can able to start
* and if we want to check weather coroutine is alive or not we  can able to check that also
*
* finally Coroutine is in our hand we can able to start and we can able to stop and we can able to check the status
*
* here below is the example
*
* */

class A03_Coroutine_Activity : AppCompatActivity() {
    lateinit var myJob: Deferred<String>
    val numInt : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a03_coroutine)


        findViewById<Button>(R.id.btnStart).setOnClickListener(){

            myJob= lifecycleScope.async {
                myFun()
                "My Coroutine"
            }
            myJob.invokeOnCompletion {
                runBlocking {
                    if(myJob.isCancelled)
                    {
                        Log.i("Coroutine","Coroutine is cancelled...")
                    }

                    if(myJob.isCompleted)
                    {
                        Log.i("Coroutine","Coroutine is completed...")
                    }
                }
            }

        }

        findViewById<Button>(R.id.btnPause).setOnClickListener(){
            runBlocking {
                stopCoroutine()
            }
        }

        findViewById<Button>(R.id.btnIsAlive).setOnClickListener(){
            runBlocking {
                isAliveCor()
            }
        }

    }

    suspend fun stopCoroutine(){
        myJob.cancelAndJoin()

    }
    suspend fun isAliveCor(){
        runBlocking {
            Log.i("Coroutine",""+myJob.isActive)
        }
    }
    suspend fun myFun(){
        for(numInt in 1..10){
            if(numInt >= 9){
                myJob.cancelAndJoin()
            }
            Log.i("Coroutine","value${numInt}")
            delay(1000)
        }
    }
}