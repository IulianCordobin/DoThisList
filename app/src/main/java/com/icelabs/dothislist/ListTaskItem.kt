package com.icelabs.dothislist

import java.util.Calendar
import java.util.Date

class ListTaskItem() {

    var text: String
    var date: Date
    var complete: Boolean = false

    init {
        this.date = Calendar.getInstance().time
        this.text = ""
        this.complete = false
    }

    constructor(text: String) : this() {
        this.text = text
    }
}
