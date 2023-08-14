package me.realized.duels.gui.settings;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.config.Config;
import me.realized.duels.gui.settings.buttons.*;
import me.realized.duels.util.gui.SinglePageGui;

public class SettingsGui extends SinglePageGui<DuelsPlugin> {

    public SettingsGui(final DuelsPlugin plugin) {
        super(plugin, plugin.getLang().getMessage("GUI.settings.title"), 3);
        final Config config = plugin.getConfiguration();

        set(13, new RequestDetailsButton(plugin));

        if (config.isKitSelectingEnabled()) {
            set(10, new KitSelectButton(plugin));
        }

        if (config.isOwnInventoryEnabled()) {
            set(13, new OwnInventoryButton(plugin));
        }

        if (config.isArenaSelectingEnabled()) {
            set(14, new ArenaSelectButton(plugin));
        }

        set(16, new RequestSendButton(plugin));
//        set(7, new CancelButton(plugin));
    }
}


