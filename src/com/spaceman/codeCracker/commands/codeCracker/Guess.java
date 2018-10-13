package com.spaceman.codeCracker.commands.codeCracker;

import com.spaceman.codeCracker.commands.CmdHandler;
import com.spaceman.codeCracker.core.CCColor;
import com.spaceman.codeCracker.core.CCColorCode;
import com.spaceman.codeCracker.core.CCGame;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Random;

public class Guess extends CmdHandler {

    @Override
    public void run(String[] args, CCSender sender) {
        //cc guess <colors...>

        if (!CCGame.mmGames.containsKey(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "You haven't started a game");
            return;
        }

        CCGame game = CCGame.mmGames.get(sender.getUniqueId());
        if (args.length == game.size+1) {

            boolean b = false;
            ArrayList<CCColor> colors = new ArrayList<>();
            for (int i = 1; i < game.size + 1; i++) {
                try {
                    CCColor color = CCColor.valueOf(args[i].toUpperCase());
                    if (colors.contains(color)) {
                        sender.sendMessage(ChatColor.RED + "Color " + color.toString() + color.name().toLowerCase() + ChatColor.RED + " is already used in your color code");
                        b = true;
                    }
                    colors.add(color);
                } catch (IllegalArgumentException iae) {
                    sender.sendMessage(ChatColor.DARK_RED + args[i].toLowerCase() + ChatColor.RED + " is not a color");
                    b = true;
                }
            }
            if (b) {
                return;
            }

            CCColorCode colorCode = new CCColorCode(colors);
            CCGame.CCGuess guess = game.addGuess(colorCode);

//            game.getGuesses().get(game.getGuesses().size() - 1);
            sender.sendMessage(ChatColor.DARK_AQUA + "Guess " + ChatColor.DARK_GREEN + game.getGuesses().size() + ChatColor.DARK_AQUA + ": "
                    + ChatColor.WHITE + guess.getGoodColors() + " [" + guess.getColorCode().toString() + ChatColor.WHITE + "] " + ChatColor.RED + guess.getGoodPlaces());

            if (guess.getGoodPlaces() == game.size) {
                StringBuilder str = new StringBuilder();
                for (char c : "Game Solved!".toCharArray()) {
                    ChatColor chatColor = ChatColor.valueOf(ChatColor.values()[new Random().nextInt(ChatColor.values().length)].name());
                    while (chatColor.isFormat() || chatColor.equals(ChatColor.RESET)) {
                        chatColor = ChatColor.valueOf(ChatColor.values()[new Random().nextInt(ChatColor.values().length)].name());
                    }
                    str.append(chatColor);
                    str.append(c);
                }
                sender.sendMessage(str.toString());
                CCGame.mmGames.remove(sender.getUniqueId());
            }

        } else {
            StringBuilder str = new StringBuilder(ChatColor.RED + "Usage:" + ChatColor.DARK_RED + " /cc guess");
            for (int i = 0; i < game.size; i++) {
                str.append(" <color>");
            }
            sender.sendMessage(str.toString());
        }

    }
}
