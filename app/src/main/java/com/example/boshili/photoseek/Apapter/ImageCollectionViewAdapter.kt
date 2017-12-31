package com.example.boshili.photoseek.Apapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.boshili.photoseek.Model.Image
import com.example.boshili.photoseek.R

/**
 * Created by boshili on 2017-12-31.
 */
class ImageCollectionViewAdapter(val context: Context, val images: List<Image>): RecyclerView.Adapter<ImageCollectionViewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(images[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = images.count()

    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {
        val photoImageView = itemView?.findViewById<ImageView>(R.id.resultImageView)
        val likeText = itemView?.findViewById<TextView>(R.id.imageLikesTextView)

        fun bind(image: Image) {
            likeText?.text = image.likes.toString()
        }
    }

}