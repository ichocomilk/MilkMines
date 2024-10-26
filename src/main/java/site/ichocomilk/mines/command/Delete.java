package site.ichocomilk.mines.command;

import org.bukkit.command.CommandSender;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;

final class Delete {

    static void handle(final CommandSender sender, final String[] args, final MineManager manager) {
        final Mine mine = manager.getByName(args[0]);
        if (mine == null) {
            sender.sendMessage("The mine " + args[0] + " don't exist");
            return;
        }
        manager.getMines().remove(mine);
        sender.sendMessage("Mine " + args[0] + " removed!");
    }
}
