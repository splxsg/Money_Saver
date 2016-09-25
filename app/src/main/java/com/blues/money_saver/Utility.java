package com.blues.money_saver;

import android.util.Log;

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

}
