package site.ichocomilk.mines.data;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public final class MinesData {
    public final List<Mine> listMines = new ArrayList<>();
    public final Object2ObjectOpenHashMap<String, Mine> byName = new Object2ObjectOpenHashMap<>();

    public void addMine(final Mine mine) {
        listMines.add(mine);
        byName.put(mine.name, mine);
    }

    public void removeMine(final Mine mine) {
        listMines.remove(mine);
        byName.remove(mine.name);
    }
}
