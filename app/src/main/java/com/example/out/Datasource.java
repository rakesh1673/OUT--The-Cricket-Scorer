package com.example.out;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Datasource {
    private SQLiteDatabase database;
    private SQLiteOpenHelper dbhelper;

    public Datasource(Context context) {
        dbhelper = new DBOpenHelper(context);
    }

    public void addTeamScore(Team team) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_TEAM_ID, team.id);
        values.put(DBOpenHelper.COLUMN_TEAM_NAME, team.name);
        values.put(DBOpenHelper.COLUMN_TEAM_RUN, team.run);
        values.put(DBOpenHelper.COLUMN_TEAM_BALL, team.ball);
        values.put(DBOpenHelper.COLUMN_TEAM_WICKET, team.wicket);

        database.insert(DBOpenHelper.TABLE_TEAM, null, values);
    }

    public void insertPlayerOne(List<Player> players) {
        for (Player player : players) {
            ContentValues values = new ContentValues();
            values.put(DBOpenHelper.COLUMN_PLAYER_ID, player.id);
            values.put(DBOpenHelper.COLUMN_PLAYER_NAME, player.name);
            values.put(DBOpenHelper.COLUMN_PLAYER_RUN, player.run);
            values.put(DBOpenHelper.COLUMN_PLAYER_BALL, player.ball);
            values.put(DBOpenHelper.COLUMN_PLAYER_FOUR, player.four);
            values.put(DBOpenHelper.COLUMN_PLAYER_SIX, player.six);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN, player.bowlerRun);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL, player.bowlerBall);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET, player.wickets);
            database.insert(DBOpenHelper.TABLE_TEAM_ONE_PLAYER, null, values);
        }
    }

    public void insertPlayerTwo(List<Player> players) {
        for (Player player : players) {
            ContentValues values = new ContentValues();
            values.put(DBOpenHelper.COLUMN_PLAYER_ID, player.id);
            values.put(DBOpenHelper.COLUMN_PLAYER_NAME, player.name);
            values.put(DBOpenHelper.COLUMN_PLAYER_RUN, player.run);
            values.put(DBOpenHelper.COLUMN_PLAYER_BALL, player.ball);
            values.put(DBOpenHelper.COLUMN_PLAYER_FOUR, player.four);
            values.put(DBOpenHelper.COLUMN_PLAYER_SIX, player.six);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN, player.bowlerRun);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL, player.bowlerBall);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET, player.wickets);
            database.insert(DBOpenHelper.TABLE_TEAM_TWO_PLAYER, null, values);
        }
    }

    public void updatePlayerTwo(List<Player> players) {
        for (Player player : players) {
            ContentValues values = new ContentValues();
            values.put(DBOpenHelper.COLUMN_PLAYER_ID, player.id);
            values.put(DBOpenHelper.COLUMN_PLAYER_NAME, player.name);
            values.put(DBOpenHelper.COLUMN_PLAYER_RUN, player.run);
            values.put(DBOpenHelper.COLUMN_PLAYER_BALL, player.ball);
            values.put(DBOpenHelper.COLUMN_PLAYER_FOUR, player.four);
            values.put(DBOpenHelper.COLUMN_PLAYER_SIX, player.six);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN, player.bowlerRun);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL, player.bowlerBall);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET, player.wickets);
            database.update(DBOpenHelper.TABLE_TEAM_TWO_PLAYER, values, "id=" + player.id, null);
        }
    }

    public void updatePlayerOne(List<Player> players) {
        for (Player player : players) {
            ContentValues values = new ContentValues();
            values.put(DBOpenHelper.COLUMN_PLAYER_ID, player.id);
            values.put(DBOpenHelper.COLUMN_PLAYER_NAME, player.name);
            values.put(DBOpenHelper.COLUMN_PLAYER_RUN, player.run);
            values.put(DBOpenHelper.COLUMN_PLAYER_BALL, player.ball);
            values.put(DBOpenHelper.COLUMN_PLAYER_FOUR, player.four);
            values.put(DBOpenHelper.COLUMN_PLAYER_SIX, player.six);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN, player.bowlerRun);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL, player.bowlerBall);
            values.put(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET, player.wickets);
            database.update(DBOpenHelper.TABLE_TEAM_ONE_PLAYER, values, "id=" + player.id, null);
        }
    }

    public void updateTeamScore(Team team) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLUMN_TEAM_ID, team.id);
        values.put(DBOpenHelper.COLUMN_TEAM_NAME, team.name);
        values.put(DBOpenHelper.COLUMN_TEAM_RUN, team.run);
        values.put(DBOpenHelper.COLUMN_TEAM_BALL, team.ball);
        values.put(DBOpenHelper.COLUMN_TEAM_WICKET, team.wicket);
        database.update(DBOpenHelper.TABLE_TEAM, values, "id=" + team.id, null);
    }

    public List<Player> getPlayerOneList() {
        List<Player> players = new ArrayList<>();

        Cursor cursor = database.query(DBOpenHelper.TABLE_TEAM_ONE_PLAYER,
                new String[]{DBOpenHelper.COLUMN_PLAYER_ID,
                        DBOpenHelper.COLUMN_PLAYER_NAME,
                        DBOpenHelper.COLUMN_PLAYER_RUN,
                        DBOpenHelper.COLUMN_PLAYER_BALL,
                        DBOpenHelper.COLUMN_PLAYER_FOUR,
                        DBOpenHelper.COLUMN_PLAYER_SIX,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET}, null, null,
                null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Player player = new Player();
                player.id = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_ID));
                player.name = cursor.getString(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_NAME));
                player.run = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_RUN));
                player.ball = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BALL));
                player.four = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_FOUR));
                player.six = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_SIX));
                player.bowlerRun = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN));
                player.bowlerBall = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL));
                player.wickets = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET));

                players.add(player);
            }
        }

        return players;
    }

    public List<Player> getPlayerTwoList() {
        List<Player> players = new ArrayList<>();

        Cursor cursor = database.query(DBOpenHelper.TABLE_TEAM_TWO_PLAYER,
                new String[]{DBOpenHelper.COLUMN_PLAYER_ID,
                        DBOpenHelper.COLUMN_PLAYER_NAME,
                        DBOpenHelper.COLUMN_PLAYER_RUN,
                        DBOpenHelper.COLUMN_PLAYER_BALL,
                        DBOpenHelper.COLUMN_PLAYER_FOUR,
                        DBOpenHelper.COLUMN_PLAYER_SIX,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL,
                        DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET}, null, null,
                null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Player player = new Player();
                player.id = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_ID));
                player.name = cursor.getString(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_NAME));
                player.run = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_RUN));
                player.ball = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BALL));
                player.four = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_FOUR));
                player.six = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_SIX));
                player.bowlerRun = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_RUN));
                player.bowlerBall = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_BALL));
                player.wickets = cursor.getInt(cursor
                        .getColumnIndex(DBOpenHelper.COLUMN_PLAYER_BOWLER_WICKET));

                players.add(player);
            }
        }

        return players;
    }

    public Team getTeamScore(int id) {
        Cursor cursor = database.query(DBOpenHelper.TABLE_TEAM, new String[]{DBOpenHelper.COLUMN_TEAM_ID,
                        DBOpenHelper.COLUMN_TEAM_NAME, DBOpenHelper.COLUMN_TEAM_RUN, DBOpenHelper.COLUMN_TEAM_BALL, DBOpenHelper.COLUMN_TEAM_WICKET}, DBOpenHelper.COLUMN_TEAM_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Team team = new Team();
        team.id = cursor.getInt(0);
        team.name = cursor.getString(1);
        team.run = Integer.parseInt(cursor.getString(2));
        team.ball = Integer.parseInt(cursor.getString(3));
        team.wicket = Integer.parseInt(cursor.getString(4));

        return team;
    }

    public void open() {
        database = dbhelper.getWritableDatabase();
    }

    public void close() {
        dbhelper.close();
    }


}