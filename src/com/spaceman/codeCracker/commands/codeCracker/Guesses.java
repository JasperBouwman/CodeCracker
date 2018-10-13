package com.spaceman.codeCracker.commands.codeCracker;

import com.spaceman.codeCracker.commands.CmdHandler;
import com.spaceman.codeCracker.core.CCGame;
import org.bukkit.ChatColor;

public class Guesses extends CmdHandler {

    @Override
    public void run(String[] args, CCSender sender) {
        //cc guesses [guess]

        if (!CCGame.mmGames.containsKey(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "You haven't started a game");
            return;
        }

        CCGame game = CCGame.mmGames.get(sender.getUniqueId());

        if (args.length == 2) {
            int i;
            try {
                i = Integer.parseInt(args[1]);
            } catch (NumberFormatException nfe) {
                sender.sendMessage(ChatColor.DARK_RED + args[1] + ChatColor.RED + " is not a valid number");
                return;
            }
            CCGame.CCGuess guess = game.getGuesses().getOrDefault(i - 1, null);
            if (guess == null) {
                sender.sendMessage(ChatColor.RED + "You haven't reached " + ChatColor.DARK_RED + i + ChatColor.RED + " guess" + (i == 1 ? "" : "es"));
                return;
            }

            sender.sendMessage(ChatColor.DARK_AQUA + "Guess " + ChatColor.DARK_GREEN + i + ChatColor.DARK_AQUA + ": "
                    + ChatColor.WHITE + guess.getGoodColors() + " [" + guess.getColorCode().toString() + ChatColor.WHITE + "] " + ChatColor.RED + guess.getGoodPlaces());
            return;
        }


        sender.sendMessage(ChatColor.DARK_AQUA + "Full guess list: ");

        int tmp = String.valueOf(game.getGuesses().keySet().size()).length() - 1;

        for (int i : game.getGuesses().keySet()) {
            CCGame.CCGuess guess = game.getGuesses().get(i);
            StringBuilder prefix = new StringBuilder();
            for (int j = 0; j < tmp; j++) {
                prefix.append("0");
            }
            sender.sendMessage(ChatColor.DARK_AQUA + "Guess " + ChatColor.DARK_GREEN + prefix + (i + 1) + ChatColor.DARK_AQUA + ": "
                    + ChatColor.WHITE + guess.getGoodColors() + " [" + guess.getColorCode().toString() + ChatColor.WHITE + "] " + ChatColor.RED + guess.getGoodPlaces());
            if (((i + 2) == 10 || (i + 2) == 100 || (i + 2) == 1000) && i != 0) {
                tmp--;
            }
        }
    }
}
