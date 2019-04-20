package com.example.programproductdevelopmentlab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Array;
import java.sql.SQLInput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class StudentDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "STUDENT_DATABASE";
    private static final String TABLE_NAME = "Student_Table";
    private static final String COLUMN_STUDENT_ID = "Student_ID";
    private static final String COLUMN_STUDENT_FIO = "Student_FIO";
    private static final String COLUMN_STUDENT_ADDTIME = "Adding_Time";

    public StudentDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DB", "onCreate CALLED");
        String script = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_STUDENT_FIO + " String," + COLUMN_STUDENT_ADDTIME + " Text DEFAULT 0)";

        db.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DB", "onUpgrade CALLED");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Adding new note to database from students list
    public void addNote(ArrayList<String> students_list) {
        Log.i("DB", "addNote CALLED");
        SQLiteDatabase db = this.getWritableDatabase();
        Random random = new Random();
        int diff = students_list.size() - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss");
        String addTime = sdf.format(new Date());
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_FIO, students_list.get(random.nextInt(diff + 1)));
        values.put(COLUMN_STUDENT_ADDTIME, addTime);
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("DB", "Added note in database: " + values);
    }

    // Adding 5 new notes to database from students list
    public void add5Notes(ArrayList<String> students_list) {
        Log.i("DB", "add5Notes CALLED");
        SQLiteDatabase db = this.getWritableDatabase();
        Random random = new Random();
        int diff = students_list.size() - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss");
        String addTime = sdf.format(new Date());
        ContentValues values = new ContentValues();
        for (int i = 0; i < 5; i++) {
            values.put(COLUMN_STUDENT_FIO, students_list.get(random.nextInt(diff + 1)));
            values.put(COLUMN_STUDENT_ADDTIME, addTime);
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
        Log.d("DB", "Added 5 notes in database: " + values);
    }

    // Updating last note with new name
    public void updateLastNote(String new_student) {
        Log.i("DB", "updateLastNote CALLED");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " set " + COLUMN_STUDENT_FIO + " = '" + new_student
                + "' WHERE " + COLUMN_STUDENT_ID + " = (SELECT MAX( " + COLUMN_STUDENT_ID + ") FROM " +TABLE_NAME + ")");
        Log.d("DB", "Updated day in database: Updated Note = " + new_student);
    }

    // Getting all students data
    public ArrayList<StudentsList> getAllStudents() {
        Log.i("DB", "getAllStudents CALLED");
        ArrayList<StudentsList> listOfStudents = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StudentsList student = new StudentsList();
                student.student_ID = cursor.getInt(0);
                student.student_FIO = cursor.getString(1);
                student.student_Adding_Time = cursor.getString(2);
                listOfStudents.add(student);
            } while (cursor.moveToNext());
        }
        Log.d("DB", "HISTORY: " + listOfStudents);
        return listOfStudents;
    }

    // Deleting all notes from database
    public void deleteAllNotes() {
        Log.i("DB", "deleteAllNotes CALLED");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        // Resetting auto increment
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
    }

}
