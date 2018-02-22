package com.icelabs.dothislist

import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class ListActivity : android.app.ListActivity() {

    var dbHelper = ListTasksDBHelper (this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        var tasksAdapter = ListTasksAdapter(this)

        tasksAdapter.readDatabase()

        this.listAdapter = tasksAdapter

        val buttonAddTask = findViewById<View>(R.id.buttonAddTask) as Button

        buttonAddTask.setOnClickListener {

            val editTextAddTask = findViewById<View>(R.id.editTextAddTask) as EditText

            tasksAdapter.addItem(ListTaskItem(editTextAddTask.text.toString()))

            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(ListTasksSQL.Tasks.COLUMN_NAME_TEXT, editTextAddTask.text.toString())
            }

            val newRowId = db?.insert(ListTasksSQL.Tasks.TABLE_NAME, null, values)

            editTextAddTask.setText("")

            tasksAdapter.notifyDataSetChanged()
        }

        val clearButton = findViewById<View>(R.id.buttonClearTasks) as Button

        clearButton.setOnClickListener(){
            val db = dbHelper.writableDatabase

            tasksAdapter.clearEntries()

            tasksAdapter.notifyDataSetChanged()
        }


    }



    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

}
