import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Klasa zawiera testy jednostkowe dla klasy GameStateManager.
 */
public class GameStateManagerTest {
    /**
     * Metoda wywolywana przed kazdym testem, aby przywrocic stan poczatkowy.
     */
    @BeforeEach
    void setUp() {
        // Przywracamy stan poczatkowy przed kazdym testem
        GameStateManager.setCurrentState(null);
    }

    /**
     * Testuje metode getCurrentState() klasy GameStateManager.
     */
    @Test
    void testGetCurrentState() {
        // Sprawdzamy, czy poczatkowy stan jest null
        assertEquals(null, GameStateManager.getCurrentState());

        // Ustawiamy stan
        GameState newState = GameState.PLAYING;
        GameStateManager.setCurrentState(newState);

        // Sprawdzamy, czy mozemy pobrac ustawiony stan
        assertEquals(newState, GameStateManager.getCurrentState());
    }

    /**
     * Testuje metodę setCurrentState() klasy GameStateManager.
     */
    @Test
    void testSetCurrentState() {
        // Ustawiamy stan
        GameState newState = GameState.GAME_OVER;
        GameStateManager.setCurrentState(newState);

        // Sprawdzamy, czy ustawiony stan jest poprawny
        assertEquals(newState, GameStateManager.getCurrentState());
    }
}
