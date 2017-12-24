package com.example.paulina.wisehome.base

import com.example.paulina.wisehome.model.database.Database
import easymvp.RxPresenter

abstract class BaseAbstractPresenter<V : BaseView> : RxPresenter<V>(), BasePresenter {

   /* abstract val presentationModel: BaseModel*/

    val database: Database
        get() = Database()
}