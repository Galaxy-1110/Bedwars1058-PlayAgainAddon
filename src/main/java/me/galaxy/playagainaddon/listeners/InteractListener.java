package me.galaxy.playagainaddon.listeners;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import me.galaxy.playagainaddon.PlayAgainAddon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    BedWars.ArenaUtil arenaUtil = PlayAgainAddon.getInstance().bw.getArenaUtil();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(p.getItemInHand().equals(PlayAgainAddon.getInstance().playAgainItem)){
            IArena playerArena = arenaUtil.getArenaByPlayer(p);
            if(playerArena == null) return;
            playerArena.removePlayer(p, true);
            arenaUtil.joinRandomFromGroup(p, playerArena.getGroup());
        }
    }
}
