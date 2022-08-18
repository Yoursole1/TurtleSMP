package me.yoursole.turtlesmp.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class Data implements Serializable{

    private HashMap<UUID, Long> lastMoveTime;
    private HashMap<UUID, Integer> livesLeft;
    private HashMap<UUID, Long> timePlayingSeconds;

    public Data(){
        this.lastMoveTime = new HashMap<>();
        this.livesLeft = new HashMap<>();
        this.timePlayingSeconds = new HashMap<>();
    }

    public Data(String serialized) throws IOException, ClassNotFoundException{
        Data deserialized = ((Data) deSerialize(serialized));
        this.lastMoveTime = deserialized.getLastMoveTime();
        this.livesLeft = deserialized.getLivesLeft();
        this.timePlayingSeconds = deserialized.getTimePlayingSeconds();
    }

    public void setSeconds(UUID uuid, long time){
        this.timePlayingSeconds.put(uuid,time);
    }
    public long getSeconds(UUID uuid){
        return this.timePlayingSeconds.get(uuid);
    }

    public void setLastTimeMove(UUID uuid, long time){
        this.lastMoveTime.put(uuid, time);
    }
    public long getLastTimeMove(UUID uuid){
        return this.lastMoveTime.get(uuid);
    }

    public void removeLife(UUID uuid){
        this.livesLeft.put(uuid, this.livesLeft.get(uuid) - 1);
    }
    public int getLives(UUID uuid){
        return this.livesLeft.get(uuid);
    }
    public void setLives(UUID uuid, int lives){
        this.livesLeft.put(uuid, lives);
    }

    public boolean isAfk(Player p){
        long time = this.lastMoveTime.get(p.getUniqueId());
        return System.currentTimeMillis()-time > 300000L; //5 minutes
    }

    public String serialize() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(this);
        oos.close();
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    private Object deSerialize(String s) throws IOException, ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o  = ois.readObject();
        ois.close();
        return o;
    }


}
