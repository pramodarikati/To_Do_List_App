package com.example.todolistapp.model

import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Note(
    @PrimaryKey
    var id: String? = null,
    var title: String? = "null",
    var content: String? = "null",
) : RealmObject(),Parcelable