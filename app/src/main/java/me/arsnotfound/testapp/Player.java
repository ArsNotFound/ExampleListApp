package me.arsnotfound.testapp;

import java.util.Comparator;

public class Player {
    private String name;

    private int ball;

    private boolean isChecked;

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    static class CompBall implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            if (p1.ball != p2.ball)
                return Integer.compare(p1.ball, p2.ball);
            else
                return p1.name.compareTo(p2.name);
        }
    }
}
