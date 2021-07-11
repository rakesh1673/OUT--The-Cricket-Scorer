package com.example.out;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public long id;
    public String name;
    public List<Player> players;
    public int run;
    public boolean isFirstInnings;
    public boolean isWon;
    public int wicket;
    public int ball;

    public Team() {
        init("Untitled");
    }

    public Team(int teamNo) {
        init("Team " + teamNo);
        id = teamNo;
    }

    private void init(String teamName) {
        name = teamName;
        players = new ArrayList<>();
        run = 0;
        isFirstInnings = false;
        isWon = false;
        wicket = 0;
        ball = 0;
    }

    public double runRate() {
        return run * 1.0 / ball * 6;
    }

    public int over() {
        return ball / 6;
    }

    public int overBall() {
        return ball % 6;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", run=" + run +
                ", wicket=" + wicket +
                ", ball=" + ball +
                '}';
    }
}