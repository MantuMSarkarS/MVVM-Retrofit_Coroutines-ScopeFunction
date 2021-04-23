package com.milkyway.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class PostResponse(
    @Embedded
val data: Data,
    @Embedded
    val support: Support
)

@Entity(tableName = "data")
data class Data(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String
)

@Entity(tableName = "aupport")
data class Support(
    var url: String,
    var text: String
)