package com.spaceman.codeCracker;

import com.spaceman.codeCracker.commands.CodeCracker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        getCommand("CodeCracker").setExecutor(new CodeCracker(this));
        getCommand("CodeCracker").setAliases(Collections.singletonList("cc"));
    }

}
