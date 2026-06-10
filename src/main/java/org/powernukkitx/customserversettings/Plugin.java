package org.powernukkitx.customserversettings;

import cn.nukkit.plugin.PluginBase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.powernukkitx.customserversettings.form.SettingsForm;
import org.powernukkitx.customserversettings.form.SettingsFormFactory;
import org.powernukkitx.customserversettings.listener.PacketReceiveListener;

public class Plugin extends PluginBase {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private static Plugin plugin;

    @Getter
    private SettingsForm settingsForm;

    @Override
    public void onLoad() {
        setPlugin(this);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        settingsForm = SettingsFormFactory.create(getConfig());
        getServer().getPluginManager().registerEvents(new PacketReceiveListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
