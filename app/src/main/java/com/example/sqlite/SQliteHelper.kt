package com.example.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.math.E

class SQliteHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "student.db"
        private const val DATABASE_VERSION  =1
        private const val TBL_STUDENT = "tbl_student"
        private const val ID ="id"
        private const val NAME = "name"
        private const val EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblstudent = (
                "CREATE TABLE " + TBL_STUDENT+ "("+
                        ID +" INTEGER PRIMARY KEY,"+ NAME + " TEXT,"
                                + EMAIL +" TEXT,"

                )
         db?.execSQL(createTblstudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
       db!!.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std:StudentMode):Long
    {
        val db= this.writableDatabase
        val contentValues =ContentValues()
        contentValues.put(ID,std.id)
        contentValues.put(NAME,std.name)
        contentValues.put(EMAIL,std.email)

        val succes = db.insert( TBL_STUDENT,null,contentValues)
        db.close()
        return succes
    }


     @SuppressLint("Range")
     fun getAllStudnet():ArrayList<StudentMode>
     {
         val stdList : ArrayList<StudentMode> = ArrayList()
         val selectQuery = "SELECT * FROM $TBL_STUDENT"
         val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
         var id:Int
         var name :String
         var email:String

         if(cursor.moveToFirst()){

             do {
                 id = cursor.getInt(cursor.getColumnIndex("id"))
                 name = cursor.getString(cursor.getColumnIndex("name"))
                 email = cursor.getString(cursor.getColumnIndex("email"))
                 val std = StudentMode(id = id, name = name, email = email)
                 stdList.add(std)
             }while (cursor.moveToNext())
         }
return stdList
     }
}