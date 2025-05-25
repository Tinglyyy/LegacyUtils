package de.unverwunderbar.legacy.legacyutils.message.impl.title;

import de.unverwunderbar.legacy.legacyutils.message.UMessage;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class TitleMessage extends UMessage {
    private final int fadeIn, stay, fadeOut;

    public TitleMessage(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        super(new TextComponent(
                new TextComponent(title),
                new TextComponent(subtitle)
        ));

        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public TitleMessage(String title, String subtitle) {
        this(title, subtitle, -1, -1, -1);
    }

    @Override
    public void send(Player player) {
        String title = this.message.getExtra().get(0).toLegacyText();
        String subtitle = this.message.getExtra().get(1).toLegacyText();
        if(fadeIn == -1)
            player.sendTitle(title, subtitle);
        else
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
