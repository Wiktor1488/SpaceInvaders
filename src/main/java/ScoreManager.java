import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Klasa ScoreManager zarzadza wynikami gry.
 */
public class ScoreManager {
    private int currentTopScore;
    String highScoreFileName;
    /**
     * Konstruktor klasy ScoreManager. Inicjalizuje zarzadzane wyniki gry.
     */
    public ScoreManager() {
        this.highScoreFileName = "highscore.txt";
        loadHighScore();
    }
    /**
     * Wczytuje aktualnie najlepszy wynik z pliku.
     */
    public void loadHighScore() {
        File file = new File("highscore.txt");
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextInt()) {
                currentTopScore = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Zapisuje nowy najlepszy wynik, jeśli jest wyzszy od aktualnego.
     *
     * @param score Nowy wynik do zapisania.
     */
    public void saveTopScore(int score) {
        if (score > currentTopScore) {
            try (FileWriter writer = new FileWriter("highscore.txt")) {
                writer.write(Integer.toString(score));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Pobiera aktualnie najlepszy wynik gry.
     *
     * @return Aktualnie najlepszy wynik.
     */
    public int getCurrentTopScore() {
        return currentTopScore;
    }
}
