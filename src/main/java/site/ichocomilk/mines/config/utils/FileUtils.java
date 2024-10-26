package site.ichocomilk.mines.config.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import site.ichocomilk.mines.config.ConfigSection;

public final class FileUtils {

    private final File datafolder;
    private final Logger logger;
    private final Yaml yaml;

    public FileUtils(File datafolder, Logger logger, Yaml yaml) {
        this.datafolder = datafolder;
        this.logger = logger;
        this.yaml = yaml;
    }

    public ConfigSection getConfig(final String file) {
        return getConfig(new File(datafolder, file));
    }

    @SuppressWarnings("unchecked")
    public ConfigSection getConfig(final File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return new ConfigSection(yaml.loadAs(reader, Map.class));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error parsing the file " + file, e);
            return null;
        }
    }

    public boolean writeFile(final Map<?, ?> data, final String fileName) {
        try (FileWriter writer = new FileWriter(new File(datafolder, fileName))) {
            yaml.dump(data, writer);
            return true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't write the file " + fileName + ". Error:", e);
            return false;
        }
    }

    public ConfigSection getAndCreateIfAbsent(final String file) {
        if (!datafolder.exists()) {
            if (!datafolder.mkdir()) {
                logger.warning("Can't create the data folder. File " + file);
                return null;
            }
        }
        final File fileOut = new File(datafolder, file);
        if (!fileOut.exists()) {
            write(file, fileOut);
        }
        return getConfig(fileOut);
    }

    public File createFolder(final String folderName) {
        final File folderFile = new File(datafolder, folderName);
        if (!folderFile.exists()) {
            if (!folderFile.mkdirs()) {
                logger.warning("Can't create the data folder. File " + folderName);
                return null;
            }
        }
        return folderFile;
    }

    public void write(final String resourcePath, final File outDestination) {
        final InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) {
            logger.warning("The file " + resourcePath + " don't exist in resources folder");
            return;
        }
        try {
            IOUtils.copy(stream, new FileOutputStream(outDestination));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error copying the file " + resourcePath + " into " + outDestination, e);
        }
    }
}