package com.develpoment.gobolabali.fundamentalstatistic.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.develpoment.gobolabali.fundamentalstatistic.Model.DataMatchList;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG ="DatabaseHelper";

    private static final  int DATABASE_VERSION = 22;

    private static final String DATABASE_NAME = "fundamentalStatistic";

    private static final String TABLE_TEAM = "team";
    private static final String TABLE_PLAYER = "player";
    private static final String TABLE_TOURNAMENT = "tournament";
    private static final String TABLE_MATCH = "matchlist";
    private static final String TABLE_PLAYER_MATCH = "matchplayer";
    private static final String TABLE_MATCH_DETAIL = "matchdetail";
    private static final String TABLE_GOAL = "goal";

    private static final String ID_TEAM = "id";
    private static final String NAMA_TEAM = "nama";

    private static final String ID_PLAYER = "id";
    private static final String FULL_NAME_PLAYER = "fullname";
    private static final String NICK_NAME_PLAYER = "nickname";
    private static final String POSISI_PLAYER = "posisi";
    private static final String POSISI_NOMOR = "posnomor";
    private static final String NO_PUNGGUNG_PLAYER = "nopunggung";
    private static final String STATUS = "status";
    private static final String KEY_ID_TEAM_PLAYER = "idteam";

    private static final String ID_TOURNAMENT = "id";
    private static final String NAMA_TOURNAMENT = "namatournament";

    private static final String ID_MATCH = "id";
    private static final String NAMA_MATCH = "namapertandingan";
    private static final String KEY_ID_TOURNAMENT = "idtournament";
    private static final String KEY_ID_TEAM_HOME = "idteam1";
    private static final String KEY_ID_TEAM_AWAY = "idteam2";
    private static final String TANGGAL_MATCH = "tanggal";
    private static final String START_MATCH = "mulai";
    private static final String END_MATCH = "akhir";
    private static final String STATUS_MATCH = "status";

    private static final String ID_MATCH_PLAYER="id";
    private static final String KEY_MATCH_ID="idmatch";
    private static final String KEY_TEAM_MATCH_ID="idteam";
    private static final String KEY_PLAYER_MATCH_ID="idplayer";
    private static final String POSISI_MATCH_PLAYER="posisi";
    private static final String POSISI_NOMOR_MATCH_PLAYER="posnomor";
    private static final String NOPUNGGUNG_MATCH_PLAYER="nopunggung";
    private static final String NICKNAME_MATCH_PLAYER="nickname";
    private static final String STATUS_MATCH_PLAYER="status";
    private SQLiteDatabase database;



    private static final String CREATE_TABLE_TEAM = "CREATE TABLE "
            + TABLE_TEAM + "(" +ID_TEAM + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAMA_TEAM + " TEXT " + ")";

    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE "
            + TABLE_PLAYER + "(" + ID_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FULL_NAME_PLAYER + " TEXT, "
            + NICK_NAME_PLAYER + " TEXT, "
            + POSISI_PLAYER + " TEXT, "
            + POSISI_NOMOR + " TEXT, "
            + NO_PUNGGUNG_PLAYER + " TEXT,"
            + STATUS + " INTEGER DEFAULT 0 ,"
            + KEY_ID_TEAM_PLAYER + " INTEGER " + ")";

    private static final String CREATE_TABLE_TOURNAMENT = "CREATE TABLE "
            + TABLE_TOURNAMENT + "(" +ID_TOURNAMENT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAMA_TOURNAMENT + " TEXT " + ")";

    private static final String CREATE_TABLE_MATCH = "CREATE TABLE "
            + TABLE_MATCH + "(" + ID_MATCH + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAMA_MATCH + " TEXT, "
            + KEY_ID_TOURNAMENT + " TEXT, "
            + KEY_ID_TEAM_HOME + " TEXT, "
            + KEY_ID_TEAM_AWAY + " TEXT, "
            + TANGGAL_MATCH + " TEXT, "
            + START_MATCH + " TEXT,"
            + END_MATCH + " TEXT ,"
            + STATUS_MATCH + " INTEGER DEFAULT 0 " +")";

    private static final String CREATE_TABLE_PLAYER_MATCH = "CREATE TABLE "
            + TABLE_PLAYER_MATCH + "(" + ID_MATCH_PLAYER + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MATCH_ID + " INTEGER, "
            + KEY_TEAM_MATCH_ID + " INTEGER, "
            + KEY_PLAYER_MATCH_ID + " INTEGER, "
            + POSISI_MATCH_PLAYER + " TEXT , "
            + POSISI_NOMOR_MATCH_PLAYER + " TEXT , "
            + NOPUNGGUNG_MATCH_PLAYER+ " TEXT ,"
            + NICKNAME_MATCH_PLAYER+ " TEXT ,"
            + STATUS_MATCH_PLAYER + " INTEGER DEFAULT 0 " +")";

    private static final String CREATE_TABLE_MATCH_DETAIL = "CREATE TABLE "
            + TABLE_MATCH_DETAIL + "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " iddetail INTEGER, idmatch VARCHAR(20), idplayer VARCHAR(20), " +
            " posisi VARCHAR(20), " +
            " touch_ball INTEGER DEFAULT 0, " +
            " false_passing INTEGER DEFAULT 0," +
            " false_control INTEGER DEFAULT 0, " +
            " sot INTEGER DEFAULT 0, " +
            " ball_saving_area INTEGER DEFAULT 0," +
            " good_saving INTEGER DEFAULT 0, " +
            " good_distribution INTEGER DEFAULT 0, " +
            " fouls INTEGER DEFAULT 0, " +
            " red_card INTEGER DEFAULT 0, " +
            " yellow_distribution INTEGER DEFAULT 0 )" ;

    private static final String CREATE_TABLE_GOAL = "CREATE TABLE goal(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idmatch VARCHAR(20),idteam VARCHAR(20), idplayer VARCHAR(20) NOT NULL, " +
            "minute INTEGER DEFAULT 0, post_time DATETIME )";



    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_PLAYER);
        db.execSQL(CREATE_TABLE_TOURNAMENT);
        db.execSQL(CREATE_TABLE_MATCH);
        db.execSQL(CREATE_TABLE_PLAYER_MATCH);
        db.execSQL(CREATE_TABLE_MATCH_DETAIL);
        db.execSQL(CREATE_TABLE_GOAL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TOURNAMENT);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MATCH);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLAYER_MATCH);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MATCH_DETAIL);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_GOAL);

        onCreate(db);
