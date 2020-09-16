package com.example.orendatask.data.persistant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orenda.model.PhotoModel

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photomodel")
    fun getAllPhotos(): LiveData<List<PhotoModel>?>

    @Insert
    fun insert(list: List<PhotoModel>?)

    @Query("SELECT COUNT(*) FROM photomodel")
    fun getCount() : Long
}