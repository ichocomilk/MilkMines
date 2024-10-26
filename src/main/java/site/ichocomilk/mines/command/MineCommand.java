package site.ichocomilk.mines.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import site.ichocomilk.mines.MinesPlugin;
import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;

public final class MineCommand implements TabExecutor {

    private final MinesPlugin mines;
    private final MineManager manager;
    private final String version;

    public MineCommand(MinesPlugin mines, MineManager manager) {
        this.mines = mines;
        this.version = mines.getDescription().getVersion();
        this.manager = manager;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 2) {
            return List.of("create", "delete", "set", "settime", "tp", "settp", "pos", "info", "reset");
        }
        if (args.length == 1) {
            return manager.getMinesNames();
        }
        return List.of();
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(format());
            return true;
        }
        if (args.length < 2) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                mines.reload();
                sender.sendMessage("Plugin reloaded!");
                return true;
            }
            sender.sendMessage(format());
            return true;
        }

        switch (args[1]) {
            case "create":
                Create.handle(sender, args, manager);
                return true;
            case "delete":
                Delete.handle(sender, args, manager);
                return true;
        }

        final Mine mine = manager.getByName(args[0]);
        if (mine == null) {
            sender.sendMessage("The mine " + args[0] + " don't exist. Available mines: " + manager.getMinesNames());
            return true;
        }
        switch (args[1].toLowerCase()) {
            case "set":
                SetMaterial.handle(sender, args, mine);
                break;
            case "settime":
                Reset.setResetTime(sender, args, mine);
                break;
            case "tp":
                Pos.handleTeleport(sender, args, mine);
                break;
            case "settp":
                Pos.handleSetTeleport(sender, args, mine);
                break;
            case "pos":
                Pos.handlePos(sender, args, mine);
                break;
            case "info":
                Info.handle(sender, mine);
                break;
            case "reset":
                Reset.resetMine(sender, args, mine, manager);
                break;
            default:
                sender.sendMessage(format());
        }
        return true;
    }

    private String format() {
        return 
            "\n " +
            "\n §f§lMilk§b§lMines §7v" + version + " §8- by iChocoMilk" +
            "\n " +
            "\n §e/mines §6reload" +
            "\n §e/mines §7(mineName) -> " +
            "\n     §7set §8- Set mine item" +
            "\n     §7delete §8- Delete mine" +
            "\n     §7create §8- Create mine" +
            "\n     §7settime (time) §8- Reset time" +
            "\n     §7info §8- See mine info" +
            "\n     §7tp §8 - Teleport to mine" +
            "\n     §7settp §8 - Set Teleport" +
            "\n     §7pos §8- Set mine position" +
            "\n ";
    }
}