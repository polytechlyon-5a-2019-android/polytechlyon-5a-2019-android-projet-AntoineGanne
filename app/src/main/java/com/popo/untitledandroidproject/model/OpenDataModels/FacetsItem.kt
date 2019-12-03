package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class FacetsItem(@Json(name = "path")
                      val path: String = "",
                      @Json(name = "count")
                      val count: Int = 0,
                      @Json(name = "name")
                      val name: String = "",
                      @Json(name = "state")
                      val state: String = "")