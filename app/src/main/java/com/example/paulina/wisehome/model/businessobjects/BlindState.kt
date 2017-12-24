package com.example.paulina.wisehome.model.businessobjects

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.utils.ResUtil


enum class BlindState(val resourceId : Int) {
    OPEN(R.string.open),
    CLOSED(R.string.closed),
    PARTLY_CLOSE(R.string.partly_closed),
    MOVING(R.string.moving);

    fun toStringLocale(): String? {
        return ResUtil.getString(resourceId)
    }

    companion object {
        fun toEnum(name: String): NavDrawerItemType? =
                NavDrawerItemType.values().firstOrNull { ResUtil.getString(it.resourceId).equals(name, ignoreCase = true) }
    }
}