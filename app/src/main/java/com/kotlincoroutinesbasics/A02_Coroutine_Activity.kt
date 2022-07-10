package com.kotlincoroutinesbasics

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/*
*To Launch Coroutines we have 3 launchers
* 1) Launch (it will work normally)
* 2) Async (it will return some value )
* 3) RunBlocking (It will block the main the=read execution)
*
* We have 3 Coroutine Scoops
*
* 1) Global Scope (This is through out the application Scope)
* 2) Activity or Fragment Scope (it will only exists activity or fragment scope)
* 3) ViewModel Scope (this is view model scope)
* */

class A02_Coroutine_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a02_coroutine)

        findViewById<Button>(R.id.btnCoroutine).setOnClickListener() {
            lifecycleScope.launch(Dispatchers.Main) {
                val time = measureTimeMillis {
                    fun1()
                    fun2()
                }
                Log.i("Coroutine", "Completion Time : $time")
            }
        }

        findViewById<Button>(R.id.btnNormalThread).setOnClickListener() {
            fun3()
            fun4()
        }
    }

    private fun fun1() {
        for (i in 1..20) {
            Log.i("Coroutine", "fun1   :$i")
            Thread.sleep(2000)
        }

        Log.i(
            "Coroutine",
            "fun1 _ Thread  ${Thread.currentThread().name}}"
        )
    }

    private suspend fun fun2() {
        for (i in 1..10) {
            Log.i("Coroutine", "fun2  $i")
            delay(1000)
        }
        Log.i(
            "Coroutine",
            "fun2 _ Thread  ${Thread.currentThread().name}"
        )
    }

    private fun fun3() {
        for (i in 1..10) {
            Log.i("Coroutine", "fun3 : $i")
            Thread.sleep(1000)
        }
        Log.i("Coroutine", "fun3 - Thread : " + Thread.currentThread().name)
    }

    private fun fun4() {
        for (i in 11..20) {
            Log.i("Coroutine", "fun4 : $i")
            Thread.sleep(1000)
        }
        Log.i("Coroutine", "fun4 - Thread : " + Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Coroutine", "On Destroy Method Called : ")
    }

}