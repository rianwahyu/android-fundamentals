package com.develpoment.gobolabali.fundamentalstatistic.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Utils;
import com.develpoment.gobolabali.fundamentalstatistic.R;

/**
 * Created by Rian on 11/07/2018.
 */

public class FragmentResultTeam extends Fragment {
    int NUM_ROW;
    DatabaseHelper db = new DatabaseHelper(getActivity());
    String idmatch;
    String team;
    String teampos;
    String th;
    String tr = "";
    String category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        category = args.getString("category");
        idmatch = args.getString("idmatch");
        team = args.getString("team");
        teampos = args.getString("teampos");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_math_detail3, container, false);
        db = new DatabaseHelper(getActivity());
        Cursor dt = db.getStats(category, idmatch, team);
        this.NUM_ROW = dt.getCount();
        WebView webView = (WebView) rootView.findViewById(R.id.webView);
        while (dt.moveToNext()) {
            double res;
            if (category.equals("PLAYER")) {
                String touch = dt.getString(dt.getColumnIndex("touch_ball"));
                String control = dt.getString(dt.getColumnIndex("false_passing"));
                String passing = dt.getString(dt.getColumnIndex("false_control"));
                String sot = dt.getString(dt.getColumnIndex("sot"));
                String position = dt.getString(dt.getColumnIndex("posnomor"));
                Double achieveX = Double.valueOf(Double.parseDouble(touch) - (Double.parseDouble(control) + Double.parseDouble(passing)));
                res = 0.0d;
                if (Integer.valueOf(touch).intValue() != 0) {
                    res = achieveX.doubleValue() / Double.parseDouble(touch);
                }
                res *= 100.0d;
                if (!touch.equals("0") || !sot.equals("0") || !passing.equals("0") || !control.equals("0")) {
                    tr += "<tr><td>" + dt.getString(dt.getColumnIndex("nopunggung")) + "</td><td>" + dt.getString(dt.getColumnIndex("nickname")) + "</td>";
                    tr += "<td style='text-align:right;'>" + Utils.convertPositionNumber(position, teampos) + "</td><td style='text-align:right;'>" + touch + "</td><td style='text-align:right;'>" + control + "</td><td style='text-align:right;'>" + passing + "</td><td style='text-align:right;'>" + sot + "</td><td style='text-align:center;'>" + String.format("%.1f", new Object[]{Double.valueOf(res)}) + "%</td></tr>";
                }
            } else if (category.equals("KEEPER")) {
                String saving = dt.getString(dt.getColumnIndex("ball_saving_area"));
                String distribution = dt.getString(dt.getColumnIndex("good_saving"));
                String anticipation = dt.getString(dt.getColumnIndex("good_distribution"));
                res = 0.0d;
                if (Integer.valueOf(saving).intValue() != 0) {
                    res = (Double.parseDouble(saving) - Double.parseDouble(anticipation)) / Double.parseDouble(saving);
                }
                if (!saving.equals("0") || !distribution.equals("0") || !anticipation.equals("0")) {
                    this.tr += "<tr><td>" + dt.getString(dt.getColumnIndex("nopunggung")) + "</td><td>" + dt.getString(dt.getColumnIndex("nickname")) + "</td>";
                    this.tr += "<td style='text-align:right;'>" + saving + "</td><td style='text-align:right;'>" + distribution + "</td><td style='text-align:right;'>" + anticipation + "</td><td style='text-align:center;'>" + res + "%</td></tr>";
                }
            }else if (category.equals("FOULS")){
                String fouls = dt.getString(dt.getColumnIndex("fouls"));
                String yellow = dt.getString(dt.getColumnIndex("red_card"));
                String red = dt.getString(dt.getColumnIndex("yellow_distribution"));
                res = 0.0d;
                if (Integer.valueOf(fouls).intValue() !=0){
                    res = (Double.parseDouble(fouls) - Double.parseDouble(yellow)) / Double.parseDouble(red);
                }
                if (!fouls.equals("0") || !red.equals("0") || yellow.equals("0")){
                    this.tr += "<tr><td>" + dt.getString(dt.getColumnIndex("nopunggung")) + "</td><td>" + dt.getString(dt.getColumnIndex("nickname")) + "</td>";
                    this.tr += "<td style='text-align:right;'>" + fouls + "</td><td style='text-align:right;'>" + yellow + "</td><td style='text-align:right;'>" + red + "</td><td style='text-align:center;'>" + res + "%</td></tr>";
                }
            }
            else {
                this.tr += "<tr><td>" + dt.getString(dt.getColumnIndex("nopunggung")) + "</td><td>" + dt.getString(dt.getColumnIndex("nickname")) + "</td>";
                this.tr += "<td style='text-align:right;'>" + dt.getString(dt.getColumnIndex("jumlah")) + "</td></tr>";
            }
        }
        if (category.equals("PLAYER")) {
            th = "<tr><th >NO</th><th >NICKNAME</th><th >POSITION</th><th >TOUCH</th><th >False PASSING</th><th >False CONTROL</th><th >SHOOT</th><th >ACHIEVEMENT</th></tr>";
        } else if (category.equals("KEEPER")) {
            th = "<tr><th >NO</th><th >NICKNAME</th><th >BALL SAVING AREA</th><th >GOOD SAVING</th><th >GOOD DISTRIBUTION</th><th>ACHIEVEMENT</th></tr>";
        } else if (category.equals("FOULS")){
            th = "<tr><th >NO</th><th >NICKNAME</th><th >FOULS</th><th >YELLOW CARD</th><th >RED CARD</th><th>ACHIEVEMENT</th></tr>";
        }
        else {
            th = "<tr><th>NO</th><th>NICKNAME</th><th>NISN</th><th>JUMLAH</th></tr>";
        }
        String tableGoal = "";
        String th_goal = "";
        String tr_goal = "";
        if (category.equals("sgc")) {
            Cursor c = db.getGoal(idmatch, team);
            if (c.getCount() > 0) {
                th_goal = "<tr><th>NO</th><th>NAMA LENGKAP</th><th>MENIT</th></tr>";
                while (c.moveToNext()) {
                    tr_goal = tr_goal + "<tr><td>" + c.getString(c.getColumnIndex("nopunggung")) + "</td><td>" + c.getString(c.getColumnIndex("nickname")) + "</td><td>" + c.getString(c.getColumnIndex("minute")) + "</td></tr>";
                }
                tableGoal = "<h4 style='margin-bottom:5px'>Informasi Goal</h4><table class='tg'>" + th_goal + tr_goal + "</table>";
            }
        }
        webView.loadData("<style type='text/css'>table{width:100%;} th{background:#ddd;}.tg{border-collapse:collapse;border-spacing:0;}.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}.tg .tg-yw4l{vertical-align:top}</style><table class='tg'> " + this.th + this.tr + "</table>" + tableGoal, "text/html; charset=utf-8", "utf-8");
        return rootView;
    }

}
