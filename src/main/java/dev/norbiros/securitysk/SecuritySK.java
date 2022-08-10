package dev.norbiros.securitysk;

import dev.norbiros.securitysk.utils.*;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class SecuritySK extends JavaPlugin {

    SecuritySK instance;
    SkriptAddon addon;

    @Override
    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("dev.norbiros.securitysk", "skript");
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Addon has been enabled!");
        ((Logger) LogManager.getRootLogger()).addFilter(new LogFilter());
        // Massive thanks to LuuckA21 for ConsoleSpamFix's code
    }

    public SecuritySK getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}