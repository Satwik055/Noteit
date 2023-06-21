package com.satwik.noteit.model

import androidx.room.TypeConverter

class Convertors {

    @TypeConverter
    fun fromStringArrayList(list: ArrayList<String>?):String?{
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toStringArrayList(string:String?):ArrayList<String>{
        return arrayListOf(*string?.split(",")?.toTypedArray()?: return ArrayList())
    }

}