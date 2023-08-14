package me.realized.duels.gui.settings.buttons;

import me.realized.duels.DuelsPlugin;
import me.realized.duels.gui.BaseButton;
import me.realized.duels.setting.Settings;
import me.realized.duels.util.compat.Items;
import me.realized.duels.util.inventory.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RequestSendButton extends BaseButton {

    public RequestSendButton(final DuelsPlugin plugin) {
        super(plugin, ItemBuilder.of(Items.GREEN_DYE.clone()).name(plugin.getLang().getMessage("GUI.settings.buttons.send.name")).build());
    }

    @Override
    public void onClick(final Player player) {
        final Settings settings = settingManager.getSafely(player);
        final Player target = Bukkit.getPlayer(settings.getTarget());

        if (settings.getTarget() == null) {
            settings.reset();
            player.closeInventory();
            return;
        }

        if (target == null) {
            settings.reset();
            player.closeInventory();
            lang.sendMessage(player, "ERROR.player.no-longer-online");
            return;
        }

        final String lore = lang.getMessage("GUI.settings.buttons.details.lore",
                "opponent", target.getName(),
                "kit", settings.isOwnInventory() ? "Own Inventory" : settings.getKit() != null ? settings.getKit().getName() : lang.getMessage("GENERAL.not-selected"),
                "own_inventory", settings.isOwnInventory() ? lang.getMessage("GENERAL.enabled") : lang.getMessage("GENERAL.disabled"),
                "arena", settings.getArena() != null ? settings.getArena().getName() : lang.getMessage("GENERAL.random"),
                "item_betting", settings.isItemBetting() ? lang.getMessage("GENERAL.enabled") : lang.getMessage("GENERAL.disabled"),
                "bet_amount", settings.getBet()
        );
        setLore(lore.split("\n"));

        if (!settings.isOwnInventory() && settings.getKit() == null) {
            player.closeInventory();
            lang.sendMessage(player, "ERROR.duel.mode-unselected");
            return;
        }

        player.closeInventory();
        requestManager.send(player, target, settings);
    }
}
