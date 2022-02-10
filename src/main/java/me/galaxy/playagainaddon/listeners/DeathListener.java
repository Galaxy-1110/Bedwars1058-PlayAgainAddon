package me.galaxy.playagainaddon.listeners;

import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import me.galaxy.playagainaddon.PlayAgainAddon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DeathListener implements Listener {

    @EventHandler
    public void onFinalKill( PlayerKillEvent e ) {
        if(e.getCause().isFinalKill()) {
            PlayAgainAddon.getInstance().addPlayAgainItem(e.getVictim());
        }
    }
}
