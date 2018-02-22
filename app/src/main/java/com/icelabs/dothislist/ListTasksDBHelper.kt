package com.icelabs.dothislist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * Created by iulian.cordobin on 2/14/2018.
 */

private const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${ListTasksSQL.Tasks.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${ListTasksSQL.Tasks.COLUMN_NAME_TEXT} TEXT," +
                "${ListTasksSQL.Tasks.COLUMN_NAME_DATE} TEXT," +
                "${ListTasksSQL.Tasks.COLUMN_NAME_COMPLETE} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ListTasksSQL.Tasks.TABLE_NAME}"

class ListTasksDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "DoThisList.db"
    }
}

