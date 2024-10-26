package site.ichocomilk.mines.nms.v1_21;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_21_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_21_R1.block.CraftBlockType;

import net.minecraft.world.level.block.state.IBlockData;
import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.nms.NMSVersion;

public final class NMSWrapper1_21 implements NMSVersion {
    
    public void setBlocks(final Mine mine, final World bukkitworld) {
        final IBlockData materialData = ((CraftBlockType<?>)(mine.block).asBlockType()).getHandle().o();

        final int minX = mine.minPos.x, minY = mine.minPos.y, minZ = mine.minPos.z;
        final int maxX = mine.maxPos.x, maxY = mine.maxPos.y, maxZ = mine.maxPos.z;

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (int z = minZ; z < maxZ; z++) {
                    // TODO: Add cached chunk and avoid getBlockAt in every block...
                    final Block block = bukkitworld.getBlockAt(x,y,z);

                    final CraftBlock craftBlock = ((CraftBlock)block);

                    final IBlockData old = craftBlock.getNMS();
                    if (CraftBlockType.minecraftToBukkit(old.b()) == Material.AIR) {
                        craftBlock.getHandle().a(craftBlock.getPosition(), materialData, 1042);
                    }
                }
            }
        }
    }
}
