package com.spaceman.codeCracker.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class CmdHandler {

    public abstract void run(String[] args, CCSender sender);

    public static class CCSender {
        private String uuid = "";
        private CommandSender sender = null;

        CCSender(CommandSender sender) {
            if (sender instanceof Player) {
                this.uuid = ((Player) sender).getUniqueId().toString();
                this.sender = sender;
            } else if (sender instanceof ConsoleCommandSender) {
                uuid = sender.getName();
                this.sender = sender;
            }
        }

        public void sendMessage(String message) {
            if (sender != null) {
                sender.sendMessage(message);
            }
        }

        public String getUniqueId() {
            return uuid;
        }
    }
}

