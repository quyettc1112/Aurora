package com.aurora.aurora.Model.RequestDTO

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class OrderDetailsDTO(
    @SerializedName("deliveryLocation") var deliveryLocation: String,
    @SerializedName("orderDetails") val orderDetails: List<OrderDetail>,
    @SerializedName("note") val note: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.createTypedArrayList(OrderDetail.CREATOR) ?: listOf(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(deliveryLocation)
        parcel.writeTypedList(orderDetails)
        parcel.writeString(note)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDetailsDTO> {
        override fun createFromParcel(parcel: Parcel): OrderDetailsDTO {
            return OrderDetailsDTO(parcel)
        }

        override fun newArray(size: Int): Array<OrderDetailsDTO?> {
            return arrayOfNulls(size)
        }
    }
}

data class OrderDetail(
    @SerializedName("toyId")   val toyId: String,
    @SerializedName("quantity")   val quantity: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(toyId)
        parcel.writeInt(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderDetail> {
        override fun createFromParcel(parcel: Parcel): OrderDetail {
            return OrderDetail(parcel)
        }

        override fun newArray(size: Int): Array<OrderDetail?> {
            return arrayOfNulls(size)
        }
    }
}