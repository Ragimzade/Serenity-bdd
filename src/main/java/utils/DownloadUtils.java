package utils;

import base.IBaseEntity;
import org.openqa.selenium.TimeoutException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtils implements IBaseEntity {
    public static final int TIMEOUT_IN_SECONDS = 20;
    public static final int DELAY_IN_MILLIS = 1000;

    public static boolean isFileDownloaded(String filename) {
        Path filePath = getPath(filename);
        try {
            IBaseEntity.getDelay(TIMEOUT_IN_SECONDS, DELAY_IN_MILLIS)
                    .until(() -> filePath.toFile().exists() && filename.endsWith(filename.substring(10)));
            log.info("File " + filePath.toFile().getName() + " is downloaded");
            return true;
        } catch (TimeoutException ex) {
            log.error("File is not downloaded", ex);
            return false;
        }
    }

    private static Path getPath(String filename) {
        return Paths.get(System.getProperty("user.dir"), config.getBrowserDownloadPath(), filename);
    }

    public static void deleteDirectory(String filename) {
        try {
            log.info(String.format("deleting directory '%s' ", getPath(filename)));
            Files.deleteIfExists(getPath(filename));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Something gone wrong, try again");
        }
    }
}