//        dropDb(db);
    }

    private void dropDb(SQLiteDatabase db) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TOURNAMENT);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_MATCH);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_PLAYER_MATCH);
    }

    public void clear(String mode) {

        SQLiteDatabase db = getReadableDatabase();
        /*if (mode.equals("result")) {
            this.database.execSQL("DELETE FROM match_detail");
            return;
        }*/

        if (mode.equals("all")){
            db.execSQL(" DELETE FROM " + TABLE_TEAM);
            db.execSQL(" DELETE FROM " + TABLE_PLAYER);
            db.execSQL(" DELETE FROM " + TABLE_TOURNAMENT);
            db.execSQL(" DELETE FROM " + TABLE_MATCH);
            db.execSQL(" DELETE FROM " + TABLE_PLAYER_MATCH);
            db.execSQL(" DELETE FROM " + TABLE_MATCH_DETAIL);
            db.execSQL(" DELETE FROM " + TABLE_GOAL);
            return;
        }

    }

    public void deleteNilai() {
        SQLiteDatabase database = this.getWritableDatabase();

        database.execSQL(" DELETE FROM " + TABLE_MATCH_DETAIL);
        database.execSQL(" DELETE FROM " + TABLE_GOAL);

        database.close();
    }

    public void deleteAll(){
        SQLiteDatabase database = this.getWritableDatabase();

        database.execSQL(" DELETE FROM " +TABLE_PLAYER_MATCH);
        database.execSQL(" DELETE FROM " +TABLE_MATCH);
        database.execSQL(" DELETE FROM " +TABLE_TOURNAMENT);
        database.execSQL(" DELETE FROM " +TABLE_TEAM);
        database.execSQL(" DELETE FROM " +TABLE_PLAYER);
        database.execSQL(" DELETE FROM " + TABLE_MATCH_DETAIL);
        database.execSQL(" DELETE FROM " + TABLE_GOAL);

        database.close();
    }



    //Query Table Team
    public ArrayList<HashMap<String,String>> getAllTeam(){
        ArrayList<HashMap<String,String>> teamList;
        teamList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " +TABLE_TEAM;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_TEAM, cursor.getString(0));
                map.put(NAMA_TEAM, cursor.getString(1));
                teamList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +teamList);

        database.close();
        return teamList;
    }

    public List<DataTeam> getTeam (){
        List<DataTeam> teamList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TEAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            DataTeam tem = new DataTeam();
            tem.setId(cursor.getString(cursor.getColumnIndex("id")));
            tem.setNama(cursor.getString(cursor.getColumnIndex("nama")));
            teamList.add(tem);
        }while (cursor.moveToNext());
        return teamList;
    }

    public String getTeamName(String id){
        String nb = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select nama from team where id = '"+ id +"' ", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount() < 1){
            return "not found";
        }

        nb = cursor.getString(0);

        return nb;
    }

    public void insertTeam(String nama) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = " INSERT INTO " + TABLE_TEAM + "(nama)" +
                "VALUES ('" + nama + "')";
        Log.e("insert sqlite" ,"" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void updateTeam(int id, String nama) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = " UPDATE " +TABLE_TEAM+ " SET "
                + NAMA_TEAM + "='" + nama + "'"
                + " WHERE " + ID_TEAM + "=" + "'" + id + "'";
        Log.e("update sqlite" , updateQuery);

        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = " DELETE FROM " + TABLE_TEAM + " WHERE " + ID_TEAM +
                "=" + "'" + id + "'";
        Log.e("delete" ,deleteQuery);
        database.execSQL(deleteQuery);
        database.close();

    }


    public ArrayList<DataTeam> getSpinnerTeam(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TEAM;
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<DataTeam> team = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                DataTeam dteam = new DataTeam();
                dteam.setId(cursor.getString(cursor.getColumnIndex("id")));
                dteam.setNama(cursor.getString(cursor.getColumnIndex("nama")));
                team.add(dteam);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return team;
    }


    // -- QUERY CRUD TABEL PLAYER -- \\
    public ArrayList<HashMap<String,String>> getAllPlayer (int idteam){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER + " WHERE idteam = '"+idteam+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_PLAYER, cursor.getString(0));
                map.put(FULL_NAME_PLAYER, cursor.getString(1));
                map.put(NICK_NAME_PLAYER, cursor.getString(2));
                map.put(POSISI_PLAYER, cursor.getString(3));
                map.put(POSISI_NOMOR, cursor.getString(4));
                map.put(NO_PUNGGUNG_PLAYER, cursor.getString(5));
                map.put(STATUS, cursor.getString(6));
                map.put(KEY_ID_TEAM_PLAYER, cursor.getString(7));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }


    public ArrayList<HashMap<String,String>> getPlayerLast (int idteam){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER + " WHERE idteam = '"+idteam+"' ORDER " +
                " BY id DESC LIMIT 1 ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_PLAYER, cursor.getString(0));
                map.put(FULL_NAME_PLAYER, cursor.getString(1));
                map.put(NICK_NAME_PLAYER, cursor.getString(2));
                map.put(POSISI_PLAYER, cursor.getString(3));
                map.put(POSISI_NOMOR, cursor.getString(4));
                map.put(NO_PUNGGUNG_PLAYER, cursor.getString(5));
                map.put(STATUS, cursor.getString(6));
                map.put(KEY_ID_TEAM_PLAYER, cursor.getString(7));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }



    public void insertPlayer(String fullname, String nickname, String posisi, String posnomor ,String nopunggung, String status ,int idTeam){
        SQLiteDatabase database = this.getWritableDatabase();
        String insertValues = " INSERT INTO " + TABLE_PLAYER + "(fullname, nickname, posisi,posnomor, nopunggung, status ,idteam)" +
                " VALUES ('"+fullname+"', '"+nickname+"', '"+posisi+"','"+posnomor+"', '"+nopunggung+"','"+status+"' ,'"+idTeam+"') ";
        Log.e("insert sqlite" ,"" + insertValues);
        database.execSQL(insertValues);
        database.close();
    }

    public void updatePlayer(int id, String fullname, String nickname, String posisi, String nopunggung, String status ,int idteam){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = " UPDATE " + TABLE_PLAYER + " SET "
                + FULL_NAME_PLAYER + "='" + fullname + "',"
                + NICK_NAME_PLAYER + "='" + nickname + "',"
                + POSISI_PLAYER + "='" + posisi + "',"
                + NO_PUNGGUNG_PLAYER + "='" + nopunggung + "',"
                + STATUS + "='" + status + "',"
                + KEY_ID_TEAM_PLAYER + "='" + idteam + "'"
                + " WHERE " + ID_PLAYER + "=" + "'" + id + "'";

        Log.e("update sqlite" , updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deletePlayer(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = " DELETE FROM " + TABLE_PLAYER + " WHERE " + ID_PLAYER +
                "=" + "'" + id + "'";
        Log.e("delete" ,deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }

    public String getPlayerName(String id){
        String nb = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select nickname from player where id = '"+ id +"' ", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount() < 1){
            return "not found";
        }

        nb = cursor.getString(0);

        return nb;
    }

    public ArrayList<HashMap<String,String>> getAllPlayerPosition (int idteam, String posisi ){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER + " WHERE idteam = '"+idteam+"' AND posisi ='"+posisi+"'  " ;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_PLAYER, cursor.getString(0));
                map.put(FULL_NAME_PLAYER, cursor.getString(1));
                map.put(NICK_NAME_PLAYER, cursor.getString(2));
                map.put(POSISI_PLAYER, cursor.getString(3));
                map.put(NO_PUNGGUNG_PLAYER, cursor.getString(4));
                map.put(KEY_ID_TEAM_PLAYER, cursor.getString(5));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }

    public ArrayList<HashMap<String,String>> getAllPlayerPosition2 (int idteam, String posisi ){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER + " WHERE idteam = '"+idteam+"' AND posisi ='"+posisi+"'  " ;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_PLAYER, cursor.getString(0));
                map.put(FULL_NAME_PLAYER, cursor.getString(1));
                map.put(NICK_NAME_PLAYER, cursor.getString(2));
                map.put(POSISI_PLAYER, cursor.getString(3));
                map.put(NO_PUNGGUNG_PLAYER, cursor.getString(4));
                map.put(KEY_ID_TEAM_PLAYER, cursor.getString(5));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }

    // -- AKHIR CRUD TABEL PLAYER -- \\


    // -- QUERY CRUD TABEL TOURNAMENT

    public ArrayList<HashMap<String,String>> getAllTournament(){
        ArrayList<HashMap<String,String>> teamList;
        teamList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " +TABLE_TOURNAMENT;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_TOURNAMENT, cursor.getString(0));
                map.put(NAMA_TOURNAMENT, cursor.getString(1));
                teamList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e("select sqlite " ,"" +teamList);

        database.close();
        return teamList;
    }

    public List<DataTournament> getTournament (){
        List<DataTournament> teamList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TOURNAMENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            DataTournament tem = new DataTournament();
            tem.setId(cursor.getString(cursor.getColumnIndex("id")));
            tem.setNamaTournament(cursor.getString(cursor.getColumnIndex("namatournament")));
            teamList.add(tem);
        }while (cursor.moveToNext());
        return teamList;
    }

    public String getTournamentName(String id){
        String nb = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select namatournament from tournament where id = '"+ id +"' ", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount() < 1){
            return "not found";
        }

        nb = cursor.getString(0);

        return nb;
    }

    public void insertTournament(String nama) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = " INSERT INTO " + TABLE_TOURNAMENT + "(namatournament)" +
                "VALUES ('" + nama + "')";
        Log.e("insert sqlite" ,"" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void updateTournament(int id, String nama) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = " UPDATE " +TABLE_TOURNAMENT+ " SET "
                + NAMA_TOURNAMENT + "='" + nama + "'"
                + " WHERE " + ID_TOURNAMENT + "=" + "'" + id + "'";
        Log.e("update sqlite" , updateQuery);

        database.execSQL(updateQuery);
        database.close();
    }

    public void deleteTournament(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = " DELETE FROM " + TABLE_TOURNAMENT + " WHERE " + ID_TOURNAMENT +
                "=" + "'" + id + "'";
        Log.e("delete" ,deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }


    public ArrayList<DataTournament> getSpinnerTournament(){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TOURNAMENT;
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<DataTournament> team = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                DataTournament dteam = new DataTournament();
                dteam.setId(cursor.getString(cursor.getColumnIndex("id")));
                dteam.setNamaTournament(cursor.getString(cursor.getColumnIndex("namatournament")));
                team.add(dteam);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return team;
    }
    // -- AKHIR CRUD TABEL TOURNAMENT -- \\


    // QUERY CRUD TABLE MATCH
    public ArrayList<HashMap<String,String>> getAllMatch (int idtournament){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_MATCH + " WHERE idtournament = '"+idtournament+"'";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_MATCH, cursor.getString(0));
                map.put(NAMA_MATCH, cursor.getString(1));
                map.put(KEY_ID_TOURNAMENT, cursor.getString(2));
                map.put(KEY_ID_TEAM_HOME, cursor.getString(3));
                map.put(KEY_ID_TEAM_AWAY, cursor.getString(4));
                map.put(TANGGAL_MATCH, cursor.getString(5));
                map.put(START_MATCH, cursor.getString(6));
                map.put(END_MATCH, cursor.getString(7));
                map.put(START_MATCH, cursor.getString(8));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e(" select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }

    public void insertMatch(String namapertandingan,String idtournament, String idteam1, String idteam2, String tanggal, String mulai, String akhir){
        SQLiteDatabase database = this.getWritableDatabase();
        String insertValues = " INSERT INTO " + TABLE_MATCH + "(namapertandingan,idtournament, idteam1, idteam2, tanggal, mulai, akhir)" +
                " VALUES ('"+namapertandingan+"','"+idtournament+"', '"+idteam1+"', '"+idteam2+"', '"+tanggal+"', '"+mulai+"', '"+akhir+"') ";
        Log.e("insert sqlite" ,"" + insertValues);
        database.execSQL(insertValues);
        database.close();
    }

    public void updateMatch(int id, String namapertandingan, String idtournament, String idteam1, String idteam2, String tanggal, String mulai, String akhir){
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = " UPDATE " + TABLE_MATCH + " SET "
                + NAMA_MATCH + "='" + namapertandingan + "',"
                + KEY_ID_TOURNAMENT + "='" + idtournament + "',"
                + KEY_ID_TEAM_HOME + "='" + idteam1 + "',"
                + KEY_ID_TEAM_AWAY + "='" + idteam2 + "',"
                + TANGGAL_MATCH + "='" + tanggal + "',"
                + START_MATCH + "='" + mulai + "',"
                + END_MATCH + "='" + akhir + "'"
                + " WHERE " + ID_MATCH + "=" + "'" + id + "'";

        Log.e("update sqlite" , updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void deleteMatch(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = " DELETE FROM " + TABLE_MATCH + " WHERE " + ID_MATCH +
                "=" + "'" + id + "'";
        Log.e("delete" ,deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }

    public ArrayList<DataMatchList> getSpinnerMatch(int idtournament){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_MATCH + " WHERE idtournament = '"+idtournament+"'" ;
        Cursor cursor = database.rawQuery(query,null);
        ArrayList<DataMatchList> team = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                DataMatchList dteam = new DataMatchList();
                dteam.setId(cursor.getString(cursor.getColumnIndex("id")));
                dteam.setNamaPertandingan(cursor.getString(cursor.getColumnIndex("namapertandingan")));
                team.add(dteam);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return team;
    }

    String team2;
    public String getTeam1(String id){
        String nb = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select idteam1,idteam2 from matchlist where id = '"+ id +"' ", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount() < 1){
            return "not found";
        }
        nb = cursor.getString(0);
//        INUMM3 = cursor.getString(1);
        team2 = cursor.getString(1);
//        NamaBarangM3 = cursor.getString(2);
        return nb;
    }

    public String getTeam2(String id) {
        return team2;
    }

    public boolean updateMatchStatus(String id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where = "id='" + id + "'";
        cv.put("status", Integer.valueOf(1));
        try {
            database.update("match", cv, where, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //END QUERY CRUD TABLE MATCH


    //Match Player

    public String getPosition(String idmatch, String idplayer){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery(" SELECT posnomor From matchplayer " +
                "where idmatch ='"+idmatch+"' AND idplayer ='"+idplayer+"' ", null);
        c.moveToFirst();

        return c.getString(0);
    }

    public Cursor getFormation(String idmatch, String idteam){
        SQLiteDatabase database = this.getReadableDatabase();
        return  database.rawQuery(" SELECT idplayer,nickname,posnomor,status,nopunggung " +
                "FROM matchplayer " +
                "WHERE  idmatch='" + idmatch + "' " +
                "AND idteam='" + idteam + "' " +
                "AND status= 1 " , null);
    }

    public Cursor getPlayerData(String team) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT player.* FROM player " +
                "INNER  JOIN matchplayer ON player.id = matchplayer.idplayer " +
                "WHERE matchplayer.idteam='" + team + "' " +
                "GROUP BY matchplayer.idplayer " +
                "ORDER BY player.nopunggung ASC ";
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerCadangan(String team, String match) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT player.id, player.nickname, " +
                " matchplayer.status, matchplayer.nopunggung " +
                " FROM player INNER  JOIN matchplayer " +
                " ON player.id = matchplayer.idplayer " +
                " WHERE matchplayer.idteam='" + team + "' " +
                " AND matchplayer.idmatch='" + match + "' " +
                " AND matchplayer.status= 0 GROUP BY matchplayer.idplayer " +
                " ORDER BY matchplayer.nopunggung ASC ";
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerDetail(String team, String match, String playernumber) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT * FROM player WHERE id='" + getPlayerId(team, match, playernumber) + "'";
        Log.e("LOG", query);
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        return c;
    }

    public Cursor getPlayerCadangan2(String team, String match) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT idplayer,status, nopunggung , nickname FROM matchplayer " +
                " WHERE idteam='" + team + "' " +
                " AND idmatch='" + match + "' " +
                " AND status=0 GROUP BY idplayer " +
                " ORDER BY nopunggung ASC ";
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerDataWhere(String team, String cur_player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "";
        if (cur_player == null || cur_player.equals("")) {
            selectQuery = " SELECT player.id, player.fullname, player.nickname, " +
                    "matchplayer.nopunggung, matchplayer.status, " +
                    "matchplayer.posnomor FROM player INNER JOIN " +
                    "matchplayer ON player.id = matchplayer.idplayer " +
                    "WHERE  matchplayer.idteam='" + team + "' AND matchplayer.status=1 " +
                    " GROUP BY matchplayer.idplayer";
        } else {
            selectQuery = "SELECT player.id, player.fullname, " +
                    "matchplayer.nopunggung, player.nickname, " +
                    "matchplayer.status, matchplayer.posnomor  " +
                    "FROM player INNER JOIN matchplayer " +
                    "ON player.id = matchplayer.idplayer " +
                    "WHERE matchplayer.status=1 AND matchplayer.idteam='" + team + "'  " +
                    (" AND matchplayer.nopunggung " +
                            "NOT IN (" + cur_player + ")") + " " +
                    "GROUP BY matchplayer.idplayer";
        }
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerDataWhere2(String team, String cur_player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "";
        if (cur_player == null || cur_player.equals("")) {
            selectQuery = " SELECT player.id, player.fullname, player.nickname, " +
                    "matchplayer.nopunggung, matchplayer.status, " +
                    "matchplayer.posnomor FROM player INNER JOIN " +
                    "matchplayer ON player.id = matchplayer.idplayer " +
                    "WHERE  matchplayer.idteam='" + team + "' AND matchplayer.status=0 " +
                    "GROUP BY matchplayer.idplayer";
        } else {
            selectQuery = "SELECT player.id, player.fullname, " +
                    "matchplayer.nopunggung, player.nickname, " +
                    "matchplayer.status, matchplayer.posnomor  " +
                    "FROM player INNER JOIN matchplayer " +
                    "ON player.id = matchplayer.idplayer " +
                    "WHERE  matchplayer.idteam='" + team + "' AND matchplayer.status=0 " +
                    (" AND matchplayer.nopunggung " +
                            "NOT IN (" + cur_player + ")") + " " +
                    "GROUP BY matchplayer.idplayer";
        }
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerDataWhere3(String team, String cur_player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "";
        if (cur_player == null || cur_player.equals("")) {
            selectQuery = " SELECT player.id, player.fullname, player.nickname, " +
                    "matchplayer.nopunggung, matchplayer.status, " +
                    "matchplayer.posnomor FROM player INNER JOIN " +
                    "matchplayer ON player.id = matchplayer.idplayer " +
                    "WHERE  matchplayer.idteam='" + team + "' AND matchplayer.status=2 " +
                    "GROUP BY matchplayer.idplayer";
        } else {
            selectQuery = "SELECT player.id, player.fullname, " +
                    "matchplayer.nopunggung, player.nickname, " +
                    "matchplayer.status, matchplayer.posnomor  " +
                    "FROM player INNER JOIN matchplayer " +
                    "ON player.id = matchplayer.idplayer " +
                    "WHERE  matchplayer.idteam='" + team + "' AND matchplayer.status=2 " +
                    (" AND matchplayer.nopunggung " +
                            "NOT IN (" + cur_player + ")") + " " +
                    "GROUP BY matchplayer.idplayer";
        }
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getPlayerByMatchTeam(String team, String match_id) {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "";
        selectQuery = "SELECT matchplayer.id,fullname,nickname," +
                "matchplayer.nopunggung as nopunggung, " +
                "matchplayer.status, matchplayer.posisi " +
                "FROM player INNER JOIN matchplayer" +
                " ON player.id = matchplayer.idplayer " +
                "WHERE  matchplayer.idteam='" + team + "' " +
                "AND matchplayer.idmatch='" + match_id + "' " +
                "GROUP BY matchplayer.idplayer " +
                "ORDER BY matchplayer.status DESC";
//        Log.e(SearchIntents.EXTRA_QUERY, selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public void updateFormation(String match, String team, String playerOld, String playerNew, String position) {
        SQLiteDatabase database = this.getReadableDatabase();
        String where = "idteam='" + team + "' AND idmatch='"
                + match + "' AND idplayer='"
                + getPlayerId(team, match, playerOld) + "'";
        ContentValues cva = new ContentValues();
        cva.put("status", "0");
        cva.put("posnomor", " ");
        database.update("matchplayer", cva, where, null);
        Log.e("QUERY", where);
        String where2 = " idteam='" + team + "' AND idmatch='"
                + match + "' AND idplayer='" + playerNew + "'";
        ContentValues cva2 = new ContentValues();
        cva2.put("status", "1");
        cva2.put("posnomor", position);
        database.update("matchplayer", cva2, where2, null);
        Log.e("QUERY", where2);
    }

    public void updateFormation2(String match, String team, String playerOld, String playerNew, String position) {
        SQLiteDatabase database = this.getReadableDatabase();
        String where = "idteam='" + team + "' AND idmatch='"
                + match + "' AND idplayer='"
                + getPlayerId(team, match, playerOld) + "'";
        ContentValues cva = new ContentValues();
        cva.put("status", "0");
        cva.put("posnomor", " ");
        database.update("matchplayer", cva, where, null);
        Log.e("QUERY", where);
        String where2 = " idteam='" + team + "' AND idmatch='"
                + match + "' AND idplayer='" + playerNew + "'";
        ContentValues cva2 = new ContentValues();
        cva2.put("status", "1");
        cva2.put("posnomor", position);
        database.update("matchplayer", cva2, where2, null);
        Log.e("QUERY", where2);
    }

    public void removeFormation(String match, String team, String player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = " UPDATE matchplayer " +
                "SET status=1 WHERE idteam='" + team + "' " +
                "AND idmatch='" + match + "' " +
                "AND idplayer='" + getPlayerId(team, match, player) + "'";
        database.execSQL(query);
        Log.e("QUERY", query);
    }

    public void removePlayerFormation(String match, String team, String player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = " DELETE matchplayer WHERE idteam='" + team + "' " +
                "AND idmatch='" + match + "' " +
                "AND idplayer='" + getPlayerId(team, match, player) + "'";
        database.execSQL(query);
        Log.e("QUERY", query);
    }

    public void insertFormation(String match, String team, String player, String position) {
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL(" UPDATE matchplayer SET status=1,posnomor='"
                + position + "' WHERE idteam='" + team + "' " +
                "AND idmatch='" + match + "' AND idplayer='" + player + "'");
    }

    public String getPlayerId(String team, String match, String playernumber) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT player.id FROM " +
                "matchplayer INNER JOIN player " +
                "ON matchplayer.idplayer = player.id " +
                "WHERE  matchplayer.idmatch='" + match + "' " +
                "AND matchplayer.idteam='" + team + "' " +
                "AND matchplayer.nopunggung='" + playernumber + "'";
        Log.e("LOG", query);
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        return c.getString(0);
    }

    public boolean updatePlayerMatch(String id, String number) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where = "id='" + id + "'";
        cv.put("nopunggung", number);
        try {
            database.update(" matchplayer", cv, where, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private String getDetailId(String match, String player) {
        SQLiteDatabase database = getReadableDatabase();
        String query = " SELECT id FROM matchplayer " +
                "WHERE idmatch='" + match + "' " +
                "AND idplayer='" + player + "'";
        Log.e("LOG", query);
        Cursor c = database.rawQuery(query, null);
        c.moveToFirst();
        return c.getString(0);
    }


    public void insertPlayerMatch(Integer idmatch, Integer idTeam, Integer idplayer, String posisi,String nopunggung, String nickname ){
        SQLiteDatabase database = this.getWritableDatabase();
        String insertValues = " INSERT INTO " + TABLE_PLAYER_MATCH + "(idmatch, idteam, idplayer, posisi ,nopunggung, nickname)" +
                " VALUES ('"+idmatch+"', '"+idTeam+"', '"+idplayer+"', '"+posisi+"','"+nopunggung+"','"+nickname+"') ";
        Log.e("insert sqlite" ,"" + insertValues);
        database.execSQL(insertValues);
        database.close();
    }

    public void insertPlayerMatch2(Integer idTeam, Integer idplayer, String posisi,String posnomor,String nopunggung, String nickname){
        SQLiteDatabase database = this.getWritableDatabase();
        String insertValues = " INSERT INTO " + TABLE_PLAYER_MATCH + "(idteam, idplayer, posisi ,posnomor,nopunggung, nickname)" +
                " VALUES ('"+idTeam+"', '"+idplayer+"', '"+posisi+"','"+posnomor+"','"+nopunggung+"','"+nickname+"')";
        Log.e("insert sqlite" ,"" + insertValues);
        database.execSQL(insertValues);
        database.close();
    }

    public void insertPlayerMatch3(Integer idmatch, Integer idTeam, Integer idplayer, String posisi,String nopunggung, String nickname){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("idmatch",idmatch);
        insertValues.put("idteam",idTeam);
        insertValues.put("idplayer",idplayer);
        insertValues.put("posisi",posisi);
        insertValues.put("nopunggung",nopunggung);
        insertValues.put("nickname",nickname);
        database.insertWithOnConflict(TABLE_PLAYER_MATCH, null, insertValues, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public boolean updatePlayerMatch(int id, int idteam, int idplayer, int idmatch) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where = " id='" + id + "' AND idteam='"
                + idteam + "' AND idplayer='" + idplayer + "'";
        cv.put("idmatch", idmatch);
        try {
            database.update(" matchplayer", cv, where, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void updatestatusplayermatch(Integer idMatch, Integer idTeam, Integer idPlayer){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(" UPDATE matchplayer SET status=1  WHERE idmatch='" + idMatch + "' " +
                "AND idteam='" + idTeam + "' AND idplayer='" + idPlayer + "'");
    }

    public void updatestatusplayermatchNon(Integer idMatch, Integer idTeam, Integer idPlayer){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(" UPDATE matchplayer SET status=0  WHERE idmatch='" + idMatch + "' " +
                "AND idteam='" + idTeam + "' AND idplayer='" + idPlayer + "'");
    }

    public ArrayList<HashMap<String,String>> getAllPlayerMatchforUpdate (int idteam){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER_MATCH + " " +
                " WHERE  idteam ='"+idteam+"' ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_MATCH_PLAYER, cursor.getString(0));
                map.put(KEY_PLAYER_MATCH_ID, cursor.getString(1));
                map.put(KEY_TEAM_MATCH_ID, cursor.getString(2));
                map.put(KEY_PLAYER_MATCH_ID, cursor.getString(3));
                map.put(POSISI_MATCH_PLAYER, cursor.getString(4));
                map.put(POSISI_NOMOR_MATCH_PLAYER, cursor.getString(5));
                map.put(NOPUNGGUNG_MATCH_PLAYER, cursor.getString(6));
                map.put(NICKNAME_MATCH_PLAYER, cursor.getString(7));
                map.put(STATUS_MATCH_PLAYER, cursor.getString(8));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e(" select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }


    public ArrayList<HashMap<String,String>> getAllPlayerMatch (int idmatch, int idteam){

        ArrayList<HashMap<String,String>>  playerList;
        playerList = new ArrayList<>();
        String selectQuery = " SELECT * FROM " + TABLE_PLAYER_MATCH + " " +
                " WHERE idmatch = '"+idmatch+"' AND idteam ='"+idteam+"' ";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(ID_MATCH_PLAYER, cursor.getString(0));
                map.put(KEY_PLAYER_MATCH_ID, cursor.getString(1));
                map.put(KEY_TEAM_MATCH_ID, cursor.getString(2));
                map.put(KEY_PLAYER_MATCH_ID, cursor.getString(3));
                map.put(POSISI_MATCH_PLAYER, cursor.getString(4));
                map.put(POSISI_NOMOR_MATCH_PLAYER, cursor.getString(5));
                map.put(NOPUNGGUNG_MATCH_PLAYER, cursor.getString(6));
                map.put(NICKNAME_MATCH_PLAYER, cursor.getString(7));
                map.put(STATUS_MATCH_PLAYER, cursor.getString(8));
                playerList.add(map);
            }while (cursor.moveToNext());

        }
        Log.e(" select sqlite " ,"" +playerList);
        database.close();
        return playerList;
    }


    //END MATCH PLAYER


    // SET POSISI

    public String convertPosition(String name, String team_pos) {
        String res = "";
        String num = "";
        String order = "";
        Integer bc = Integer.valueOf(0);
        Integer mc = Integer.valueOf(0);
        Integer fc = Integer.valueOf(0);
        if (name.equals("GK")) {
            num = "1";
            order = "1";
        } else if (name.equals("BL") || name.equals("ML") || name.equals("FL")) {
            if (team_pos.equals("left")) {
                num = "1";
            } else {
                num = "4";
            }
        } else if (name.equals("BR") || name.equals("MR") || name.equals("FR")) {
            if (team_pos.equals("left")) {
                num = "4";
            } else {
                num = "1";
            }
        } else if (name.equals("BC")) {
            order = "2";
            if (bc.equals(Integer.valueOf(0))) {
                num = "2";
            } else {
                num = "3";
            }
            bc = Integer.valueOf(bc.intValue() + 1);
        } else if (name.equals("MC")) {
            order = "3";
            if (mc.equals(Integer.valueOf(0))) {
                num = "2";
            } else {
                num = "3";
            }
            mc = Integer.valueOf(mc.intValue() + 1);
        } else if (name.equals("FC")) {
            order = "4";
            if (fc.equals(Integer.valueOf(0))) {
                num = "2";
            } else {
                num = "3";
            }
            fc = Integer.valueOf(fc.intValue() + 1);
        }
        if (name.equals("BL") || name.equals("BR")) {
            order = "2";
        } else if (name.equals("ML") || name.equals("MR")) {
            order = "3";
        } else if (name.equals("FL") || name.equals("FR")) {
            order = "4";
        }
        return order + "" + num;
    }

    public Integer checkSide(String idmatch, String idteam) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT idteam1, idteam2 FROM matchlist WHERE id='" + idmatch + "'", null);
        c.moveToFirst();
        if (c.getString(0).equals(idteam)) {
            return Integer.valueOf(1);
        }
        return Integer.valueOf(2);
    }

    public boolean checkMatchStatus(String idmatch) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT status FROM matchlist WHERE id='" + idmatch + "'", null);
        c.moveToFirst();
        if (c.getString(0).equals("0")) {
            return false;
        }
        return true;
    }

    public String getStatusMatch(String id){
        String nb = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select status from matchlist where id = '"+ id +"' ", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if (cursor.getCount() < 1){
            return "not found";
        }

        nb = cursor.getString(0);

        return nb;
    }


    public void setMatchDetailData(String type, String idmatch, String player) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = getMD("*", idmatch, player);
        Integer intMd = Integer.valueOf(c.getCount());
        String positon = getPosition(idmatch, player);
        Integer lastVal;
        if (intMd.intValue() > 0 ){
            c.moveToFirst();
            String where = "id='" +c.getString(0) + "'";
            lastVal = Integer.valueOf(getMDVal(type, idmatch, player) + 1);
            cv.put(type, lastVal);
            cv.put("posisi", positon);
            database.update("matchdetail", cv, where, null);
            Log.e("LOG", "Update Match Detail=>" + type + ":" + lastVal);
            return;
        }
        String detail = getDetailId(idmatch, player);
        lastVal = Integer.valueOf(1);
        cv.put("idmatch", idmatch);
        cv.put("idplayer", player);
        cv.put("iddetail", detail);
        cv.put("posisi", positon);
        database.insert("matchdetail", null, cv);
        Log.e("LOG", "INSERT MATCH DETAIL");
    }

    public int getMDVal(String type, String idmatch, String player) {
        SQLiteDatabase database = this.getReadableDatabase();
        String param = "*";
        Integer lastVal = Integer.valueOf(0);
        param = type;
        Cursor c = database.rawQuery("SELECT " + type + " FROM matchdetail WHERE idmatch='" + idmatch + "' AND idplayer='" + player + "'", null);
        Log.e("valcount", String.valueOf(c.getCount()));
        try {
            if (c.getCount() > 0){
                c.moveToFirst();
                lastVal = Integer.valueOf(Integer.parseInt(c.getString(0)));
            }
        }catch (Exception e){
            Log.e("type", type);
        }

        return lastVal.intValue();
    }

    public Cursor getMD(String param, String match, String player) {
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery("SELECT " + param + " " +
                "FROM matchdetail WHERE idmatch='" + match + "' " +
                "AND idplayer='" + player + "'", null);
    }

    public Cursor getStats(String category, String idmatch, String team) {
        SQLiteDatabase database = this.getReadableDatabase();
        String jumlah = category + " AS jumlah";
        String typeRange = " AND " + category + " >0 ";

        if (category.equals("PLAYER")){
            jumlah = "touch_ball, false_passing, false_control, sot";
            typeRange = "";
        } else if (category.equals("KEEPER")){
            jumlah = "ball_saving_area, good_saving, good_distribution";
            typeRange = "";
        }else if (category.equals("FOULS")){
            jumlah = "fouls, red_card, yellow_distribution";
            typeRange = "";
        }
        String selectQuery = "SELECT player.id, player.fullname,matchplayer.posnomor, " +
                "player.nickname,matchplayer.nopunggung," + jumlah + " FROM player INNER JOIN matchplayer ON player.id = matchplayer.idplayer " +
                "INNER JOIN matchdetail ON matchplayer.id = matchdetail.iddetail  WHERE matchplayer.idteam='" + team + "' AND " +
                "matchplayer.idmatch='" + idmatch + "' " + typeRange + "ORDER BY matchplayer.nopunggung ASC ";
        Log.e("query", selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public Cursor getGoal(String idmatch, String team) {
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT goal.minute, player.fullname, matchplayer.nopunggung FROM goal " +
                "INNER JOIN player ON player.id = goal.player_id INNER JOIN matchplayer ON matchplayer.idplayer = player.id " +
                "WHERE  matchplayer.idmatch='" + idmatch + "'  AND matchplayer.idteam='" + team + "'";
        Log.e("query", selectQuery);
        return database.rawQuery(selectQuery, null);
    }

    public void setMatchDetailReaction(String selectedText, String idmatch, String player) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor c = getMD("*", idmatch, player);
        if (Integer.valueOf(c.getCount()).intValue() > 0) {
            c.moveToFirst();
            String where = "id='" + c.getString(0) + "'";
            cv.put("reaction", selectedText);
            database.update("matchdetail", cv, where, null);
            Log.e("LOG", "UPDATE MATCH DETAIL=> reaction:" + selectedText);
            return;
        }
        cv.put("idmatch", idmatch);
        cv.put("idplayer", player);
        cv.put("reaction", selectedText);
        database.insert("matchdetail", null, cv);
        Log.e("LOG", "INSERT MATCH DETAIL");
    }

    public Cursor customQuery(String query) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query, null);
    }

    public boolean saveData(String TABLE_NAME, ContentValues cv) {
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_NAME, null, cv);
        return true;
    }

    public boolean updatePlayer(String id, String name, String nickname, String number) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String where = "id='" + id + "'";
        cv.put("fullname", name);
        cv.put("nickname", nickname);
        cv.put("nopunggung", number);
        try {
            database.update("player", cv, where, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateData(String TABLE_NAME, ContentValues cv, String where) {
        SQLiteDatabase database = getWritableDatabase();
        database.update(TABLE_NAME, cv, where, null);
        return true;
    }

    public boolean deleteData(String matchplayer, String s) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(matchplayer, s, null) > 0;
    }


}
