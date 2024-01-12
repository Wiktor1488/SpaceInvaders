import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Testy jednostkowe dla klasy Main.
 */
public class MainTest {
    private Main main;

    /**
     * Inicjalizuje atrapy obiektow oraz klase Main przed kazdym testem.
     */
    @BeforeEach
    public void setUp() {
        main = new Main();
        main.batch = Mockito.mock(SpriteBatch.class);
        main.playButtonTexture = Mockito.mock(Texture.class);
        main.exitButtonTexture = Mockito.mock(Texture.class);
        main.backgroundTexture = Mockito.mock(Texture.class);
        main.game = Mockito.mock(Game.class);
        main.menu = Mockito.mock(Menu.class);
        main.menuGameOver = Mockito.mock(MenuGameOver.class);
        main.scoreManager = Mockito.mock(ScoreManager.class);
    }

    /**
     * Testuje poprawnosc inicjalizacji obiektow klasy Main oraz zwiazanych klas.
     */
    @Test
    public void testCreate() {
        assertNotNull(main);
        assertNotNull(main.batch);
        assertNotNull(main.playButtonTexture);
        assertNotNull(main.exitButtonTexture);
        assertNotNull(main.backgroundTexture);
        assertNotNull(main.game);
        assertNotNull(main.menu);
        assertNotNull(main.menuGameOver);
        assertNotNull(main.scoreManager);
    }
}
