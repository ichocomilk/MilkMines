package site.ichocomilk.mines.config;

import java.io.File;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import site.ichocomilk.mines.config.mine.MineConfigLoader;
import site.ichocomilk.mines.config.utils.FileUtils;

public final class ConfigManager {

    private final Logger logger;
    private final File datafolder;

    private final MineConfigLoader mineFiles = new MineConfigLoader();

    public ConfigManager(Logger logger, File datafolder) {
        this.logger = logger;
        this.datafolder = datafolder;
    }

    public void load() {
        final Yaml yaml = new Yaml();
        final FileUtils fileUtils = new FileUtils(datafolder, logger, yaml);

        //new MessagesConfigLoader().load(fileUtils.getAndCreateIfAbsent("messages.yml"));

        mineFiles.load(fileUtils.getAndCreateIfAbsent("mines.yml"), logger);
    }

    public void save() {
        final Yaml yaml = new Yaml();
        final FileUtils fileUtils = new FileUtils(datafolder, logger, yaml);
        mineFiles.save(fileUtils);
    }

    public MineConfigLoader getMineFiles() {
        return mineFiles;
    }
}
