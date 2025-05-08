package josipfinance.test;

import josipfinance.main.Helper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {

    @Test
    void testGetWriterAndReader() throws IOException {
        String filename = "testfile.txt";
        String content = "This is a test line.";

        try (BufferedWriter writer = Helper.getWriter(filename, StandardOpenOption.CREATE)) {
            writer.write(content);
        }

        try (BufferedReader reader = Helper.getReader(filename)) {
            String readLine = reader.readLine();
            assertEquals(content, readLine);
        }

        Files.deleteIfExists(Path.of("resources", filename));
    }

    @Test
    void testGetReaderFileNotFound() {
        assertThrows(IOException.class, () -> {
            Helper.getReader("nonexistent_file.txt");
        });
    }
}
