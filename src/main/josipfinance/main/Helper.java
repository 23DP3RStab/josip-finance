package josipfinance.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class Helper {

    private static final String RESOURCES_FOLDER = "resources";

    public static BufferedReader getReader(String filename) throws IOException {
        return Files.newBufferedReader(getFilePath(filename));
    }

    public static BufferedWriter getWriter(String filename, StandardOpenOption option) throws IOException {
        return Files.newBufferedWriter(getFilePath(filename), option);
    }

    private static Path getFilePath(String filename) throws IOException {
        File folder = new File(RESOURCES_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    
        File file = new File(folder, filename);
        return file.toPath();
    }
}