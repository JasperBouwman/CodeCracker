package com.spaceman.codeCracker.commands.codeCracker;

import com.spaceman.codeCracker.commands.CmdHandler;
import com.spaceman.codeCracker.core.CCColorCode;
import com.spaceman.codeCracker.core.CCGame;
import org.bukkit.ChatColor;

public class Forfeit extends CmdHandler {

    @Override
    public void run(String[] args, CCSender sender) {
        //cc forfeit

        if (!CCGame.mmGames.containsKey(sender.getUniqueId())) {
            sender.sendMessage(ChatColor.RED + "You haven't started a game");
            return;
        }

        CCGame game = CCGame.mmGames.get(sender.getUniqueId());
        CCColorCode colorCode = game.forfeit(sender);

        sender.sendMessage(ChatColor.DARK_AQUA + "Awh snap. You could not crack the code, the code was: " + ChatColor.WHITE + "[" + colorCode.toString() + ChatColor.WHITE + "]");

    }
}
