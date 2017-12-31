package com.example.boshili.photoseek.AppController

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by boshili on 2017-12-31.
 */
class AppDelegate: Application() {
    companion object {
        lateinit var requestQueue: RequestQueue
    }

    override fun onCreate() {
        requestQueue = Volley.newRequestQueue(applicationContext)
        super.onCreate()
    }

}