package com.example.out;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cric.db";

    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_TEAM = "Team";

    public static final String TABLE_TEAM_ONE_PLAYER = "Player1";
    public static final String TABLE_TEAM_TWO_PLAYER = "Player2";


    public static final String COLUMN_PLAYER_ID= "id";
    public static final String COLUMN_PLAYER_NAME= "Name";
    public static final String COLUMN_PLAYER_RUN= "Run";
    public static final String COLUMN_PLAYER_BALL= "Ball";
    public static final String COLUMN_PLAYER_FOUR= "Four";
    public static final String COLUMN_PLAYER_SIX= "Six";
    public static final String COLUMN_PLAYER_BOWLER_BALL= "BowlerBall";
    public static final String COLUMN_PLAYER_BOWLER_RUN= "BowlerRun";
    public static final String COLUMN_PLAYER_BOWLER_WICKET = "Wickets";

    public static final String COLUMN_TEAM_ID = "id";
    public static final String COLUMN_TEAM_NAME = "name";
    public static final String COLUMN_TEAM_RUN = "run";
    public static final String COLUMN_TEAM_BALL = "ball";
    public static final String COLUMN_TEAM_WICKET = "wicket";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAM_TABLE = "CREATE TABLE " + TABLE_TEAM + "("
                + COLUMN_TEAM_ID + " INTEGER PRIMARY KEY," + COLUMN_TEAM_NAME + " TEXT,"
                + COLUMN_TEAM_RUN + " INTEGER, " + COLUMN_TEAM_BALL + " INTEGER, " + COLUMN_TEAM_WICKET + " INTEGER" + ")";
        String CREATE_PLAYER1_TABLE = "CREATE TABLE " + TABLE_TEAM_ONE_PLAYER + "("
                + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY," + COLUMN_PLAYER_NAME + " TEXT,"
                + COLUMN_PLAYER_RUN + " INTEGER, " + COLUMN_PLAYER_BALL + " INTEGER, " + COLUMN_PLAYER_FOUR
                + " INTEGER, " + COLUMN_PLAYER_SIX + " INTEGER, " + COLUMN_PLAYER_BOWLER_RUN  + " INTEGER, "
                + COLUMN_PLAYER_BOWLER_BALL + " INTEGER, " + COLUMN_PLAYER_BOWLER_WICKET +  " INTEGER" + ")";

        String CREATE_PLAYER2_TABLE = "CREATE TABLE " + TABLE_TEAM_TWO_PLAYER + "("
                + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY," + COLUMN_PLAYER_NAME + " TEXT,"
                + COLUMN_PLAYER_RUN + " INTEGER, " + COLUMN_PLAYER_BALL + " INTEGER, " + COLUMN_PLAYER_FOUR
                + " INTEGER, " + COLUMN_PLAYER_SIX + " INTEGER, " + COLUMN_PLAYER_BOWLER_RUN  + " INTEGER, "
                + COLUMN_PLAYER_BOWLER_BALL + " INTEGER, " + COLUMN_PLAYER_BOWLER_WICKET +  " INTEGER" + ")";

        db.execSQL(CREATE_TEAM_TABLE);
        db.execSQL(CREATE_PLAYER1_TABLE);
        db.execSQL(CREATE_PLAYER2_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        onCreate(db);
    }
}