package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class RecordsItem(@Json(name = "recordid")
                       val recordid: String? = "",
                       @Json(name = "datasetid")
                       val datasetid: String? = "",
                       @Json(name = "fields")
                       val fields: Fields,
                       @Json(name = "record_timestamp")
                       val recordTimestamp: String? = "")