package com.example.hackchallenge

import android.os.Parcel
import android.os.Parcelable

data class BucketItem(val name: String?, val description: String?, var isComplete: Boolean = false) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeByte(if (isComplete) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BucketItem> {
        override fun createFromParcel(parcel: Parcel): BucketItem {
            return BucketItem(parcel)
        }

        override fun newArray(size: Int): Array<BucketItem?> {
            return arrayOfNulls(size)
        }
    }
}