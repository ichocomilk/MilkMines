package site.ichocomilk.mines.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;

final class Create {

    static void handle(final CommandSender sender, final String[] args, final MineManager manager) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return;
        }
        final Mine mine = manager.getByName(args[0]);
        if (mine != null) {
            sender.sendMessage("The mine " + args[0] + " already exist");
            return;
        }
        manager.getMines().add(new Mine(60, args[0], ((Player)sender).getWorld()));
        sender.sendMessage("Mine " + args[0] + "created!");
    }
}
