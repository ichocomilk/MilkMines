package site.ichocomilk.mines;

import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import site.ichocomilk.mines.command.MineCommand;
import site.ichocomilk.mines.config.ConfigManager;
import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;
import site.ichocomilk.mines.nms.NMSVersion;
import site.ichocomilk.mines.nms.none.NMSWrapperNone;
import site.ichocomilk.mines.nms.v1_21.NMSWrapper1_21;
import site.ichocomilk.mines.timer.MineResetTimer;

public final class MinesPlugin extends JavaPlugin {

    private final ConfigManager configManager = new ConfigManager(getLogger(), getDataFolder());

    @Override
    public void onEnable() {
        configManager.load();

        final List<Mine> mines = configManager.getMineFiles().getMines();
        final MineManager mineManager = new MineManager(mines, getNmsVersion());

        registerCommand("mines", new MineCommand(this, mineManager));

        getServer().getScheduler().runTaskTimer(this, new MineResetTimer(mines, mineManager), 20, 20);
    }

    @Override
    public void onDisable() {
        configManager.save();
    }

    public void reload() {
        configManager.save();
        configManager.load();
    }

    private void registerCommand(final String name, final CommandExecutor executor) {
        final PluginCommand command = getCommand(name);
        if (command == null) {
            getLogger().warning("No se ha encontrado el comando " + name + " en el plugin.yml.");
            return;
        }
        command.setExecutor(executor);
        if (executor instanceof TabExecutor) {
            command.setTabCompleter((TabExecutor)executor);
        }
    }

    private NMSVersion getNmsVersion(){
        final String version = getServer().getClass().getPackage().getName();
        final String nms = version.substring(version.lastIndexOf('.') + 1);

        if (nms.equalsIgnoreCase("v1_21_R1")) {
            getLogger().info("Using NMS Hook 1.21");
            return new NMSWrapper1_21();
        }

        getLogger().info("Can't found any supported version...");
        return new NMSWrapperNone();
    }
}