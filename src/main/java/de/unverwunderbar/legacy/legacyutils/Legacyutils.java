package de.unverwunderbar.legacy.legacyutils;

import com.comphenix.protocol.ProtocolLib;
import de.unverwunderbar.legacy.legacyutils.scoreboard.AnimatedScoreBoard;
import de.unverwunderbar.legacy.legacyutils.scoreboard.UScoreBoard;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

public final class Legacyutils extends JavaPlugin implements Listener {

    public static Legacyutils main;

    public static ProtocolLib protocolLib;

    @Override
    public void onEnable() {
        main = this;

        protocolLib = (ProtocolLib) getServer().getPluginManager().getPlugin("ProtocolLib");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        AnimatedScoreBoard scoreBoard = new AnimatedScoreBoard(DisplaySlot.SIDEBAR, "hi", "moin", "wasgeht\n", "AAA");
        scoreBoard.show(e.getPlayer());

        Bukkit.getScheduler().runTaskLater(this, () -> {
            scoreBoard.animate(5);
        }, 30);
    }

    @Override
    public void onDisable() {
    }
}
