import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Glowna klasa aplikacji odpowiedzialna za zarzadzanie stanem gry i renderowanie.
 */
public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture playButtonTexture;
    Texture exitButtonTexture;
    Texture backgroundTexture;
    Game game;
    Menu menu;
    MenuGameOver menuGameOver;
    ScoreManager scoreManager;
    private BitmapFont font;

    /**
     * Metoda inicjalizujaca gre i jej komponenty.
     */
    @Override
    public void create() {
        GameStateManager.setCurrentState(GameState.MENU);  // Ustawienie poczatkowego stanu gry na MENU
        batch = new SpriteBatch();
        playButtonTexture = new Texture("play.png");
        exitButtonTexture = new Texture("exit.png");
        backgroundTexture = new Texture("background.jpg");
        game = new Game(batch);
        scoreManager = new ScoreManager();
        font = new BitmapFont();
        menu = new Menu(batch, scoreManager);  // Inicjalizacja menu
        menuGameOver = new MenuGameOver(batch, scoreManager);  // Inicjalizacja ekranu Game Over
    }

    /**
     * Metoda renderujaca cala gre i odpowiedzialna za zmiany stanow gry.
     */
    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0, 0, 0, 1);  // Wyczyszczenie ekranu na kolor czarny

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        switch (GameStateManager.getCurrentState()) {
            case MENU:
                menu.handleMenuInput();  // Obsluga interakcji w menu
                menu.renderMenu();  // Renderowanie menu
                break;
            case PLAYING:
                game.renderGame(deltaTime);  // Renderowanie gry
                break;
            case GAME_OVER:
                menuGameOver.handleGameOverInput();  // Obsluga interakcji na ekranie Game Over
                menuGameOver.renderGameOver();  // Renderowanie ekranu Game Over
                scoreManager.saveTopScore(game.getScore());  // Zapisanie wyniku gracza
                game.resetGame();  // Zresetowanie stanu gry
                break;
        }

        batch.end();
    }

    /**
     * Metoda zwalniajaca zasoby uzywane w grze.
     */
    @Override
    public void dispose() {
        batch.dispose();
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
        game.dispose();
    }
}
