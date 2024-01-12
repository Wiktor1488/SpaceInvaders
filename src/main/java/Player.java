import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa reprezentujaca gracza.
 */
public class Player {
    /**
     * Aktualna pozycja gracza.
     */
    public Vector2 position;

    /**
     * Aktualna pozycja pocisku gracza.
     */
    public Vector2 position_bullet;

    /**
     * Sprite reprezentujacy gracza.
     */
    public Sprite sprite;

    /**
     * Sprite reprezentujacy pocisk gracza.
     */
    public Sprite sprite_bullet;

    /**
     * Prędkosc poruszania się gracza.
     */
    public float speed = 300;

    /**
     * Prędkosc poruszania się pocisku gracza.
     */
    public float speed_bullet = 1000;

    /**
     * Konstruktor gracza.
     *
     * @param img         Tekstura gracza
     * @param color       Kolor gracza
     * @param img_bullet  Tekstura pocisku gracza
     */
    public Player(Texture img, Color color, Texture img_bullet) {
        sprite = new Sprite(img);
        sprite_bullet = new Sprite(img_bullet);
        sprite_bullet.setScale(2);
        sprite.setScale(2);
        sprite.setColor(color);

        // Ustaw pozycje startowa gracza
        float initialX = Gdx.graphics.getWidth() / 2;
        float initialY = 10; // Mozesz dostosowac tę wartosc

        position = new Vector2(initialX, initialY);
        position_bullet = new Vector2(0, 10000);
    }

    /**
     * Aktualizuje logike gracza.
     *
     * @param deltaTime  Czas trwania klatki
     */
    public void update(float deltaTime) {
        System.out.println("Player PositionX: " + position.x);
        System.out.println("Bullet PositionY: " + position_bullet.x);

        if (Gdx.input.isButtonJustPressed(0) && position_bullet.y >= Gdx.graphics.getHeight()) {
            position_bullet.x = position.x + 8;
            position_bullet.y = 0;
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            position.x -= deltaTime * speed;
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            position.x += deltaTime * speed;
        }

        float buffer = 5.0f; // Dodac wartosc bufora (mozesz dostosowac wartosc)
        float leftBound = 0 + buffer;
        float rightBound = Gdx.graphics.getWidth() - buffer;
        float halfWidth = sprite.getWidth() * sprite.getScaleX() / 2;

        if (position.x - halfWidth <= leftBound) {
            position.x = leftBound + halfWidth;
        }
        if (position.x + halfWidth >= rightBound) {
            position.x = rightBound - halfWidth;
        }
        position_bullet.y += deltaTime * speed_bullet;
    }

    /**
     * Renderuje gracza i pocisk gracza na ekranie.
     *
     * @param batch  Obiekt do renderowania
     */
    public void draw(SpriteBatch batch) {
        update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        sprite_bullet.setPosition(position_bullet.x, position_bullet.y);
        sprite_bullet.draw(batch);
    }

    /**
     * Resetuje pozycje gracza i pocisku gracza do pozycji startowej.
     */
    public void reset() {
        // Ustaw pozycje startowa gracza
        float initialX = Gdx.graphics.getWidth() / 2;
        float initialY = 10;
        position.set(initialX, initialY);

        // Ustaw pozycję startowa dla pocisku poza widokiem
        position_bullet.set(0, 10000);
    }
}
