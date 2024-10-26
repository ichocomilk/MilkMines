package site.ichocomilk.mines.command;

import org.bukkit.command.CommandSender;

import site.ichocomilk.mines.data.Mine;

final class Info {

    static void handle(final CommandSender sender, final Mine mine) {
        sender.sendMessage(
            "\n " +
            "\n §fName: §a" + mine.name +
            "\n §fBlock: §e" + (mine.block == null ? "None" : mine.block.name()) +
            "\n §fMin: §b" + (mine.minPos == null ? "None" : mine.minPos.toString()) +
            "\n §fMax: §3" + (mine.maxPos == null ? "None" : mine.maxPos.toString()) +
            "\n §fReset: §b" + mine.countdown + "§7/§3" + mine.resetTime +
            "\n §fTeleport: §9" + (mine.teleport == null ? "None" : mine.teleport.toString())
        );
    }
}
