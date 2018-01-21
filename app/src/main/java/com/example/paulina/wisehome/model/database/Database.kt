package com.example.paulina.wisehome.model.database

import com.example.paulina.wisehome.model.businessobjects.AccountType
import io.paperdb.Paper


class Database {
    fun getAccountType(): AccountType {
        return Paper.book().read(DatabaseKeys.ACCOUNT_TYPE.toString())
    }

    fun putAccountType(accountType: AccountType) {
        Paper.book().write(DatabaseKeys.ACCOUNT_TYPE.toString(), accountType)
    }

    fun putLoggedUsername(username: String) {
        Paper.book().write(DatabaseKeys.USERNAME.toString(), username)
    }

    fun getUsername(): String {
        return Paper.book().read(DatabaseKeys.USERNAME.toString())
    }
}