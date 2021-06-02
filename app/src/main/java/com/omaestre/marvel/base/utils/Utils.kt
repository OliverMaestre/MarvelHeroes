package com.omaestre.marvel.base.utils

import android.util.DisplayMetrics
import android.util.TypedValue
import java.math.BigInteger
import java.security.MessageDigest

class Utils {
    companion object{
        fun md5(input:String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')

        }

        fun dpToPixel(sizeInDp: Int, displayMetrics: DisplayMetrics?): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDp.toFloat(), displayMetrics).toInt()
        }
    }
}