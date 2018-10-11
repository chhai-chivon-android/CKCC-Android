package kh.edu.rupp.ckcc.myapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DbManagerLib extends SQLiteAssetHelper {

    public DbManagerLib(Context context) {
        super(context, "myapp.db", null, 1);
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

}
