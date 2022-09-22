package com.martinz.myshoe.model

import androidx.annotation.DrawableRes

data class Shoes(
    var shoesName: String = "",
    var shoesCompany: String = "",
    var shoesSize: Int? = null ,
    var shoesDescription: String = "",
    val shoesPicture : Int? = null
)