package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class Fields(@Json(name = "directed_by")
                  val directedBy: String = "",
                  @Json(name = "image")
                  val image: Image?,
                  @Json(name = "year")
                  val year: String = "",
                  @Json(name = "url_poster")
                  val urlPoster: String = "",
                  @Json(name = "produced_by")
                  val producedBy: String = "",
                  @Json(name = "url_en")
                  val urlEn: String? = "",
                  @Json(name = "title")
                  val title: String = "")