package com.example.orendatask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orenda.model.PhotoModel
import com.example.orendatask.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class PhotoAdapter(val photoList: ArrayList<PhotoModel>)
    : RecyclerView.Adapter<ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_photos, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(photoList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return photoList.size
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById(R.id.iv_photos) as ImageView
    private val txtTitle = view.findViewById(R.id.tv_title) as TextView
    private val progressBar = view.findViewById(R.id.progressBar) as ProgressBar

    fun bindItems(photo: PhotoModel) {
        Picasso.get().load(photo.url).into(image, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
                txtTitle.text = photo.title
            }

            override fun onError(e: Exception?) {
            }

        })
    }
}