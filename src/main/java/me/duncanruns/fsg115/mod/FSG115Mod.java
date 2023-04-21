package me.duncanruns.fsg115.mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.duncanruns.fsg115.mod.util.FileUtil;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FSG115Mod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("fsg115-mod");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path TOKEN_PATH = Paths.get("oceantoken.txt");
    private static final Path CONFIG_FOLDER_PATH = Paths.get("config");
    private static final Path OPTIONS_PATH = CONFIG_FOLDER_PATH.resolve("fsg115mod.txt");

    private static FSG115ModOptions options;

    public static String lastToken = null;

    public static FSG115ModOptions getOptions() {
        return options;
    }

    public static String getLastToken() {
        return lastToken;
    }

    public static void setLastToken(String lastToken) {
        FSG115Mod.lastToken = lastToken;
        try {
            FileUtil.writeString(TOKEN_PATH, lastToken);
        } catch (IOException ignored) {
        }
    }

    public static void saveOptions() {
        try {
            if (!Files.isDirectory(CONFIG_FOLDER_PATH)) {
                Files.createDirectory(CONFIG_FOLDER_PATH);
            }
            String toSave = GSON.toJson(options);
            FileUtil.writeString(OPTIONS_PATH, toSave);
        } catch (IOException e) {
            LOGGER.warn("FSG115Mod: Failed to save mod options file: " + e);
        }
    }

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "FSG115Mod: Initializing");
        try {
            lastToken = FileUtil.readString(TOKEN_PATH);
        } catch (IOException ignored) {
        }

        if (Files.exists(OPTIONS_PATH)) {
            try {
                options = GSON.fromJson(FileUtil.readString(OPTIONS_PATH), FSG115ModOptions.class);
            } catch (IOException e) {
                LOGGER.warn("FSG115Mod: Failed to load mod options: " + e);
                options = new FSG115ModOptions();
            }
        } else {
            options = new FSG115ModOptions();
            saveOptions();
        }
    }

    public static class FSG115ModOptions {
        public int threads = 4;
        public boolean runInBG = true;
    }
}