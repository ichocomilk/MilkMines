package site.ichocomilk.mines.timer;

import java.util.List;

import site.ichocomilk.mines.data.Mine;
import site.ichocomilk.mines.manager.MineManager;

public final class MineResetTimer implements Runnable {

    private final List<Mine> mines;
    private final MineManager manager;

    public MineResetTimer(List<Mine> mines, MineManager manager) {
        this.mines = mines;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (final Mine mine : mines) {
            if (mine.countdown-- != 0) {
                continue;
            }
            manager.resetMine(mine);
            mine.countdown = mine.resetTime;
        }
    }
}