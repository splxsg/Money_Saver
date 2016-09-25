package com.blues.money_saver.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.blues.money_saver.data.MoneyContract.MoneyEntry;
/**
 * Created by Blues on 04/09/2016.
 */
public class MoneyDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 5;
    static final String DATABASE_NAME = "money.db";
    public MoneyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_MONEY_TABLE = "CREATE TABLE " + MoneyEntry.TABLE_NAME + " (" +
                MoneyEntry._ID + " INTEGER PRIMARY KEY," +
                MoneyEntry.COLUMN_MONEY_ID + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_DATE_Year + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_DATE_Month + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_DATE_Date + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_AMOUNT + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_CATEGORY + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_DETAILS + " TEXT NOT NULL," +
                MoneyEntry.COLUMN_MONEY_CHANGE_ABLE + " TEXT NOT NULL" +
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_MONEY_TABLE);
        Log.v("MoneyDBHelper","create! ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoneyEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    
}
