package me.yoursole.turtlesmp.event;

import me.yoursole.turtlesmp.data.DataHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!DataHolder.data.getLivesLeft().containsKey(e.getPlayer().getUniqueId())){
            DataHolder.data.setLives(e.getPlayer().getUniqueId(), 3);
        }
        if(!DataHolder.data.getLastMoveTime().containsKey(e.getPlayer().getUniqueId())){
            DataHolder.data.setLastTimeMove(e.getPlayer().getUniqueId(), 0L);
        }
        if(!DataHolder.data.getTimePlayingSeconds().containsKey(e.getPlayer().getUniqueId())){
            DataHolder.data.setSeconds(e.getPlayer().getUniqueId(), 0L);
        }
    }
}
