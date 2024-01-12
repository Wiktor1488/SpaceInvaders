import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * Klasa DesktopLauncher sluzy do uruchomienia gry na platformie desktopowej
 */
public class DesktopLauncher {
	/**
	 * Metoda glowna main, ktora rozpoczyna uruchamianie gry.
	 *
	 * @param arg Argumenty wiersza polecen (nie są uzywane w tej aplikacji).
	 */
	public static void main(String[] arg) {
		// Konfiguracja aplikacji
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60); // Ustaw maksymalną liczbę klatek na sekundę na 60
		config.setTitle("Space Invaders"); // Ustaw tytuł okna
		config.setWindowedMode(600, 600); // Ustaw rozmiar okna na 600x600 pikseli

		// Utworz nową instancję egry i uruchom ją
		new Lwjgl3Application(new Main(), config);
	}
}
