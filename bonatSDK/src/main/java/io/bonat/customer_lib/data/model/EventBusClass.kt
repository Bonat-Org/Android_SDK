package io.bonat.customer_lib.data.model

import android.os.Parcel
import android.os.Parcelable

class EventBusClass() : Parcelable {
    var data: Any? = null
    var type: String? = null

    constructor(data: Any?, type: String?) : this() {
        this.data = data
        this.type = type
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventBusClass> {
        override fun createFromParcel(parcel: Parcel): EventBusClass {
            return EventBusClass(parcel)
        }

        override fun newArray(size: Int): Array<EventBusClass?> {
            return arrayOfNulls(size)
        }
    }
}