package com.example.orendatask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.orenda.model.PhotoModel
import com.example.orendatask.R
import com.example.orendatask.adapter.PhotoAdapter
import com.example.orendatask.utils.ShowProgress
import com.example.orendatask.viewmodel.ViewModalClass
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var list: ArrayList<PhotoModel>? = ArrayList()
    private lateinit var viewModel: ViewModalClass
    lateinit var showProgress: ShowProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =  ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(ViewModalClass::class.java)
        showProgress = ShowProgress()

        val manager =  GridLayoutManager(this , 2)
        rv_photos.layoutManager =  manager
        rv_photos.itemAnimator = DefaultItemAnimator()
        rv_photos.isNestedScrollingEnabled = false

        getAndInsertPhotosFromApi()

    }

    private fun getAndInsertPhotosFromApi() {
        viewModel.getPhotos(this).observe(this, androidx.lifecycle.Observer {
            if (it!!.isNotEmpty()) {
                list?.clear()
                list?.addAll(it)
                val adapter = list?.let { it1 -> PhotoAdapter(it1) }
                rv_photos.adapter = adapter
                adapter!!.notifyDataSetChanged()
                showProgress.dismiss()
            }
        })
    }


}