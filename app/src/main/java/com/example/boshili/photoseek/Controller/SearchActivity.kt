package com.example.boshili.photoseek.Controller

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.example.boshili.photoseek.Apapter.ImageCollectionViewAdapter
import com.example.boshili.photoseek.DataServices.ImageDataService
import com.example.boshili.photoseek.R
import com.example.boshili.photoseek.Utils.KeyboardManager
import com.example.boshili.photoseek.ViewModel.ImageModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    lateinit var imageDataService: ImageDataService
    lateinit var imageAdapter: ImageCollectionViewAdapter

    val spanCount: Int
        get() {
            val orientation = resources.configuration.orientation
            val screenSize = resources.configuration.screenWidthDp

            return when {
                orientation == Configuration.ORIENTATION_LANDSCAPE -> 3
                screenSize >= 720 -> 4
                else -> 2
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchSpinner.visibility = View.INVISIBLE
        imageDataService = ImageDataService( onSuccessCallBack, onErrorCallBack)
        setupAdapter()
    }

    fun startSearch(view: View) {
        KeyboardManager.hideKeyboard(this)
        val keyword = keywordTextField.text.toString()
        if (keyword != "") {
            searchSpinner.visibility = View.VISIBLE
            imageDataService.searchImageBy(keyword)
        } else {
            searchSpinner.visibility = View.INVISIBLE
            Toast.makeText(this, "Pleas Enter Image Keyword", Toast.LENGTH_SHORT).show()
        }
    }

    val onSuccessCallBack :() -> Unit = {
        searchSpinner.visibility = View.INVISIBLE
        imageAdapter.notifyDataSetChanged()
    }

    val onErrorCallBack: (String) -> Unit = {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    fun setupAdapter() {
        imageAdapter = ImageCollectionViewAdapter(this, ImageModel.images)
        val layoutManager = GridLayoutManager(this, this.spanCount)
        imageCollectionView.adapter = imageAdapter
        imageCollectionView.layoutManager = layoutManager
    }

}
