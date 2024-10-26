package site.ichocomilk.mines.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.nms.NMSVersion;

public final class MineManager {

    private final NMSVersion nmsVersion;
    private final List<Mine> mines;

    public MineManager(List<Mine> mines, NMSVersion nmsVersion) {
        this.nmsVersion = nmsVersion;
        this.mines = mines;
    }

    public void resetMine(final Mine mine) {
        if (!mine.canGenBlocks()) {
            Bukkit.broadcastMessage("CAn't reset . " + mine.name);
            return;
        }
        nmsVersion.setBlocks(mine, mine.world);
    }

    public List<String> getMinesNames() {
        final List<String> names = new ArrayList<>(mines.size());
        for (final Mine mine : mines) {
            names.add(mine.name);
        }
        return names;
    }
    public Mine getByName(final String name) {
        for (final Mine mine : mines) {
            if (mine.name.equalsIgnoreCase(name)) {
                return mine;
            }
        }
        return null;
    }
    public List<Mine> getMines() {
        return mines;
    }
}
