package de.unverwunderbar.legacy.legacyutils.message;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class UMessage {
    public final BaseComponent message;

    protected UMessage(BaseComponent message) {
        this.message = message;
    }

    public abstract void send(Player player);

    public void send(Player... players) {
        for(Player player : players)
            send(player);
    }

    public void broadcast() {
        send(Bukkit.getOnlinePlayers().toArray(new Player[0]));
    }

}
