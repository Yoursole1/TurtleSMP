package me.yoursole.turtlesmp.event;

import me.yoursole.turtlesmp.data.DataHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(e.hasChangedOrientation()){ //you don't change orientation in an AFK pool
            DataHolder.data.setLastTimeMove(e.getPlayer().getUniqueId(), System.currentTimeMillis());
        }
    }
}
