package site.ichocomilk.mines.config.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import site.ichocomilk.mines.config.ConfigSection;

public final class MessagesConfigLoader {
    
    @SuppressWarnings("all")
    public void load(final ConfigSection section) {
        if (section == null) {
            return;
        }
        final Set<Entry<String, Object>> entries = section.values().entrySet();        
        final Map<String, String> messages = new HashMap<>(entries.size());

        for (final Entry<String, Object> entry : entries) {
            if (entry.getValue() instanceof Map) {
                loadSection(entry.getKey(), messages, ((Map)entry.getValue()).entrySet());
                continue;
            }
            messages.put(entry.getKey(), MessageColor.toString(entry.getValue()));
        }
        Messages.setInstance(new Messages(messages));
    }

    @SuppressWarnings("all")
    private void loadSection(final String key, final Map<String, String> messages, final Set<Entry<Object,Object>> entries) {
        for (final Entry<Object,Object> entry : entries) {
            if (!(entry instanceof Map)) {
                messages.put(key + '.' + entry.getKey(), MessageColor.toString(entry.getValue()));
                continue;
            }
            loadSection(key + '.' + entry.getKey(), messages, ((Map)entry).entrySet());    
        }
    }
}
