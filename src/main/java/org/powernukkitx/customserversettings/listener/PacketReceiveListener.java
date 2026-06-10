package org.powernukkitx.customserversettings.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.PacketReceiveEvent;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerSettingsResponsePacket;
import org.powernukkitx.customserversettings.Plugin;
import org.powernukkitx.customserversettings.event.ServerSettingsSendEvent;

public class PacketReceiveListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void on(PacketReceiveEvent event) {
        var player = event.getPlayer();
        var packet = event.getPacket();

        if (player == null) return;

        if (packet instanceof ServerSettingsRequestPacket) {
            var form = Plugin.getPlugin().getSettingsForm();
            var sendEvent = new ServerSettingsSendEvent(player, form.getFormId(), form.getFormData());
            player.getServer().getPluginManager().callEvent(sendEvent);

            if (sendEvent.isCancelled()) return;

            var response = new ServerSettingsResponsePacket();
            response.setFormID(sendEvent.getFormId());
            response.setFormData(sendEvent.getFormData());
            player.sendPacket(response);
        }
    }
}
