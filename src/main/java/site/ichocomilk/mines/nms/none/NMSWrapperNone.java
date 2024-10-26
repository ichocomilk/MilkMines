package site.ichocomilk.mines.nms.none;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.nms.NMSVersion;

public final class NMSWrapperNone implements NMSVersion {

    @Override
    public void setBlocks(final Mine mine, final World bukkitWorld) {

        final int minX = mine.minPos.x, minY = mine.minPos.y, minZ = mine.minPos.z;
        final int maxX = mine.maxPos.x, maxY = mine.maxPos.y, maxZ = mine.maxPos.z;
        final Material blockMaterial = mine.block;

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    final Block block = bukkitWorld.getBlockAt(x,y,z);
                    if (block.getType() == Material.AIR) {
                        block.setType(blockMaterial);
                    }
                }
            }
        }
    }
}
