package org.powernukkitx.customserversettings.event;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.player.PlayerEvent;
import lombok.Getter;
import lombok.Setter;

public class ServerSettingsSendEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    @Setter
    private int formId;

    @Getter
    @Setter
    private String formData;

    public ServerSettingsSendEvent(Player player, int formId, String formData) {
        this.player = player;
        this.formId = formId;
        this.formData = formData;
    }

    public static HandlerList getHandlers() {
        return HANDLERS;
    }
}
