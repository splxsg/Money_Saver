package com.blues.money_saver;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.blues.money_saver.data.MoneyContract;
import com.blues.money_saver.data.MoneyContract.SummaryEntry;

import java.util.Vector;

import static com.blues.money_saver.CategoryFragment.LOG_TAG;

/**
 * Created by Blues on 21/09/2016.
 */
public class Utility {
    public static String[] Monthontab={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    private static String category;
    private static int tabind;

    public static String monthConvert(int month)
    {
        if(month >=1 && month <=12)
       return Monthontab[month];
        else
            return "none";
    }

    public static void setCategory(String cat){category=cat;}

    public static String getCategory(){return category;}

    public static void setTabindex(int tabindex){tabind=tabindex;
    Log.v("SETUTILITY",tabind+"");}

    public static int getTabindex(){return tabind;}


    public static void updateSummary(Context context,String month)
    {
        Cursor mMoneyCursor;
        float income,payout,balance;
        String select_month, income_str,loan_str;
        Uri summaryUri,moneyUri;
        int columnCategory, columnAmount;
        String strcolCategory,strcolAmount;

        summaryUri = MoneyContract.SummaryEntry.CONTENT_URI;
        moneyUri  = MoneyContract.MoneyEntry.CONTENT_URI;

        select_month = MoneyContract.MoneyEntry.COLUMN_MONEY_DATE_Month;
        income_str = context.getString(R.string.nav_income_str);
        loan_str = context.getString(R.string.nav_loan_str);

        income = 0f;
        payout = 0f;
        mMoneyCursor = context.getContentResolver().query(
                moneyUri,
                null,
                select_month + "=?",
                new String[] {month},
                null);

        columnCategory = mMoneyCursor.getColumnIndex(MoneyContract.MoneyEntry.COLUMN_MONEY_CATEGORY);
        columnAmount = mMoneyCursor.getColumnIndex(MoneyContract.MoneyEntry.COLUMN_MONEY_AMOUNT);
        if(mMoneyCursor.moveToFirst())
        {
                while(!mMoneyCursor.isAfterLast())
                {
                    strcolCategory = mMoneyCursor.getString(columnCategory);
                    strcolAmount = mMoneyCursor.getString(columnAmount);
                    if(strcolCategory != income_str ||
                    strcolCategory!= loan_str)
                    {
                        payout += Float.parseFloat(strcolAmount);
                    }
                    else
                    {
                        income += Float.parseFloat(strcolAmount);
                    }
                }
        }

        balance = income - payout;

        if(mMoneyCursor.moveToFirst())
        {
            Vector<ContentValues> cVVector = new Vector<ContentValues>(1);
            ContentValues moneyValues = new ContentValues();

            moneyValues.put(SummaryEntry.COLUMN_SUMMARY_INCOME,income);
            moneyValues.put(SummaryEntry.COLUMN_SUMMARY_PAYOUT,payout);
            moneyValues.put(SummaryEntry.COLUMN_SUMMARY_BALANCE,balance);

            cVVector.add(moneyValues);
            if(context.getContentResolver().update(summaryUri,
                    moneyValues,
                    select_month + "=?",
                    new String[] {month}) != -1)
                Log.d(LOG_TAG, "Overview has been updated");
        }

    }

}
