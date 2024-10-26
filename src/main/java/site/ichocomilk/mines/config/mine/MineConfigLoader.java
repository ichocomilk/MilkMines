package site.ichocomilk.mines.config.mine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Set;

import site.ichocomilk.mines.config.ConfigSection;
import site.ichocomilk.mines.config.utils.FileUtils;
import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.data.MinePosition;

public final class MineConfigLoader {

    private List<Mine> mines;

    public void load(final ConfigSection config, final Logger logger) {
        if (config == null || config.values() == null) {
            this.mines = new ArrayList<>();
            return;
        }
        final Set<Entry<String, Object>> entries = config.values().entrySet();
        final List<Mine> newMines = new ArrayList<>(entries.size());

        for (final Entry<String, Object> entry : entries) {
            if (!(entry.getValue() instanceof Map)) {
                continue;
            }
            @SuppressWarnings("unchecked")
            final ConfigSection mineSection = new ConfigSection((Map<String, Object>)entry.getValue());
            final String worldName = mineSection.getString("world");
            final World world;
            if (worldName == null || (world = Bukkit.getWorld(worldName)) == null) {
                logger.warning("Can't found the world " + worldName);
                continue;
            }

            final Mine mine = new Mine(mineSection.getInt("resetTime"), entry.getKey(), world);
            mine.block = Material.getMaterial(mineSection.getString("block"));
            mine.minPos = new MinePosition(
                mineSection.getInt("minX"),
                mineSection.getInt("minY"),
                mineSection.getInt("minZ")
            );
            mine.maxPos = new MinePosition(
                mineSection.getInt("maxX"),
                mineSection.getInt("maxY"),
                mineSection.getInt("maxZ")
            );

            newMines.add(mine);
        }
        this.mines = newMines;
    }

    public void save(final FileUtils fileUtils) {
        final Map<String, Map<String, Object>> minesData = new HashMap<>();

        for (final Mine mine : mines) {
            final Map<String, Object> data = new HashMap<>(8);
            if (mine.block == null || mine.minPos == null || mine.maxPos == null) {
                continue;
            }
            data.put("block", mine.block.name());

            data.put("minX", mine.minPos.x);
            data.put("minY", mine.minPos.y);
            data.put("minZ", mine.minPos.z);
    
            data.put("maxX", mine.maxPos.x);
            data.put("maxY", mine.maxPos.y);
            data.put("maxZ", mine.maxPos.z);
    
            data.put("resetTime", mine.resetTime);
            data.put("world", mine.world.getName());
        
            minesData.put(mine.name, data);
        }

        fileUtils.writeFile(minesData, "mines.yml");
    }

    public List<Mine> getMines() {
        return mines;
    }
}
