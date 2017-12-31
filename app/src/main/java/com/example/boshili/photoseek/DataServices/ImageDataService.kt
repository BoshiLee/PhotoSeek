package com.example.boshili.photoseek.DataServices

import com.android.volley.Request
import com.android.volley.Response
import com.example.boshili.photoseek.Model.Image
import com.example.boshili.photoseek.Utils.URLService
import com.example.boshili.photoseek.ViewModel.ImageModel
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by boshili on 2017-12-31.
 */
class ImageDataService(var successCallBack: () -> Unit, var errorCallBack: (String) -> Unit) {

    fun searchImageBy(keyword: String) {
        val url = URLService.returnImageRequestURLFrom(keyword)
        RequestServices.handleJsonRequest(false, Request.Method.GET, url, null, null, Response.ErrorListener { error ->
            errorCallBack(error.localizedMessage)
        }) { response ->
            if (response is JSONObject) {
                ImageModel.images.clear()
                try {
                    val list = response.getJSONArray("hits")
                    for (ix in 0 until list.length()){
                        val likes = list.getJSONObject(ix).getInt("likes")
                        val previewURL = list.getJSONObject(ix).getString("previewURL")
                        val webformatURL = list.getJSONObject(ix).getString("webformatURL")
                        ImageModel.images.add(Image(likes, previewURL, webformatURL))
                    }
                    successCallBack()
                }catch (e: JSONException) {
                    errorCallBack(e.localizedMessage)
                }
            }
        }
    }
}