import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testy jednostkowe dla klasy Player.
 */
public class PlayerTest {

    private Player player;

    /**
     * Przygotowanie atrap obiektow oraz inicjalizacja klasy Player przed kazdym testem.
     */
    @BeforeEach
    public void setUp() {
        openMocks(this); // Inicjalizacja atrap obiektow

        // Symulacja zachowania Gdx.graphics.getWidth() / 2
        GdxMock.init();
        when(Gdx.graphics.getWidth()).thenReturn(800); // Dostosuj szerokosc wedlug potrzeb

        Texture mockTexture = mock(Texture.class);
        Texture mockBulletTexture = mock(Texture.class);
        player = new Player(mockTexture, Color.GREEN, mockBulletTexture);
    }

    /**
     * Testuje resetowanie pozycji gracza.
     */
    @Test
    public void testReset() {
        // Ustaw pozycje gracza i pocisku na inne wartosci
        player.position.set(100, 200);
        player.position_bullet.set(50, 60);

        // Wywolaj reset
        player.reset();

        // Sprawdz, czy pozycje zostaly zresetowane do oczekiwanych wartości
        assertEquals(400, player.position.x); // Oczekiwana szerokość / 2
        assertEquals(10, player.position.y);
        assertEquals(0, player.position_bullet.x);
        assertEquals(10000, player.position_bullet.y);
    }
}
