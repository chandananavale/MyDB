package com.example.chandana.mydbtwo.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chandana.mydbtwo.model.Employee;

import java.util.ArrayList;


/**
 * Created by Chandana on 28-04-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyEmployee.db";
    public static final int DATABASE_VERSION = 2;

    public static final String CONTACTS_TABLE_NAME = "Employee_Details";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMP_ID = "emp_id";
    public static final String CONTACTS_COLUMN_MOBILE = "mobile";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STATE = "state";
    public static final String CONTACTS_COLUMN_CITY = "city";

    private String CREATE_TABLE_QUERY = "CREATE TABLE " + CONTACTS_TABLE_NAME + "( " +
            CONTACTS_COLUMN_ID + " INTEGER PRIMARY KEY, " +
            CONTACTS_COLUMN_NAME + " TEXT, " +
            CONTACTS_COLUMN_EMP_ID + " TEXT, " +
            CONTACTS_COLUMN_MOBILE + " TEXT, " +
            CONTACTS_COLUMN_EMAIL + " TEXT, " +
            CONTACTS_COLUMN_STATE + " TEXT, " +
            CONTACTS_COLUMN_CITY + " TEXT "
            + " )";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
        db.execSQL(CREATE_TABLE_QUERY);
    }

    private ContentValues getContentValues(Employee employee) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NAME, employee.getName());
        contentValues.put(CONTACTS_COLUMN_EMP_ID,employee.getEmpId());
        contentValues.put(CONTACTS_COLUMN_MOBILE, employee.getMobileNumber());
        contentValues.put(CONTACTS_COLUMN_EMAIL, employee.getEmail());
        contentValues.put(CONTACTS_COLUMN_STATE, employee.getState());
        contentValues.put(CONTACTS_COLUMN_CITY, employee.getCity());
        return contentValues;
    }

    public boolean save(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(CONTACTS_TABLE_NAME, null, getContentValues(employee));
        return true;
    }

    public Employee findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME + " WHERE " + CONTACTS_COLUMN_ID + "=?", new String[]{id + ""});
        Employee employee = null;
        if (cursor.moveToNext()) {
            employee = moveCursor(cursor);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return employee;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean update(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(CONTACTS_TABLE_NAME, getContentValues(employee), CONTACTS_COLUMN_ID + " = ? ", new String[]{Integer.toString(employee.getId())});
        return true;
    }

    public Integer deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CONTACTS_TABLE_NAME, CONTACTS_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<Employee> getAllCotacts() {
        ArrayList<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            employees.add(moveCursor(cursor));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return employees;
    }

    private Employee moveCursor(Cursor cursor) {
        Employee employee = new Employee();
        int index = 0;
        employee.setId(cursor.getInt(index++));
        employee.setName(cursor.getString(index++));
        employee.setEmpId(cursor.getString(index++));
        employee.setMobileNumber(cursor.getString(index++));
        employee.setEmail(cursor.getString(index++));
        employee.setState(cursor.getString(index++));
        employee.setCity(cursor.getString(index++));
        return employee;
    }
}




