package me.galaxy.playagainaddon.listeners;

import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import me.galaxy.playagainaddon.PlayAgainAddon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class GameEndListener implements Listener {

    @EventHandler
    public void onGameEnd(GameEndEvent e) {

        Bukkit.getScheduler().runTaskLater(PlayAgainAddon.getInstance(), () -> {

                for(UUID uuid : new ArrayList<>(e.getAliveWinners())){
                    Player p = Bukkit.getPlayer(uuid);
                    PlayAgainAddon.getInstance().addPlayAgainItem(p);
                }

        }, 20L);
    }
}
