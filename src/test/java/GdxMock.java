import com.badlogic.gdx.Gdx;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Klasa GdxMock sluzy do tworzenia atrap obiektow Gdx w celu testowania.
 */
public class GdxMock {

    /**
     * Inicjalizuje atrapy obiektow Gdx.
     */
    public static void init() {
        // Inicjalizacja atrap obiektow Gdx
        Gdx.graphics = mock(com.badlogic.gdx.Graphics.class);
    }

    /**
     * Atrapa metody getWidth() obiektu Gdx.graphics.
     * @param width Szerokosc, ktora zostanie zwrocona przez atrape.
     */
    public static void mockGraphicsWidth(int width) {
        when(Gdx.graphics.getWidth()).thenReturn(width);
    }

}
