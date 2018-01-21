package com.example.paulina.wisehome.model.businessobjects

import com.example.paulina.wisehome.R
import com.example.paulina.wisehome.model.utils.ResUtil

enum class NavDrawerItemType(var resourceId: Int) {
    MY_ROOMS(R.string.my_rooms),
    ADD_ROOM(R.string.add_room),
    ADD_DEVICE(R.string.add_device),
    LOGOUT(R.string.logout),
    ADD_ACCOUNT(R.string.add_account),
    CHANGE_PASSWORD(R.string.change_password);

    fun toStringLocale(): String? {
        return ResUtil.getString(resourceId)
    }

    companion object {
        fun toEnum(name: String): NavDrawerItemType? =
                NavDrawerItemType.values().firstOrNull { ResUtil.getString(it.resourceId).equals(name, ignoreCase = true) }
    }
}