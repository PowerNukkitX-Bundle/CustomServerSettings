package org.powernukkitx.customserversettings.form;

import cn.nukkit.utils.Config;
import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public final class SettingsFormFactory {

    private static final Gson GSON = new Gson();

    private SettingsFormFactory() {
    }

    public static SettingsForm create(Config config) {
        var root = new LinkedHashMap<String, Object>();
        root.put("type", "custom_form");
        root.put("title", config.getString("title", "Server Settings"));
        root.put("content", config.getMapList("elements"));

        var icon = config.getSection("icon");
        if (icon != null && !icon.isEmpty()) {
            var iconData = Map.of(
                    "type", icon.getString("type", "url"),
                    "data", icon.getString("data", "")
            );
            root.put("icon", iconData);
            root.put("icon_outline", iconData);
        }

        return new SettingsForm(config.getInt("form-id", 5000), GSON.toJson(root));
    }
}
