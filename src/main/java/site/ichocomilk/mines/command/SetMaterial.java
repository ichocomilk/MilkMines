package site.ichocomilk.mines.command;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import site.ichocomilk.mines.data.Mine;

final class SetMaterial {
    
    static void handle(final CommandSender sender, final String[] args, final Mine mine) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return;
        }
        final Player player = (Player)sender;
        final Material material = player.getInventory().getItemInMainHand().getType();
        if (material == null) {
            sender.sendMessage("§cYou need have a item in your hand");
            return;
        }
        mine.block = material;
        sender.sendMessage("§cNow the mine " + mine.name + " contains the block " + material.name());
    }
}
