package com.example.orendatask.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.orenda.model.PhotoModel
import com.example.orendatask.data.persistant.AppDatabase
import com.example.orendatask.data.persistant.PhotosDao
import com.example.orendatask.data.remote.RetrofitClient
import com.example.orendatask.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Repository(application: Application) {

    private var appDatabase = AppDatabase.getDatabase(application)
    private var retrofitClient = RetrofitClient.getRetrofitClient()
    private var photoDao: PhotosDao = appDatabase.photoDao()

    fun getPhotos(mainActivity: MainActivity): LiveData<List<PhotoModel>?> {
        mainActivity.showProgress.show(mainActivity)

        val thread = Thread {
            val count = photoDao.getCount()
            if (count <= 0) {
                Log.d("MainRepository", "Fetching From Server")
                getPhotosFromNetwork(mainActivity)
            } else {
                Log.d("MainRepository", "Fetching From Local DB")
            }
        }
        thread.start()
        return photoDao.getAllPhotos()

    }

    private fun getPhotosFromNetwork(mainActivity: MainActivity) {
            retrofitClient.getPhotos().enqueue(object : Callback<List<PhotoModel>> {
                override fun onResponse(
                    call: Call<List<PhotoModel>>,
                    response: Response<List<PhotoModel>>
                ) {
                    val photoModels = response.body()
                    if (photoModels != null) {
                        val thread = Thread {
                            photoDao.insert(photoModels)
                        }
                        thread.start()
                    }
                    mainActivity.showProgress.dismiss()
                }

                override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                    mainActivity.showProgress.dismiss()
                    //Toast.makeText(mainActivity ,  t.cause.toString(), Toast.LENGTH_LONG).show()
                }
            })
    }

}