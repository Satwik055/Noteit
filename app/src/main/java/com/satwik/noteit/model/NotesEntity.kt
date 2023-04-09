package com.satwik.noteit.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notes_id" )
    var id:Int? = null,

    @ColumnInfo(name = "notes_title" )
    var title:String,

    @ColumnInfo(name = "notes_content" )
    var content:String
):Parcelable