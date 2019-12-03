package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class Image(@Json(name = "thumbnail")
                 val thumbnail: Boolean? = false,
                 @Json(name = "filename")
                 val filename: String? = "",
                 @Json(name = "format")
                 val format: String? = "",
                 @Json(name = "width")
                 val width: Int? = 0,
                 @Json(name = "id")
                 val id: String? = "",
                 @Json(name = "height")
                 val height: Int? = 0)