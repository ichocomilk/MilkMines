package site.ichocomilk.mines.data;

import org.bukkit.Material;
import org.bukkit.World;

public final class Mine {

    public Material block;
    public MinePosition minPos, maxPos, teleport;

    public final World world;
    public int resetTime;
    public final String name;
    public int countdown;

    public Mine(int resetTime, String name, World world) {
        this.resetTime = resetTime;
        this.countdown = resetTime;
        this.name = name;
        this.world = world;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Mine) && ((Mine)obj).name.equals(this.name));
    }
    
    public boolean canGenBlocks() {
        return world != null && minPos != null && maxPos != null && block != null;
    }
}
