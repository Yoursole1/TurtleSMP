package me.yoursole.turtlesmp.event;

import me.yoursole.turtlesmp.data.DataHolder;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        if(!(DataHolder.data.getLivesLeft().get(e.getPlayer().getUniqueId()) != null &&
        DataHolder.data.getLives(e.getPlayer().getUniqueId()) <= 0)){
            DataHolder.data.removeLife(e.getPlayer().getUniqueId());
            return;
        }

        if(!e.getPlayer().isOp()){
            e.getPlayer().banPlayer("You ran out of lives D:");
        }else{
            e.getPlayer().sendMessage(ChatColor.RED +"You ran out of lives but are OP so weren't banned");
        }

    }
}
