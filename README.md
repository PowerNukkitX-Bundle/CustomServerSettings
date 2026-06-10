# CustomServerSettings

A PowerNukkitX plugin that sends a fully customizable server settings form to players when they open the **Settings** screen in Minecraft: Bedrock Edition. The form content is defined in the plugin config and can also be modified at runtime by other plugins through a custom event.

## How It Works

When the client opens the settings screen, it sends a `ServerSettingsRequestPacket`. This plugin intercepts that packet and replies with a `ServerSettingsResponsePacket` containing a custom form built from `config.yml`. The form appears as a dedicated tab inside the client's settings menu.

## Features

- Fully config-driven custom settings form (title, icon, elements)
- Supports all custom form element types (`label`, `toggle`, `input`, `dropdown`, `slider`, `step_slider`)
- `ServerSettingsSendEvent` API for developers to modify or cancel the form per player

## Installation

1. Download or build `CustomServerSettings.jar`
2. Drop it into your server's `plugins` folder
3. Restart the server
4. Edit `plugins/CustomServerSettings/config.yml` to customize the form

## Configuration

```yaml
form-id: 0
title: "Custom Server Settings"
icon:
  type: url
  data: "https://i.imgur.com/SCi3ZlL.png"
elements:
  - type: label
    text: "Welcome to the server! Customize your experience below."
  - type: toggle
    text: "Enable notifications"
    default: true
    tag: notifications
  - type: toggle
    text: "Enable particles"
    default: true
    tag: particles
```

| Key | Description |
| --- | --- |
| `form-id` | The form ID used in the response packet |
| `title` | The title shown at the top of the settings tab |
| `icon` | Optional form icon; `type` is `url` or `path`, `data` is the image source |
| `elements` | List of form elements, serialized to the form JSON as-is |

Each entry under `elements` is passed through to the form JSON directly, so any field supported by Bedrock custom forms (`text`, `default`, `options`, `min`, `max`, `step`, `placeholder`, custom tags, etc.) can be used.

## Developer API

Listen to `ServerSettingsSendEvent` to modify or cancel the settings form before it is sent:

```java
@EventHandler
public void onServerSettingsSend(ServerSettingsSendEvent event) {
    Player player = event.getPlayer();

    event.setFormId(0);
    event.setFormData("{\"type\":\"custom_form\",\"title\":\"My Settings\",\"content\":[]}");

    // or prevent the form from being sent entirely
    event.setCancelled();
}
```

| Method | Description |
| --- | --- |
| `getPlayer()` | The player who opened the settings screen |
| `getFormId()` / `setFormId(int)` | The form ID of the response packet |
| `getFormData()` / `setFormData(String)` | The raw form JSON of the response packet |
| `isCancelled()` / `setCancelled()` | Cancel to skip sending the form |

## Building

```bash
./gradlew jar
```

The output jar is located at `build/libs/CustomServerSettings.jar`.

## License

This project is licensed under the [MIT License](LICENSE).
