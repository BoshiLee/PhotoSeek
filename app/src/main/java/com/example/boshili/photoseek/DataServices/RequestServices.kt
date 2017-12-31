package com.example.boshili.photoseek.DataServices

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.boshili.photoseek.AppController.AppDelegate
import org.json.JSONObject

/**
 * Created by boshili on 2017-12-31.
 */
object RequestServices {

    enum class ContentType(val rawValue: String) {
        JSON("application/json; charset=utf-8")
    }

    fun handleJsonRequest(isJsonArray: Boolean, method: Int, url: String, body: JSONObject?, headers: MutableMap<String, String>?, errorListener: Response.ErrorListener, responseListener: (Any) -> Unit) {

        if (isJsonArray) {
            val request = object : JsonArrayRequest(method, url, null, Response.Listener { responseListener(it) }, errorListener) {
                override fun getBodyContentType(): String = ContentType.JSON.rawValue

                override fun getBody() = body?.toString()?.toByteArray() ?: super.getBody()

                override fun getHeaders() = headers ?: super.getHeaders()
            }
            AppDelegate.requestQueue.add(request)
        } else {
            val request = object : JsonObjectRequest(method, url, null, Response.Listener { responseListener(it) }, errorListener) {
                override fun getBodyContentType(): String = ContentType.JSON.rawValue

                override fun getBody(): ByteArray = body?.toString()?.toByteArray() ?: super.getBody()

                override fun getHeaders() = headers ?: super.getHeaders()
            }
            AppDelegate.requestQueue.add(request)
        }

    }
}