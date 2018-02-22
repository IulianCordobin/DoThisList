package com.icelabs.dothislist

import android.app.Activity
import android.provider.BaseColumns
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import java.util.ArrayList

class ListTasksAdapter : BaseAdapter {

    private var parentActivity: ListActivity
    private var listItems: ArrayList<ListTaskItem>

    private constructor() {
        this.parentActivity = ListActivity()
        this.listItems = ArrayList<ListTaskItem>()
    }

    constructor(activity: ListActivity) : this() {
        this.parentActivity = activity
    }

    override fun getItem(position: Int): String {
        return this.listItems[position].text
    }

    override fun getItemId(position: Int): Long {
        return this.listItems[position].hashCode().toLong()
    }

    override fun getCount(): Int {
        return this.listItems.size
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = this.parentActivity.layoutInflater.inflate(R.layout.custom_list_item, container, false)
        }

        (convertView!!.findViewById<View>(R.id.itemText) as TextView).text = getItem(position)

        return convertView
    }

    fun addItem(newTask: ListTaskItem) {
        listItems.add(newTask)
    }

    fun readDatabase(){

        val db = parentActivity.dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, ListTasksSQL.Tasks.COLUMN_NAME_TEXT)

        //val selection = "${ListTasksSQL.Tasks.COLUMN_NAME_COMPLETE} = ?"
        //val selectionArgs = arrayOf("done")

        //val sortOrder = "${ListTasksSQL.Tasks.COLUMN_NAME_DATE} DESC"

        val cursor = db.query(
                ListTasksSQL.Tasks.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        )

        val itemIds = mutableListOf<Long>()

        with(cursor) {
            while(moveToNext()){
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val text = getString(getColumnIndexOrThrow(ListTasksSQL.Tasks.COLUMN_NAME_TEXT))
                itemIds.add(itemId)

                this@ListTasksAdapter.addItem(com.icelabs.dothislist.ListTaskItem(text))
            }
        }
    }

    fun clearEntries()
    {
        val db = parentActivity.dbHelper.readableDatabase

        val selection = "${ListTasksSQL.Tasks.COLUMN_NAME_COMPLETE} LIKE ?"
        val selectionArgs = arrayOf("")

        //db.delete(ListTasksSQL.Tasks.TABLE_NAME, selection, selectionArgs)

        db.execSQL("DELETE FROM ${ListTasksSQL.Tasks.TABLE_NAME}")

        listItems.clear()
    }

    /*


val db = dbHelper.writableDatabase

// New value for one column
val values = ContentValues().apply {
    put(FeedEntry.COLUMN_NAME_TITLE, title)
}

// Which row to update, based on the title
val selection = "${FeedEntry.COLUMN_NAME_TITLE} LIKE ?"
val selectionArgs = arrayOf("MyTitle")
val count = db.update(
        FeedEntry.TABLE_NAME,
        values,
        selection,
        selectionArgs)

Persisting Da
     */
}
