package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

/*
* if we want cal a suspending function
*
* We can able to call ->
* 1) with in the Coroutine Scope
* 2) with in the SUSPEND FUNCTION
*
*   Date : 09/07/2022
* */
private const val TAG = "SuspendingFunction"

class C00_Suspend_Function_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c00_suspend_function)

        CoroutineScope(Dispatchers.IO).launch {
            taskOne()
        }
        CoroutineScope(Dispatchers.IO).launch {
            taskTwo()
        }
    }

    suspend fun taskOne() {
        Log.i(TAG, "This is TaskOne")
        yield()                             // here we are using suspending functions so it will suspend and execute another tSK
        Log.i(TAG, "This is TaskOne")


    }

    suspend fun taskTwo() {
        Log.i(TAG, "This is TaskTwo")
        yield()
        Log.i(TAG, "This is TaskTwo")
    }
}
/*

Here is the Output


I/SuspendingFunction: This is TaskTwo
I/SuspendingFunction: This is TaskOne
I/SuspendingFunction: This is TaskTwo
I/SuspendingFunction: This is TaskOne
*
* */