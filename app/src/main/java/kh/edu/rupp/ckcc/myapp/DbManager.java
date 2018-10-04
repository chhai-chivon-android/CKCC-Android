package kh.edu.rupp.ckcc.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {

    // DB Schema

    public DbManager(Context context) {
        super(context, "mydb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create event table
        String sql = "create table tblEvent(id integer primary key autoincrement, " +
                "title text, imageUrl text, date text, location text)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // Data Manipulation
    public Event[] getAllEvents() {
        // Get DB object
        SQLiteDatabase db = getReadableDatabase();

        // Select data
        Cursor cursor = db.query("tblEvent", null, null, null, null, null, "id desc");
        // Cursor cursor = db.rawQuery("select * from tblEvent order by id desc", null);

        Event[] events = new Event[cursor.getCount()];
        int index = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String imageUrl = cursor.getString(2);
            String date = cursor.getString(3);
            String location = cursor.getString(4);

            Event event = new Event(id, title, imageUrl, date, location, "");
            events[index] = event;
            index++;
        }

        // Free up cursor
        cursor.close();

        return events;
    }

    public void insertTemporaryData() {

        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < 10; i++) {
            db.execSQL("insert into tblEvent(title, imageUrl, date, location) values " +
                    "('Event " + i + "', '', '" + i + " April 2018', 'CKCC')");
        }

    }

}
