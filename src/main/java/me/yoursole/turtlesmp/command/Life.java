package me.yoursole.turtlesmp.command;

import me.yoursole.turtlesmp.data.DataHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Life implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 2){
            sender.sendMessage(ChatColor.RED+"Invalid command format");
            return true;
        }
        switch (args[0]) {
            case "add" -> {
                UUID target = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
                for (UUID uuid : DataHolder.data.getLivesLeft().keySet()) {
                    if (uuid.toString().equalsIgnoreCase(target.toString())) {
                        DataHolder.data.setLives(uuid, DataHolder.data.getLives(uuid) + 1);
                        sender.sendMessage("Successfully added life");
                        return true;
                    }
                }
                sender.sendMessage("Failed to add life (maybe invalid name?)");
            }
            case "remove" -> {
                UUID target = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
                for (UUID uuid : DataHolder.data.getLivesLeft().keySet()) {
                    if (uuid.toString().equalsIgnoreCase(target.toString())) {
                        DataHolder.data.removeLife(uuid);
                        sender.sendMessage("Successfully removed life");
                        return true;
                    }
                }
                sender.sendMessage("Failed to remove life (maybe invalid name?)");
            }
        }

        return true;
    }
}
