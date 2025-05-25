package de.unverwunderbar.legacy.legacyutils.message.impl.actionbar;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import de.unverwunderbar.legacy.legacyutils.message.UMessage;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ActionBarMessage extends UMessage {
    public ActionBarMessage(String message) {
        super(new TextComponent(message));
    }

    @Override
    public void send(Player player) {

        PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.CHAT);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(message.toLegacyText()));
        packet.getChatTypes().write(0, EnumWrappers.ChatType.GAME_INFO); // GAME_INFO = action bar

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
