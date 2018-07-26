package com.develpoment.gobolabali.fundamentalstatistic.Helpers;

import android.content.Context;
import android.database.Cursor;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rian on 12/07/2018.
 */

public class DataExport {

    public Cursor exportCSV(Context context, String idmatch, String category) {
        DatabaseHelper dbConfig = new DatabaseHelper(context);
        if (category.equals("GOAL")) {
            return dbConfig.customQuery("SELECT * FROM goal WHERE idmatch='" + idmatch + "'");
        }else {
            return dbConfig.customQuery(exportQuery(idmatch, category));
        }

    }

    public String exportQuery(String idmatch, String category) {
        String selAct = "";
        String selWhere = "";
        if (category.equals("PLAYER")) {
            selAct = "matchdetail.touch_ball,matchdetail.false_passing,matchdetail.false_control,matchdetail.sot";
        } else if (category.equals("KEEPER")) {
            selAct = "matchdetail.ball_saving_area, matchdetail.good_saving, matchdetail.good_distribution";
        } else {
            selAct = "matchdetail." + category;
        }
        if (category.equals("PLAYER") || category.equals("sgc") || category.equals("KEEPER")) {
            List<String> colList = Arrays.asList(selAct.split(","));
            String comma = "";
            for (int i = 0; i < colList.size(); i++) {
                if (i > 0) {
                    comma = " OR ";
                }
                selWhere = selWhere + comma + ((String) colList.get(i)) + " > 0";
            }
        } else {
            selWhere = selAct + " > 0";
        }
        return "SELECT matchplayer.id AS iddetail,matchplayer.idteam,matchplayer.nopunggung,matchdetail.idmatch, " + selAct + ", matchdetail.posisi, matchplayer.status FROM matchdetail INNER JOIN matchplayer ON matchdetail.iddetail = matchplayer.id WHERE matchdetail.idmatch='" + idmatch + "' AND " + selWhere;
    }
}
