package com.icelabs.dothislist

import android.provider.BaseColumns

/**
 * Created by iulian.cordobin on 2/14/2018.
 */

object ListTasksSQL {

    object Tasks : BaseColumns {
        const val TABLE_NAME = "Tasks"
        const val COLUMN_NAME_TEXT = "text"
        const val COLUMN_NAME_DATE = "date"
        const val COLUMN_NAME_COMPLETE = "complete"
    }
}
