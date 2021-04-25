package com.milkyway.myapplication.utils

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.milkyway.myapplication.model.Data

class TypeConverters {
    @TypeConverter
    fun fromStringToArrayList(value: String): ArrayList<Data> {
        Log.i("alengenije", "fromStringToArrayList string = $value")
        val listType = object:TypeToken<ArrayList<Data>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisrToString(list: ArrayList<Data>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}