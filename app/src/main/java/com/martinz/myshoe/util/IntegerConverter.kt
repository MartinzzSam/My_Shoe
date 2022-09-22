package com.martinz.myshoe.util

import androidx.databinding.InverseMethod

object IntegerConverter {

    @InverseMethod("stringToInt")
    @JvmStatic
    fun integerToString(int : Int?) : String {
        if (int != null) {
            return int.toString()
        }
        return ""
    }

    @JvmStatic
    fun stringToInt(string: String) : Int? {

        if (string.isNotBlank()) {
            return string.toInt()
        }
        return null
    }
}