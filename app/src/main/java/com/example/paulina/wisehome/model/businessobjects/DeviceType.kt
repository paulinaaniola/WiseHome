package com.example.paulina.wisehome.model.businessobjects

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.utils.ResUtil


enum class DeviceType(val resourceId: Int) {
    LIGHTS(R.string.lights),
    BLINDS(R.string.blinds),
    ALARM_SENSORS(R.string.alarm_sensors),
    WEATHER_SENSORS(R.string.weather);

    fun toStringLocale(): String? {
        return ResUtil.getString(resourceId)
    }

    companion object {
        fun toEnum(name: String): NavDrawerItemType? =
                NavDrawerItemType.values().firstOrNull { ResUtil.getString(it.resourceId).equals(name, ignoreCase = true) }
    }
}