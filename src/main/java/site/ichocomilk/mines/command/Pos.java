package site.ichocomilk.mines.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.data.MinePosition;

public final class Pos {

    static void handlePos(final CommandSender sender, final String[] args, final Mine mine) {
        if (args.length == 3 && args[2].equalsIgnoreCase("reset")) {
            mine.minPos = null;
            mine.maxPos = null;
            sender.sendMessage("Min and max positions reseted!!");
            return;
        }
        calculateMinMaxPos(mine, sender);
        sender.sendMessage(
            "\n Pos Min: " + mine.minPos.toString() +
            "\n Pos max: " + mine.maxPos.toString() 
        );
    }

    static void handleTeleport(final CommandSender sender, final String[] args, final Mine mine) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return;
        }
        final Player player = (Player)sender;
        if (mine.teleport == null || mine.teleport.isZero()) {
            sender.sendMessage("The mine " + mine.name + " don't have teleport location");
            return;
        }
        player.teleport(new Location(mine.world, mine.teleport.x, mine.teleport.y, mine.teleport.z));
        sender.sendMessage("Teleported to " + mine.name);
    }

    static void handleSetTeleport(final CommandSender sender, final String[] args, final Mine mine) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return;
        }
        final Player player = (Player)sender;
        final Location location = player.getLocation();
        mine.teleport = new MinePosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        sender.sendMessage("Teleport position set!");
    }

    private static void calculateMinMaxPos(final Mine mine, final CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return;
        }
        final Player player = (Player)sender;
        final Location location = player.getLocation();
        final int x = location.getBlockX();
        final int z = location.getBlockZ();
        final int y = location.getBlockY();

        MinePosition min = getOrDefault(mine.minPos, location);
        MinePosition max = getOrDefault(mine.maxPos, location);
    
        mine.minPos = new MinePosition(Math.min(min.x, x), Math.min(min.y, y), Math.min(min.z, z));
        mine.maxPos = new MinePosition(Math.max(max.x, x), Math.max(max.y, y), Math.max(max.z, z));
    }

    private static final MinePosition getOrDefault(final MinePosition position, final Location toCompare) {
        return (position == null) ? new MinePosition(toCompare.getBlockX(), toCompare.getBlockY(), toCompare.getBlockZ()) : position;
    }
}
