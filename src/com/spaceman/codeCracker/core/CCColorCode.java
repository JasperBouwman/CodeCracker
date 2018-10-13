package com.spaceman.codeCracker.core;

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class CCColorCode {

    private ArrayList<CCColor> colors = new ArrayList<>();

    public CCColorCode(ArrayList<CCColor> colors) {
        this.colors.addAll(colors);
    }

    static CCColorCode generateRandom(int size) {
        ArrayList<CCColor> colors = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            colors.add(CCColor.random(colors));

        }
        return new CCColorCode(colors);
    }

    ArrayList<CCColor> getColors() {
        return colors;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (CCColor color : getColors()) {
            str.append(color.toString()).append("o");
        }
        str.append(ChatColor.RESET);
        return str.toString();
    }
}
