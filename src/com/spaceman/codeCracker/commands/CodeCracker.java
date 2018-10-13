package com.spaceman.codeCracker.commands;

import com.spaceman.codeCracker.Main;
import com.spaceman.codeCracker.commands.codeCracker.Forfeit;
import com.spaceman.codeCracker.commands.codeCracker.Guess;
import com.spaceman.codeCracker.commands.codeCracker.Guesses;
import com.spaceman.codeCracker.commands.codeCracker.Start;
import com.spaceman.codeCracker.core.CCColor;
import com.spaceman.codeCracker.core.CCGame;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeCracker implements CommandExecutor, TabCompleter {

    private ArrayList<CmdHandler> actions = new ArrayList<>();

    private Main main;

    public CodeCracker(Main main) {
        actions.add(new Start());
        actions.add(new Guess());
        actions.add(new Guesses());
        actions.add(new Forfeit());
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        //cc start
        //cc guess <colors...>
        //cc guesses

        if (!(commandSender instanceof Player) && !(commandSender instanceof ConsoleCommandSender)) {
            commandSender.sendMessage("You must be a player or console to use this");
            return false;
        }

        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.DARK_AQUA + "CodeCracker is a minigame where you can crack color codes.");
            commandSender.sendMessage(ChatColor.DARK_AQUA + "Use" + ChatColor.BLUE + " /cc start [size] " + ChatColor.DARK_AQUA + "to create a CodeCracker game.");
            commandSender.sendMessage(ChatColor.DARK_AQUA + "When you have created a game use" + ChatColor.BLUE + " /cc guess <color...> " + ChatColor.DARK_AQUA + "to guess the code.");
            commandSender.sendMessage(ChatColor.DARK_AQUA + "The first number is the amount of correct colors, the last number is the amount of correct placed colors.");
            commandSender.sendMessage(ChatColor.DARK_AQUA + "When you can't crack the code use" + ChatColor.BLUE + " /cc forfeit " + ChatColor.DARK_AQUA + "and this will show you the answer.");
            commandSender.sendMessage(ChatColor.DARK_AQUA + "If you lost all your guesses use" + ChatColor.BLUE + " /cc guesses [guess]" + ChatColor.DARK_AQUA + "to get them all back nicely aligned");
            return false;
        }

        for (CmdHandler action : actions) {
            if (action.getClass().getSimpleName().equalsIgnoreCase(args[0])) {
                action.run(args, new CmdHandler.CCSender(commandSender));
                return false;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        //cc start
        //cc guess <color> <color> <color> <color>
        //cc guesses

        if (args.length == 1) {
            return copyContaining(args[0], Arrays.asList("start", "guess", "guesses", "forfeit"));
        }
        CmdHandler.CCSender ccSender = new CmdHandler.CCSender(commandSender);
        if (CCGame.mmGames.containsKey(ccSender.getUniqueId())) {
            CCGame game = CCGame.mmGames.get(ccSender.getUniqueId());
            if (args.length > 1 && args.length < game.size + 2) {
                if (args[0].equalsIgnoreCase("guess")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (CCColor color : CCColor.values()) {
                        list.add(color.name().toLowerCase());
                    }
                    return copyContaining(args[args.length - 1], list);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<String> copyContaining(String s, List<String> fullList) {
        ArrayList<String> list = new ArrayList<>();
        for (String ss : fullList) {
            if (ss.toLowerCase().contains(s.toLowerCase())) {
                list.add(ss);
            }
        }
        return list;
    }
}
