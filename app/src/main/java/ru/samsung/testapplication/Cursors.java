package ru.samsung.testapplication;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

public class Cursors {

    public static String getValue(Activity a, String key) {
        Cursor cursor = null;
        try {
            cursor = a.getContentResolver().query(a.getIntent().getData(), new String[]{key}, null, null, null);
            cursor.moveToFirst();
            return cursor.getColumnCount() > 0 ? cursor.getString(0) : null;
        } catch (Exception e) {
            Log.d("My", "cursor error");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }
}
