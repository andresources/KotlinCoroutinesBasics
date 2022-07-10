package com.kotlincoroutinesbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class A04_Coroutine_Activity : AppCompatActivity() {
    var responseCode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a04_coroutine)

        findViewById<Button>(R.id.btnNetwork).setOnClickListener() {
            readData()
        }

        findViewById<Button>(R.id.btnNetworkNormalThread).setOnClickListener() {
            Thread { readDataUpdateUI() }.start()
        }

        findViewById<Button>(R.id.btnNetworkCoroutine).setOnClickListener() {
            lifecycleScope.launch(Dispatchers.IO) {
                readDataUsingCor()
                //tv.text = "RCode : "+responseCode -> will cause error->updating ui from io thread
                runBlocking(Dispatchers.Main) {
                    findViewById<TextView>(R.id.tvText).text = "RCode : $responseCode"
                }
            }
        }

        findViewById<Button>(R.id.btnWithContext).setOnClickListener() {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    readDataUsingCor()
                }
                withContext(Dispatchers.Main) {
                    findViewById<TextView>(R.id.tvText).text = "RCode : $responseCode"
                }
            }
        }
    }

    suspend fun readDataUsingCor() {
        val url = URL("https://www.saksh.com/")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        responseCode = urlConnection.responseCode

        //tv.text = "RCode : "+responseCode -> Error bz updating UI FROM Main Thread
    }

    fun readData() {
        val url = URL("https://www.sakshi.com/")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        responseCode = urlConnection.responseCode
    }

    fun readDataUpdateUI() {
        val url = URL("https://www.sakshi.com/")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        responseCode = urlConnection.responseCode
        runOnUiThread {
            findViewById<TextView>(R.id.tvText).text = "RCode : $responseCode"
        }
    }
}