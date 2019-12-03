package com.popo.untitledandroidproject.model.OpenDataModels

import com.squareup.moshi.Json

data class FacetGroupsItem(@Json(name = "name")
                           val name: String = "",
                           @Json(name = "facets")
                           val facets: List<FacetsItem>?)