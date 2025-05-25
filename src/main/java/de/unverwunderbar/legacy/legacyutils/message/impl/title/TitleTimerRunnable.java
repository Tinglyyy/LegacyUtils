package de.unverwunderbar.legacy.legacyutils.message.impl.title;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicInteger;

public class TitleTimerRunnable extends BukkitRunnable {
    private final AtomicInteger i = new AtomicInteger(0);

    @Getter
    private final int length;

    @Getter
    private boolean finished;

    private final Player player;

    private final TitleTimer titleTimer;

    public TitleTimerRunnable(TitleTimer titleTimer, int length, Player player) {
        this.length = length;

        this.titleTimer = titleTimer;
        this.player = player;
    }

    @Override
    public void run() {
        int current = i.get();

        player.sendTitle(ChatColor.values()[current] + String.valueOf(length/20 - i.get()),
                titleTimer.message.toLegacyText(),3, 15, 3);

        current++;

        i.incrementAndGet();

        if(current > length) {
            finished = true;
            this.cancel();
        }

    }
}
