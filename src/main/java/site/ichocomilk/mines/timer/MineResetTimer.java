package site.ichocomilk.mines.timer;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (final Mine mine : mines) {
            if (mine.countdown-- != 0) {
                continue;
            }
            manager.resetMine(mine, players);
            mine.countdown = mine.resetTime;
        }
    }
}