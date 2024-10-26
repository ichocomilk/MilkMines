package site.ichocomilk.mines.data;

public final class MinePosition {
    public final int x, y, z;

    public MinePosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0 && this.z == 0; 
    }
}
