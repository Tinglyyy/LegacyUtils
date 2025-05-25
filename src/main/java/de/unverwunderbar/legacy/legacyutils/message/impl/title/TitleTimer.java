package de.unverwunderbar.legacy.legacyutils.message.impl.title;

import de.unverwunderbar.legacy.legacyutils.message.UMessage;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import static de.unverwunderbar.legacy.legacyutils.Legacyutils.main;

public class TitleTimer extends UMessage {
    private int length, delay, period;

    private TitleTimerRunnable runnable = null;

    public TitleTimer(String subtitle, int length, int delay, int period) {
        super(new TextComponent(subtitle));

        this.length = length;
        this.delay = delay;
        this.period = period;

    }

    public boolean getFinished() {
        if(runnable == null)
            return false;

        return runnable.isFinished();
    }

    @Override
    public void send(Player player) {
        runnable = new TitleTimerRunnable(this, length, player);

        runnable.runTaskTimerAsynchronously(main, delay, period);
    }

}
