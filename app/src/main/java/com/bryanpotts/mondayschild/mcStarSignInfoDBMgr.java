package com.bryanpotts.mondayschild;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by bryan on 16/10/2014.
 */
public class mcStarSignsInfoDBMgr extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.bryanpotts.mondayschild/databases/";
    private static final String DB_NAME = "starsigns.s3db";
    private static final String TBL_STARSIGNSINFO = "starsignsinfo";

    public static final String COL_STARSIGNID = "starSignID";
    public static final String COL_STARSIGN = "starSign";
    public static final String COL_STARSIGNIMG = "starSignImg";
    public static final String COL_STARSIGNDATES = "starSignDates";
    public static final String COL_STARSIGNCHARACTERISTICS = "starSignCharacteristics";

    private final Context appContext;


    // ***********
    // Constructor
    // ***********
    public mcStarSignsInfoDBMgr(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    // This is called once upon application start.
    // It creates an empty SQL query string then excecutes.
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STARSIGNSINFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_STARSIGNSINFO + "("
                + COL_STARSIGNID + " INTEGER PRIMARY KEY," + COL_STARSIGN
                + " TEXT," + COL_STARSIGNIMG + " TEXT," + COL_STARSIGNDATES + " TEXT,"
                + COL_STARSIGNCHARACTERISTICS + " TEXT" + ")";
        db.execSQL(CREATE_STARSIGNSINFO_TABLE);
    }

    // Method is required when database requires an upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_STARSIGNSINFO);
            onCreate(db);
        }
    }

    // =============================================================================
    // Creates an empty database on the system and rewrites it with your own database
    // =============================================================================
    // region
    public void dbCreate () throws IOException {

        boolean dbExist = dbCheck();

        if(!dbExist) {
            // By calling this method an empty database will be created into the default system path
            // of your application so we can overwrite that database with our database.

            this.getReadableDatabase();

            try {
                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }
    }

    // endregion

    // ====================================================================================================
    // Check if the database already exists to avoid re-copying the file each time you open the application
    // ====================================================================================================
    // region
    private boolean dbCheck() {

        SQLiteDatabase db = null;

        try {
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);

        }catch (SQLiteException e) {

            Log.e("SQLHelper", "Database not found");
        }

        if(db != null) {

            db.close();

        }

        return db != null ? true : false;
    }

    // endregion
    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    // region
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try {
            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            // Transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);

            }

            // Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();


        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }

    // endregion
    // =============================
    // Adds new records to the table
    // =============================
    // region
    public void addStarSignInfo(mcStarSignInfo aStarSignInfo) {
        ContentValues values = new ContentValues();
        values.put(COL_STARSIGN, aStarSignInfo.getStarSign());
        values.put(COL_STARSIGNIMG, aStarSignInfo.getStarSignImg());
        values.put(COL_STARSIGNDATES, aStarSignInfo.getStarSignDates());
        values.put(COL_STARSIGNCHARACTERISTICS, aStarSignInfo.getStarSignCharacteristics());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_STARSIGNSINFO, null, values);
        db.close();
    }

    // endregion
//region
    public mcStarSignInfo findStarSign(String sStarSign) {
        String query = "Select * FROM " + TBL_STARSIGNSINFO + " WHERE " + COL_STARSIGN +" = \"" + sStarSign + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        mcStarSignInfo StarSignInfo = new mcStarSignInfo();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            StarSignInfo.setStarSignID(Integer.parseInt(cursor.getString(0)));
            StarSignInfo.setStarSign(cursor.getString(1));
            StarSignInfo.setStarSignImg(cursor.getString(2));
            StarSignInfo.setStarSignDates(cursor.getString(3));
            StarSignInfo.setStarSignCharacteristics(cursor.getString(4));
            cursor.close();

        } else {
            StarSignInfo = null;
        }

        db.close();
        return  StarSignInfo;

    }

// endregion
    public boolean removeStarSign(String sStarSign) {

        boolean result = false;

        String query = "Select * FROM " + TBL_STARSIGNSINFO + " WHERE " + COL_STARSIGN + " = \"" + sStarSign + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        mcStarSignInfo StarSignInfo = new mcStarSignInfo();

        if (cursor.moveToFirst()) {
            StarSignInfo.setStarSignID(Integer.parseInt(cursor.getString(0)));
            db.delete(TBL_STARSIGNSINFO, COL_STARSIGNID + " = ?",
                    new String[] {String.valueOf(StarSignInfo.getStarSignID())});
            cursor.close();
            result = true;
        }

        db.close();
        return result;
    }

    // endregion
}
