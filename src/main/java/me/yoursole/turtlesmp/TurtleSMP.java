package me.yoursole.turtlesmp;

import lombok.SneakyThrows;
import me.yoursole.turtlesmp.command.Life;
import me.yoursole.turtlesmp.command.Revive;
import me.yoursole.turtlesmp.data.Data;
import me.yoursole.turtlesmp.data.DataHolder;
import me.yoursole.turtlesmp.event.PlayerDeath;
import me.yoursole.turtlesmp.event.PlayerJoin;
import me.yoursole.turtlesmp.event.PlayerMove;
import me.yoursole.turtlesmp.loop.GameLoop;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class TurtleSMP extends JavaPlugin {

    static JavaPlugin INSTANCE;
    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        Objects.requireNonNull(this.getCommand("life")).setExecutor(new Life());
        Objects.requireNonNull(this.getCommand("revive")).setExecutor(new Revive());

        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();

        if (this.getConfig().get("data") != null) {
            try {
                DataHolder.data = new Data((String) this.getConfig().get("data"));
            } catch (IOException | ClassNotFoundException ignored) {}
        }
        GameLoop.startLoops();
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        this.getConfig().set("data", DataHolder.data.serialize());
        this.saveConfig();
        GameLoop.cancelRunnable();
    }

    public static JavaPlugin getInstance(){
        return INSTANCE;
    }
}
