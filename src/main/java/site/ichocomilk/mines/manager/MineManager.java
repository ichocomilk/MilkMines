package site.ichocomilk.mines.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.data.MinesData;
import site.ichocomilk.mines.nms.NMSVersion;

public final class MineManager {

    private final NMSVersion nmsVersion;
    private final MinesData mines;

    public MineManager(MinesData mines, NMSVersion nmsVersion) {
        this.nmsVersion = nmsVersion;
        this.mines = mines;
    }

    public void resetMine(final Mine mine, final Collection<? extends Player> players) {
        if (!mine.canGenBlocks()) {
            return;
        }
        nmsVersion.setBlocks(mine, mine.world);
        if (mine.teleport == null) {
            return;
        }
        final int minX = mine.minPos.x, minY = mine.minPos.y, minZ = mine.minPos.z;
        final int maxX = mine.maxPos.x, maxY = mine.maxPos.y, maxZ = mine.maxPos.z;
        final Location teleportLocation = new Location(mine.world, mine.teleport.x, mine.teleport.y, mine.teleport.z);
        for (final Player player : players) {
            final Location location = player.getLocation();
            final int x = location.getBlockX();
            final int y = location.getBlockX();
            final int z = location.getBlockX();
            if (
                x >= minX && x <= maxX &&
                y >= minY && y <= maxY &&
                z >= minZ && z <= maxZ
            ) {
                player.teleport(teleportLocation);
            }
        }
    }

    public List<String> getMinesNames() {
        final List<String> names = new ArrayList<>(mines.listMines.size());
        for (final Mine mine : mines.listMines) {
            names.add(mine.name);
        }
        return names;
    }

    public Mine getByName(final String name) {
        return mines.byName.get(name);
    }

    public List<Mine> getMinesList() {
        return mines.listMines;
    }
    public MinesData getMines() {
        return mines;
    }
}
