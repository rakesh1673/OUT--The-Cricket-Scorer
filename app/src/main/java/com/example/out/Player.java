package com.example.out;

public class Player {
    public int id;
    public String name;
    public int run;
    public int ball;
    public int four;
    public int six;
    public int bowlerRun;
    public int bowlerBall;
    public int wickets;
    public double economyRate;
    public double strikeRate;

    public Player() {
        init("Player");
    }

    public Player(int playerNum) {
        init("Player " + playerNum);
        id = playerNum;
    }

    private void init(String playerName) {
        name = playerName;
        run = 0;
        ball = 0;
        four = 0;
        six = 0;
        bowlerBall = 0;
        bowlerRun = 0;
        wickets = 0;
        strikeRate = 0.0;
        economyRate = 0.0;
    }

    public void strikeRate() {
        strikeRate = run * 1.0 / ball * 100.0;
    }

    public double economyRate() {
        economyRate = bowlerRun * 1.0 / bowlerBall * 6;
        return economyRate;
    }

    public int over() {
        return bowlerBall / 6;
    }

    public int overBall() {
        return bowlerBall % 6;
    }

    @Override
    public String toString() {
        return name;
    }
}