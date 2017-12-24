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
}