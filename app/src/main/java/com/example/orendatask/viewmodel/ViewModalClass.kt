package com.example.orendatask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.orenda.model.PhotoModel
import com.example.orendatask.repository.Repository
import com.example.orendatask.view.MainActivity

class ViewModalClass(application: Application) : AndroidViewModel(application) {

    private var mainRepository: Repository = Repository(application)

    fun getPhotos(mainActivity: MainActivity): LiveData<List<PhotoModel>?> {
        return mainRepository.getPhotos(mainActivity)
    }

}