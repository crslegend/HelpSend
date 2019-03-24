package com.helpsend.app4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class RequestListHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "request_list";
    private static final String TABLE_NAME = "requests";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_TIMEDATE = "timedate";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_CONTACT, KEY_LEVEL, KEY_SUBJECT, KEY_LOCATION, KEY_TIMEDATE };

    public RequestListHandler (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME + " TEXT, " +
                KEY_CONTACT + " TEXT, " +
                KEY_LEVEL + " TEXT, " +
                KEY_SUBJECT + " TEXT, " +
                KEY_LOCATION + " TEXT, " +
                KEY_TIMEDATE + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteRequest(Request request) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[] { String.valueOf(request.getId()) });
        db.close();
    }

    public Request getRequest(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Request request = new Request();
        request.setId(Integer.parseInt(cursor.getString(0)));
        request.setName(cursor.getString(1));
        request.setContact(cursor.getString(2));
        request.setLevel(cursor.getString(3));
        request.setSubject(cursor.getString(4));
        request.setLocation(cursor.getString(5));
        request.setTimedate(cursor.getString(6));


        return request;
    }

    public List<Request> allRequests() {

        List<Request> requests = new LinkedList<Request>();
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Request request = null;

        if (cursor.moveToFirst()) {
            do {
                request = new Request();
                request.setId(Integer.parseInt(cursor.getString(0)));
                request.setName(cursor.getString(1));
                request.setContact(cursor.getString(2));
                request.setLevel(cursor.getString(3));
                request.setSubject(cursor.getString(4));
                request.setLocation(cursor.getString(5));
                request.setTimedate(cursor.getString(6));
                requests.add(request);
            } while (cursor.moveToNext());
        }

        return requests;
    }

    public void addRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, request.getName());
        values.put(KEY_CONTACT, request.getContact());
        values.put(KEY_LEVEL, request.getLevel());
        values.put(KEY_SUBJECT, request.getSubject());
        values.put(KEY_LOCATION, request.getLocation());
        values.put(KEY_TIMEDATE, request.getTimedate());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM " + TABLE_NAME;
        db.execSQL(delete);
    }
}
