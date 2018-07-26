package com.develpoment.gobolabali.fundamentalstatistic.Helpers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Rian on 03/07/2018.
 */

public class Utils {
    public static void verifyStoragePermissions(Activity activity) {
        String[] PERMISSIONS_STORAGE = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        if (ContextCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static String convertPositionNumber(String name, String team_pos) {
        String res = "";
        String num = "";
        String order = "";
        if (name.equals("11")) {
            res = "GK";
        } else {
            try {
                Integer first = Integer.valueOf(name.substring(0, 1));
                Integer sec = Integer.valueOf(name.substring(1));
                String ftext = "";
                String stext = "";
                switch (first.intValue()) {
                    case 2:
                        ftext = "D";
                        break;
                    case 3:
                        ftext = "M";
                        break;
                    case 4:
                        ftext = "F";
                        break;
                }
                if (sec.intValue() == 2 || sec.intValue() == 3) {
                    stext = "F";
                }
                if (team_pos.equals("left")) {
                    if (sec.intValue() == 1) {
                        stext = "F";
                    } else if (sec.intValue() == 4) {
                        stext = "F";
                    }
                } else if (team_pos.equals("right")) {
                    if (sec.intValue() == 4) {
                        stext = "F";
                    } else if (sec.intValue() == 1) {
                        stext = "F";
                    }
                }
                res = ftext + stext;
            } catch (Exception e) {
                return "";
            }
        }
        return res;
    }
}
