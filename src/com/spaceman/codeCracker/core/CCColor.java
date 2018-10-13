package com.spaceman.codeCracker.core;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Random;

public enum CCColor {
    GREEN(ChatColor.GREEN),
    BLUE(ChatColor.BLUE),
    YELLOW(ChatColor.YELLOW),
    RED(ChatColor.RED),
    SILVER(ChatColor.GRAY),
    PINK(ChatColor.LIGHT_PURPLE),
    ORANGE(ChatColor.GOLD),
    WHITE(ChatColor.WHITE);

    private final ChatColor chatColor;
    CCColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public static CCColor random(ArrayList<CCColor> colors) {
        CCColor newColor = CCColor.valueOf(CCColor.values()[new Random().nextInt(CCColor.values().length)].name());
        while (colors.contains(newColor)) {
            newColor = CCColor.valueOf(CCColor.values()[new Random().nextInt(CCColor.values().length)].name());
        }
        return newColor;
    }

    @Override
    public String toString() {
        return chatColor.toString();
    }
}
