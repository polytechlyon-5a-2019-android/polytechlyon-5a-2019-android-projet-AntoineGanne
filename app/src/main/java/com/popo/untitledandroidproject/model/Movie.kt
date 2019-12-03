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
import com.popo.untitledandroidproject.model.OpenDataModels.OpenDataResponse


@Keep
@Entity(tableName= "movie")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "title")
    private var _title: String="",
    @ColumnInfo(name="img")
    private var _img_src: String? ="",
    @ColumnInfo(name="year")
    private var _year: Int? =0,
    @ColumnInfo(name = "director")
    private var _directed_by: String? = "",
    @ColumnInfo(name = "wikiUrl")
    private var _wikiUrl: String? = ""
) : Parcelable,
    BaseObservable(){

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }
    var img: String?
        @Bindable get() = _img_src
        set(value) {
            _img_src = value
            notifyPropertyChanged(BR.img)
        }
    var year: Int?
        @Bindable get() = _year
        set(value) {
            _year = value
            notifyPropertyChanged(BR.year)
        }
    var director: String?
        @Bindable get() = _directed_by
        set(value) {
            _directed_by = value
            notifyPropertyChanged(BR.director)
        }

    var wikiUrl: String?
        @Bindable get() = _wikiUrl
        set(value) {
            _wikiUrl = value
            notifyPropertyChanged(BR.wikiUrl)
        }


    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(_title)
        parcel.writeString(_img_src)
        _year?.let { parcel.writeInt(it) }
        parcel.writeString(_directed_by)
        parcel.writeString(_wikiUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
