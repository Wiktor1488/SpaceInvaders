/**
 * Klasa zarzadzajaca stanem gry.
 */
public class GameStateManager {
    private static GameState currentState;

    /**
     * Pobiera aktualny stan gry.
     *
     * @return Aktualny stan gry.
     */
    public static GameState getCurrentState() {
        return currentState;
    }

    /**
     * Ustawia nowy stan gry.
     *
     * @param newState Nowy stan gry do ustawienia.
     */
    public static void setCurrentState(GameState newState) {
        currentState = newState;
    }
}
