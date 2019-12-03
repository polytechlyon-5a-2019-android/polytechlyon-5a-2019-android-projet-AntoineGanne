package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class Parameters(@Json(name = "timezone")
                      val timezone: String = "",
                      @Json(name = "format")
                      val format: String = "",
                      @Json(name = "rows")
                      val rows: Int = 0,
                      @Json(name = "dataset")
                      val dataset: List<String>?,
                      @Json(name = "facet")
                      val facet: List<String>?)