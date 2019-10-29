package com.popo.untitledandroidproject.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.annotation.Keep
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName= "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private var _id: Long=0L,
    @ColumnInfo(name="email")
    private var _email: String? ="",
    @ColumnInfo(name="password")
    private var _password: String?,
    @ColumnInfo(name = "lastname")
    private var _lastname: String? = "",
    @ColumnInfo(name = "firstname")
    private var _firstname: String? = "",
    @ColumnInfo(name = "birthday_date")
    private var _birthdayDate: Long = 0,
    @ColumnInfo(name = "gender")
    private var _gender: String? = "",
    @ColumnInfo(name="address")
    private var _address: String? ="",
    @ColumnInfo(name="city")
    private var _city:String?="",
    @ColumnInfo(name="country")
    private var _country:String?=""
    ) : Parcelable,
    BaseObservable(){

    var id: Long
        @Bindable get() = _id
        set(value) {
            _id = value
            notifyPropertyChanged(BR.id)
        }
    var email: String?
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }
    var password: String?
        @Bindable get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }
    var lastname: String?
        @Bindable get() = _lastname
        set(value) {
            _lastname = value
            notifyPropertyChanged(BR.lastname)
        }

    var firstname: String?
        @Bindable get() = _firstname
        set(value) {
            _firstname = value
            notifyPropertyChanged(BR.firstname)
        }

    var birthdayDate: Long
        @Bindable get() = _birthdayDate
        set(value) {
            _birthdayDate = value
            notifyPropertyChanged(BR.birthdayDate)
        }
    var gender: String?
    @Bindable get() = _gender
    set(value) {
        _gender = value
        notifyPropertyChanged(BR.gender)
    }
    var address: String?
        @Bindable get() = _address
        set(value) {
            _address = value
            notifyPropertyChanged(BR.gender)
        }
    var city: String?
        @Bindable get() = _city
        set(value) {
            _city = value
            notifyPropertyChanged(BR.gender)
        }
    var country: String?
        @Bindable get() = _country
        set(value) {
            _country = value
            notifyPropertyChanged(BR.gender)
        }


    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_id)
        parcel.writeString(_email)
        parcel.writeString(_password)
        parcel.writeString(_lastname)
        parcel.writeString(_firstname)
        parcel.writeValue(_birthdayDate)
        parcel.writeString(_gender)
        parcel.writeString(_address)
        parcel.writeString(_city)
        parcel.writeString(_country)
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
