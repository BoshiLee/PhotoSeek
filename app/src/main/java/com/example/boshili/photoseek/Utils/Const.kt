package com.example.boshili.photoseek.Utils

/**
 * Created by boshili on 2017-12-31.
 */
const val Base_URL = "https://pixabay.com/api/?key="
const val API_KEY = "7552897-ac3fa5743f55a80bc322f5247"

object URLService {
    fun returnImageRequestURLFrom(keyword: String): String{

        var newKeyword = keyword

        if (keyword != "") {
            newKeyword = keyword.replace(" ", "+")
        }
        return "${Base_URL}${API_KEY}&q=$newKeyword&image_type=photo"
    }
}