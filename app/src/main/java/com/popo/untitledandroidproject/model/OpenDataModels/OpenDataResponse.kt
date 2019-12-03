package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class OpenDataResponse(@Json(name = "nhits")
                            val nhits: Int = 0,
                            @Json(name = "records")
                            val records: List<RecordsItem>?,
                            @Json(name = "parameters")
                            val parameters: Parameters)