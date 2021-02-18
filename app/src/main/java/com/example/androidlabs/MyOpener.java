package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class MyOpener extends SQLiteOpenHelper {
    protected final static String DATABASE_NAME = "ChatroomDB";
    protected final static int VERSION_NUM = 1;
    public final static String TABLE_NAME = "CHATROOM";
    public final static String COL_MESSAGE = "message";
    public final static String COL_SENT = "isSent";

    public MyOpener(Context ctx) {

        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_MESSAGE + " TEXT, "
                + COL_SENT + " BOOLEAN);");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    SQLiteDatabase db = getWritableDatabase();

    public long addMessage(String message, Boolean isSent) {
        ContentValues cv = new ContentValues();
        cv.put(COL_MESSAGE, message);
        if(isSent) {
            cv.put(COL_SENT, 0);
        } else {
            cv.put(COL_SENT, 1);
        }
        long id = db.insert(TABLE_NAME, null, cv);
        return id;
    }

    public Cursor printCursor() {

        Cursor c = db.rawQuery("Select * from " + TABLE_NAME, null);

        Log.v("Database version ", String.valueOf(db.getVersion()));
        Log.v("Number of columns ", String.valueOf(c.getColumnCount()));
        for (int i= 0; i < c.getColumnCount(); i++) {
            Log.v("Column names ", c.getColumnName(i));
        }
        Log.v("Rows ", String.valueOf(c.getCount()));

        return c;
    }
}
