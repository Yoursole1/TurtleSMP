package me.yoursole.turtlesmp.command;

import me.yoursole.turtlesmp.data.DataHolder;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class Revive implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length != 1){
            sender.sendMessage(ChatColor.RED+"Invalid command format");
            return true;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        if(!p.isBanned()){
            sender.sendMessage(ChatColor.RED+"This player is not out!");
            return true;
        }

        if(DataHolder.data.getLives(((Player)sender).getUniqueId()) >= 3){
            for(UUID uuid : DataHolder.data.getLivesLeft().keySet()){
                if(uuid.toString().equalsIgnoreCase(p.getUniqueId().toString())){
                    Bukkit.getBanList(BanList.Type.NAME).pardon(Objects.requireNonNull(p.getName()));
                    DataHolder.data.setLives((((Player)sender)).getUniqueId(), DataHolder.data.getLives((((Player)sender)).getUniqueId())-3);
                    DataHolder.data.setLives(p.getUniqueId(), 1);
                    sender.sendMessage("You revived "+ args[0]);
                    break;
                }
            }
        }else{
            sender.sendMessage(ChatColor.RED+"You need 3 lives to revive a player");
        }



        return true;
    }
}
