package com.spaceman.codeCracker.core;

import com.spaceman.codeCracker.commands.CmdHandler;

import java.util.HashMap;

public class CCGame {

    public static HashMap<String, CCGame> mmGames = new HashMap<>();
    private final CCColorCode code;
    public final int size;
    private HashMap<Integer, CCGuess> guesses = new HashMap<>();

    private CCGame(CCColorCode code, int size) {
        this.code = code;
        this.size = size;
    }

    public static void startGame(CmdHandler.CCSender sender, int size) {
        CCGame game = new CCGame(CCColorCode.generateRandom(size), size);
        mmGames.put(sender.getUniqueId(), game);
    }

    public CCColorCode forfeit(CmdHandler.CCSender sender) {
        CCGame.mmGames.remove(sender.getUniqueId());
        return code;
    }

    public CCGuess addGuess(CCColorCode colorCode) {
        int goodPlaces = 0;
        int goodColors = 0;

        for (int i = 0; i < size; i++) {
            if (code.getColors().contains(colorCode.getColors().get(i))) {
                goodColors++;

                if (code.getColors().get(i).equals(colorCode.getColors().get(i))) {
                    goodPlaces++;
                }
            }
        }

        CCGuess guess = new CCGuess(colorCode, goodPlaces, goodColors);
        guesses.put(guesses.keySet().size(), guess);
        return guess;
    }

    public HashMap<Integer, CCGuess> getGuesses() {
        return guesses;
    }

    public class CCGuess {
        private CCColorCode colorCode;
        private int goodPlaces;
        private int goodColors;

        CCGuess(CCColorCode colorCode, int goodPlaces, int goodColors) {
            this.colorCode = colorCode;
            this.goodPlaces = goodPlaces;
            this.goodColors = goodColors;
        }

        public int getGoodColors() {
            return goodColors;
        }

        public int getGoodPlaces() {
            return goodPlaces;
        }

        public CCColorCode getColorCode() {
            return colorCode;
        }
    }
}
