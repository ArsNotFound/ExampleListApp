package me.arsnotfound.testapp;

import java.util.Comparator;

public class Player {
    private String name;

    private int ball;

    Player() {
        this("Someone", 0);
    }

    Player(String s, int b) {
        this.name = s;
        this.ball = b;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    static class CompBall implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            int m1 = p1.getBall();
            int m2 = p2.getBall();
            return Integer.compare(m1, m2);
        }
    }
}
