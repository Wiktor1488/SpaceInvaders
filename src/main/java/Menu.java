import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa reprezentujaca menu gry.
 */
public class Menu {
    private SpriteBatch batch;  // Obiekt do renderowania menu
    private float buttonWidth = 200;  // Szerokosc przycisków menu
    private float buttonHeight = 60;  // Wysokosc przyciskow menu
    private BitmapFont font;  // Czcionka do wyswietlania tekstu
    private Texture playButtonTexture;  // Tekstura przycisku "Play"
    private Texture exitButtonTexture;  // Tekstura przycisku "Exit"
    private ScoreManager scoreManager;  // Manager wynikow

    /**
     * Konstruktor menu.
     *
     * @param batch         Obiekt do renderowania menu
     * @param scoreManager  Manager wyników
     */
    public Menu(SpriteBatch batch, ScoreManager scoreManager) {
        this.batch = batch;
        this.scoreManager = scoreManager;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        playButtonTexture = new Texture("play.png");
        exitButtonTexture = new Texture("exit.png");
    }

    /**
     * Obsluguje interakcje uzytkownika w menu.
     */
    public void handleMenuInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= (Gdx.graphics.getWidth() - buttonWidth) / 2 &&
                    mouseX <= (Gdx.graphics.getWidth() - buttonWidth) / 2 + buttonWidth &&
                    mouseY >= Gdx.graphics.getHeight() / 2 &&
                    mouseY <= Gdx.graphics.getHeight() / 2 + buttonHeight) {
                // Przejscie do stanu PLAYING
                GameStateManager.setCurrentState(GameState.PLAYING);
            }

            if (mouseX >= (Gdx.graphics.getWidth() - buttonWidth) / 2 &&
                    mouseX <= (Gdx.graphics.getWidth() - buttonWidth) / 2 + buttonWidth &&
                    mouseY >= Gdx.graphics.getHeight() / 2 - buttonHeight - 20 &&
                    mouseY <= Gdx.graphics.getHeight() / 2 - 20) {
                // Wyjscie z gry
                Gdx.app.exit();
            }
        }
    }

    /**
     * Renderuje menu na ekranie.
     */
    public void renderMenu() {
        float menuX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        float menuY = Gdx.graphics.getHeight() / 2;

        // Wyswietlenie przycisku "Play"
        batch.draw(playButtonTexture, menuX, menuY, buttonWidth, buttonHeight);

        // Wyswietlenie przycisku "Exit"
        batch.draw(exitButtonTexture, menuX, menuY - buttonHeight - 20, buttonWidth, buttonHeight);

        // Wczytanie najlepszego wyniku
        scoreManager.loadHighScore();

        // Wyswietlenie najlepszego wyniku na ekranie
        font.draw(batch, "Top Score: " + scoreManager.getCurrentTopScore(), 20, Gdx.graphics.getHeight() - 20);
    }
}
