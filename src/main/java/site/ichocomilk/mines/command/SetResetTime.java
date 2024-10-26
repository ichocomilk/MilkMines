package site.ichocomilk.mines.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;

final class Reset {

    static void resetMine(final CommandSender sender, final String[] args, final Mine mine, final MineManager manager) {
        if (mine.block == null) {
            sender.sendMessage("The mine block with /mine " + args[0] + " set");
            return;
        }
        if (mine.minPos == null || mine.maxPos == null) {
            sender.sendMessage("The mine position /mine " + args[0] + " pos");
            return;
        }
        sender.sendMessage("Mine " + args[0] + " reset!");
        manager.resetMine(mine, Bukkit.getOnlinePlayers());
    }

    static void setResetTime(final CommandSender sender, final String[] args, final Mine mine) {
        if (args.length != 3) {
            sender.sendMessage("Format: /mine (name) settime 30. Time in seconds");
            return;
        }
        try {
            int time = Integer.parseInt(args[2]);
            if (time < 0) {
                sender.sendMessage("Time can't be negative");
                return;
            }
            mine.resetTime = time;
            mine.countdown = time;
            sender.sendMessage("Reset time set to " + time);
        } catch (Exception e) {
            sender.sendMessage("Time " + args[2] + " is not a positive numeric value");
            return;
        }
    }
}
