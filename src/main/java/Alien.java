import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa reprezentujaca obiekt typu Alien.
 */
public class Alien {

    /**
     * Aktualna pozycja obiektu Alien.
     */
    public Vector2 position;

    /**
     * Pozycja poczatkowa obiektu Alien.
     */
    public Vector2 position_initial;

    /**
     * Obiekt Sprite reprezentujacy wyglad obiektu Alien.
     */
    public Sprite sprite;

    /**
     * Flaga okrealajaca, czy obiekt Alien jest zywy.
     */
    public Boolean Alive = true;

    /**
     * Konstruktor klasy Alien.
     *
     * @param _position Aktualna pozycja obiektu Alien.
     * @param img Obrazek tekstury reprezentujacej obiekt Alien.
     */
    public Alien(Vector2 _position, Texture img) {
        position = _position;
        position_initial = position;
        sprite = new Sprite(img);
        sprite.setScale(2);
    }

    /**
     * Metoda sluzaca do rysowania obiektu Alien na danym SpriteBatch.
     *
     * @param batch SpriteBatch, na ktorym ma zostać narysowany obiekt Alien.
     */
    public void Draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}
