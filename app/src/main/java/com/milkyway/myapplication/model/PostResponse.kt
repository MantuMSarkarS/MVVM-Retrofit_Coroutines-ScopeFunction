package com.milkyway.myapplication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
class PostResponse(
    @Embedded
    @SerializedName("data")
    var data: ArrayList<Data>/*,
    @Embedded
    val support: Support*/
)

@Entity(tableName = "data")
data class Data(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String
)

data class Support(
    var url: String,
    var text: String
)