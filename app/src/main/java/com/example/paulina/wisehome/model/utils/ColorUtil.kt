package com.example.paulina.wisehome.model.utils

import android.graphics.Color
import com.example.paulina.wisehome.model.transportobjects.RGBColor

object ColorUtil {

    fun intToHex(colorInt: Int): String {
        return java.lang.Integer.toHexString(colorInt)
    }

    fun intToRgb(colorInt: Int): RGBColor {
        return RGBColor(Color.red(colorInt).toString(), Color.green(colorInt).toString(), Color.blue(colorInt).toString())
    }

    fun rgbToInt(rgbColor: RGBColor): Int {
        var red: Int = rgbColor.red.toInt()
        var green: Int = rgbColor.green.toInt()
        var blue: Int = rgbColor.blue.toInt()
        return Color.argb(255, red, green, blue)
    }
}