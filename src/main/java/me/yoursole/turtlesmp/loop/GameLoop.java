package me.yoursole.turtlesmp.loop;


import me.yoursole.turtlesmp.TurtleSMP;
import me.yoursole.turtlesmp.data.DataHolder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameLoop {

    public static void cancelRunnable(){
        Bukkit.getServer().getScheduler().cancelTask(Loop.taskID);
    }

    public static void startLoops(){
        Loop loop = new Loop();
        loop.start();
    }
}

class Loop extends Thread{

    static int taskID = -1;

    synchronized public void run() {

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(TurtleSMP.getInstance(), () -> {

            for(Player p : Bukkit.getOnlinePlayers()){
                if(DataHolder.data.isAfk(p)){
                    p.sendActionBar(new TextComponent(ChatColor.RED+"You are registered as AFK (no life points)"));
                    continue;
                }

                DataHolder.data.setSeconds(p.getUniqueId(), DataHolder.data.getSeconds(p.getUniqueId()) +1);
                if(DataHolder.data.getTimePlayingSeconds().get(p.getUniqueId()) % 10800 == 0){
                    DataHolder.data.setSeconds(p.getUniqueId(), 0L);
                    DataHolder.data.setLives(p.getUniqueId(), DataHolder.data.getLives(p.getUniqueId())+1);
                    p.sendMessage(ChatColor.GREEN+"You gained a life (playtime)!");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 5);
                }
                p.sendActionBar(new TextComponent(ChatColor.GREEN + "You have "+ DataHolder.data.getLives(p.getUniqueId())+" lives left"));
            }

        }, 0L, 20L);
    }
}


