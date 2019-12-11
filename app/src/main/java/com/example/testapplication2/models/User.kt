package com.example.testapplication2.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity (tableName = "users")
class User : Parcelable {

    @PrimaryKey (autoGenerate = true)
    var id: Int? = null

    @ColumnInfo
    var name: String? = ""

    @ColumnInfo
    var email: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        email = parcel.readString()
    }

    @Ignore
    constructor()
    constructor(id: Int?,
                name: String,
                email: String) {
        this.id = id
        this.name = name
        this.email = email
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}