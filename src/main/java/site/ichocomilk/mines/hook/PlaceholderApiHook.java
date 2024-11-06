package site.ichocomilk.mines.hook;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import site.ichocomilk.mines.data.Mine;

public final class PlaceholderApiHook extends PlaceholderExpansion {

    private final Object2ObjectMap<String, Mine> byName;

    public PlaceholderApiHook(Object2ObjectMap<String, Mine> byName) {
        this.byName = byName;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        final Mine mine = byName.get(params);
        return (mine == null) ? null : String.valueOf(mine.countdown);
    }

    @Override
    public @NotNull String getAuthor() {
        return "iChocoMilk";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "milkmines";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
}
