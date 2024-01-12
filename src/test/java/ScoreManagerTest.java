import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy ScoreManager.
 */
public class ScoreManagerTest {
    private ScoreManager scoreManager;
    private File tempFile;

    /**
     * Przygotowuje srodowisko testowe przed kazdym testem.
     *
     * @param tempDir Katalog tymczasowy dostarczany przez JUnit.
     * @throws IOException Jesli wystąpi problem z plikiem tymczasowym.
     */
    @BeforeEach
    void setUp(@TempDir Path tempDir) throws IOException {
        tempFile = tempDir.resolve("highscoretest.txt").toFile();
        tempFile.createNewFile();
        scoreManager = new ScoreManager();
    }

    /**
     * Testuje zachowanie metody saveTopScore w przypadku nizszego wyniku.
     *
     * @throws IOException Jesli wystapi problem z operacjami na pliku.
     */
    @Test
    void testSaveTopScoreLowerScore() throws IOException {
        int initialScore = 200;
        int newScore = 50;

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(Integer.toString(initialScore));
        }

        scoreManager.loadHighScore();
        scoreManager.saveTopScore(newScore);

        try (Scanner scanner = new Scanner(tempFile)) {
            int savedScore = scanner.nextInt();
            assertEquals(initialScore, savedScore); // Powinien pozostać stary wynik
        }
    }

    /**
     * Testuje zachowanie metody saveTopScore w przypadku rownego wyniku.
     *
     * @throws IOException Jesli wystąpi problem z operacjami na pliku.
     */
    @Test
    void testSaveTopScoreEqualScore() throws IOException {
        int initialScore = 100;
        int newScore = 100;

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(Integer.toString(initialScore));
        }

        scoreManager.loadHighScore();
        scoreManager.saveTopScore(newScore);

        try (Scanner scanner = new Scanner(tempFile)) {
            int savedScore = scanner.nextInt();
            assertEquals(initialScore, savedScore); // Powinien pozostać stary wynik
        }
    }
}
