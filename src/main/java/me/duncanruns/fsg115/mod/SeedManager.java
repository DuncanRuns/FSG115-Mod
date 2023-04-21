package me.duncanruns.fsg115.mod;

import me.duncanruns.fsg115.runner.FSG115;
import me.duncanruns.fsg115.runner.FilterResult;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SeedManager {
    private static final AtomicBoolean findingSeed = new AtomicBoolean(false);
    private static final AtomicBoolean seedExists = new AtomicBoolean(false);
    private static FilterResult currentResult;

    private SeedManager() {
    }

    public static boolean canTake() {
        return seedExists.get();
    }

    public static boolean isFindingSeed() {
        return findingSeed.get();
    }

    public static void find() {
        if (findingSeed.get()) {
            return;
        }
        findingSeed.set(true);
        new Thread(() -> {
            FilterResult out;
            try {
                out = FSG115.findSeed(FSG115Mod.getOptions().threads);
            } catch (IOException | NoSuchAlgorithmException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                findingSeed.set(false);
            }
            FSG115Mod.LOGGER.log(Level.INFO, "FSG115Mod: Found seed!");
            currentResult = out;
            seedExists.set(true);
        }, "seed-finder").start();
    }

    public static FilterResult take() {
        seedExists.set(false);
        return currentResult;
    }
}
