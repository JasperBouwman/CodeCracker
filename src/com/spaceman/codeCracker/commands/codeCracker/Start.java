package com.spaceman.codeCracker.commands.codeCracker;

import com.spaceman.codeCracker.commands.CmdHandler;
import com.spaceman.codeCracker.core.CCColor;
import com.spaceman.codeCracker.core.CCGame;
import org.bukkit.ChatColor;

public class Start extends CmdHandler {

    @Override
    public void run(String[] args, CCSender sender) {
        //cc start [size]

        int size = 4;

        if (args.length >= 2) {
            try {
                size = Integer.parseInt(args[1]);
                if (size > CCColor.values().length) {
                    sender.sendMessage("Game size is to high");
                    return;
                }
                if (size < 1) {
                    sender.sendMessage("Game size is to low");
                    return;

                }
            } catch (NumberFormatException nfe) {
                sender.sendMessage(args[1] + " is not a number");
                return;
            }
        }

        CCGame.startGame(sender, size);
        sender.sendMessage(ChatColor.DARK_AQUA + "Game has started");
    }
}
