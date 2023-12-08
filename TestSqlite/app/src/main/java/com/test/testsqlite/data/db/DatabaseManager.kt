package com.test.testsqlite.data.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.test.testsqlite.data.db.DataBaseConstances.COLUMN_NAME_SUBTITLE
import com.test.testsqlite.data.db.DataBaseConstances.COLUMN_NAME_TITLE
import com.test.testsqlite.data.db.DataBaseConstances.TABLE_NAME
import com.test.testsqlite.data.entities.Note

class DatabaseManager(
    val dataBaseHelper: DataBaseHelper
) {
    private var db: SQLiteDatabase? = null

    fun insertNote(note: Note) {
        db = dataBaseHelper.writableDatabase //open DataBase for writing
        val values = ContentValues().apply {
            put(COLUMN_NAME_TITLE, note.title)
            put(COLUMN_NAME_SUBTITLE, note.subtitle)
        }
        db?.insert(TABLE_NAME, null, values)
        db?.close()
    }


    @SuppressLint("Range")
    fun receiveNotes(): ArrayList<Note> {
        db = dataBaseHelper.readableDatabase //open DataBase for reading
        val cursor = db?.query(TABLE_NAME, null, null, null, null, null, null)
        val titleList = ArrayList<Note>()
        while (cursor!!.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE))
            val subtitle = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SUBTITLE))
            val note = Note(title, subtitle)
            titleList.add(note)
        }
        cursor.close()
        db?.close()
        return titleList
    }

    fun deleteNotesFromDataBase() {
        db = dataBaseHelper.writableDatabase //open DataBase for writing or deleting ha-ha-ha
        db?.delete(TABLE_NAME,null,null)
        db?.close()
    }

}