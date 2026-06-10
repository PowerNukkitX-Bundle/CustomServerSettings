package org.powernukkitx.customserversettings.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.network.protocol.ServerSettingsRequestPacket;
import cn.nukkit.network.protocol.ServerSettingsResponsePacket;
import org.powernukkitx.customserversettings.Plugin;
import org.powernukkitx.customserversettings.event.ServerSettingsSendEvent;

public class PacketReceiveListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    private void on(DataPacketReceiveEvent event) {
        var player = event.getPlayer();
        var packet = event.getPacket();

        if (player == null) return;

        if (packet instanceof ServerSettingsRequestPacket) {
            var form = Plugin.getPlugin().getSettingsForm();
            var sendEvent = new ServerSettingsSendEvent(player, form.getFormId(), form.getFormData());
            player.getServer().getPluginManager().callEvent(sendEvent);

            if (sendEvent.isCancelled()) return;

            var response = new ServerSettingsResponsePacket();
            response.setFormId(sendEvent.getFormId());
            response.setData(sendEvent.getFormData());
            player.dataPacket(response);
        }
    }
}
