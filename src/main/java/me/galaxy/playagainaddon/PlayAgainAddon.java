package me.galaxy.playagainaddon;

import com.andrei1058.bedwars.api.BedWars;
import com.cryptomorin.xseries.XMaterial;

import me.galaxy.playagainaddon.utils.FileManager;
import me.galaxy.playagainaddon.utils.TextUtil;
import me.galaxy.playagainaddon.utils.Metrics;
import me.galaxy.playagainaddon.commands.ReloadCommand;
import me.galaxy.playagainaddon.listeners.DeathListener;
import me.galaxy.playagainaddon.listeners.GameEndListener;
import me.galaxy.playagainaddon.listeners.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayAgainAddon extends JavaPlugin {

    private static PlayAgainAddon instance;
    public FileManager config;
    public ItemStack playAgainItem;
    public BedWars bw;

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("BedWars1058") != null ){
            instance = this;
            bw = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
            new ReloadCommand(bw.getBedWarsCommand(), "playagain-reload");
            config = new FileManager("config", "plugins/BedWars1058/Addons/PlayAgain");
            getServer().getPluginManager().registerEvents(new GameEndListener(), this);
            getServer().getPluginManager().registerEvents(new InteractListener(), this);
            getServer().getPluginManager().registerEvents(new DeathListener(), this);
            int pluginId = 14060;
            Metrics metrics = new Metrics(this, pluginId);
            Bukkit.getConsoleSender().sendMessage(TextUtil.color("&8--------------------------------------------------"));
            Bukkit.getConsoleSender().sendMessage(TextUtil.color("&aBedWars1058-PlayAgain &7by &cCubeCrafter &7Edited by &cGalaxy"));
            Bukkit.getConsoleSender().sendMessage(TextUtil.color("&8--------------------------------------------------"));

        } else {
            getLogger().severe("BedWars1058 was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public void addPlayAgainItem(Player p){
        Bukkit.getScheduler().runTaskLater(this, () -> {
            playAgainItem = XMaterial.matchXMaterial(config.getYml().getString("play-again-item.material")).get().parseItem();
            ItemMeta meta = playAgainItem.getItemMeta();
            meta.setDisplayName(TextUtil.color(config.getYml().getString("play-again-item.displayname")));
            meta.setLore(TextUtil.color(config.getYml().getStringList("play-again-item.lore")));
            playAgainItem.setItemMeta(meta);
            p.getInventory().setItem(config.getYml().getInt("play-again-item.slot"), playAgainItem);
        }, 20L);
    }

    public static PlayAgainAddon getInstance() {
        return instance;
    }
}
