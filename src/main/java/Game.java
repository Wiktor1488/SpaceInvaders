import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Klasa reprezentujaca logike i renderowanie gry.
 */
public class Game {
    private Player player;
    private Alien[] aliens;
    private int score;
    private int NumWidth_aliens = 10; // ilosc kosmitow w wierszu
    private int NumHeight_aliens = 4; // ilosc kosmitow w kolumnie
    private int spacing_aliens = 40; // odstep pomiedzy kosmitami
    private int minX_aliens;
    private int minY_aliens;
    private int maxX_aliens;
    private int maxY_aliens;
    private int amount_alive_aliens;
    private int direction_aliens = 1;
    private float speed_aliens = 100;
    private Vector2 offset_aliens;
    Texture img_bullet;
    Texture img_alien;
    private SpriteBatch batch;
    BitmapFont font;

    /**
     * Konstruktor klasy Game.
     *
     * @param batch SpriteBatch, na którym będzie renderowana gra.
     */
    public Game(SpriteBatch batch) {
        this.batch = batch;
        offset_aliens = new Vector2();
        img_bullet = new Texture("bullet.png");
        img_alien = new Texture("enemy.png");
        player = new Player(new Texture("hero.png"), Color.GREEN, img_bullet);
        aliens = new Alien[NumWidth_aliens * NumHeight_aliens];
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        initializeAliens();
    }

    private void initializeAliens() {
        int i = 0;
        for (int y = 0; y < NumHeight_aliens; y++) {
            for (int x = 0; x < NumWidth_aliens; x++) {
                Vector2 position = new Vector2(x * spacing_aliens, y * spacing_aliens);
                position.x += Gdx.graphics.getWidth() / 2;
                position.y += Gdx.graphics.getHeight();
                position.x -= (NumWidth_aliens / 2) * spacing_aliens;
                position.y -= (NumHeight_aliens) * spacing_aliens;
                aliens[i] = new Alien(position, img_alien);
                i++;
            }
        }
    }

    /**
     * Resetuje stan gry.
     */
    public void resetGame() {
        score = 0;
        player.reset();
        resetAliens();
    }

    private void resetAliens() {
        for (int i = 0; i < aliens.length; i++) {
            Vector2 position = new Vector2(i % NumWidth_aliens * spacing_aliens, i / NumWidth_aliens * spacing_aliens);
            position.x += Gdx.graphics.getWidth() / 2;
            position.y += Gdx.graphics.getHeight();
            position.x -= (NumWidth_aliens / 2) * spacing_aliens;
            position.y -= (NumHeight_aliens) * spacing_aliens;
            aliens[i].position_initial.set(position);
            aliens[i].position.set(position);
            aliens[i].Alive = true;
        }
    }

    /**
     * Pobiera aktualny wynik gry.
     *
     * @return Aktualny wynik gry.
     */
    public int getScore() {
        return score;
    }

    /**
     * Renderuje stan gry.
     *
     * @param deltaTime Czas delta między klatkami gry.
     */
    public void renderGame(float deltaTime) {
        player.draw(batch);

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i].Alive) {
                if (player.sprite_bullet.getBoundingRectangle().overlaps(aliens[i].sprite.getBoundingRectangle())) {
                    player.position_bullet.y = 10000;
                    aliens[i].Alive = false;
                    score += 10;
                    break;
                }
            }
        }

        minX_aliens = 10000;
        minY_aliens = 10000;
        maxX_aliens = 0;
        maxY_aliens = 0;
        amount_alive_aliens = 0;

        for (int i = 0; i < aliens.length; i++) {
            if (aliens[i].Alive) {
                int IndexX = i % NumWidth_aliens;
                int IndexY = i / NumWidth_aliens;
                if (IndexX > maxX_aliens) maxX_aliens = IndexX;
                if (IndexX < minX_aliens) minX_aliens = IndexX;
                if (IndexY > maxY_aliens) maxY_aliens = IndexY;
                if (IndexY < minY_aliens) minY_aliens = IndexY;
                amount_alive_aliens++;
            }
        }

        if (amount_alive_aliens == 0) {
            speed_aliens += 20;

            for (int i = 0; i < aliens.length; i++) {
                aliens[i].Alive = true;
            }

            for (int y = 0; y < NumHeight_aliens; y++) {
                for (int x = 0; x < NumWidth_aliens; x++) {
                    Vector2 position = new Vector2(x * spacing_aliens, y * spacing_aliens);
                    position.x += Gdx.graphics.getWidth() / 2;
                    position.y += Gdx.graphics.getHeight();
                    position.x -= (NumWidth_aliens / 2) * spacing_aliens;
                    position.y -= (NumHeight_aliens) * spacing_aliens;
                    aliens[y * NumWidth_aliens + x].position_initial.set(position);
                }
            }

            offset_aliens = new Vector2(0, 0);
            speed_aliens = 100;
            return;
        }

        offset_aliens.x += direction_aliens * deltaTime * speed_aliens;

        if (aliens[maxX_aliens].position.x >= Gdx.graphics.getWidth()) {
            direction_aliens = -1;
            offset_aliens.y -= aliens[0].sprite.getHeight() * aliens[0].sprite.getScaleY() * 0.25f;
        }

        if (aliens[minX_aliens].position.x <= 0) {
            direction_aliens = 1;
            offset_aliens.y -= aliens[0].sprite.getHeight() * aliens[0].sprite.getScaleY() * 0.25f;
        }

        if (aliens[minY_aliens].position.y <= 0) {
            offset_aliens = new Vector2(0, 0);
            speed_aliens = 100;
            resetAliens();
            GameStateManager.setCurrentState(GameState.GAME_OVER);
        }

        for (int i = 0; i < aliens.length; i++) {
            aliens[i].position = new Vector2(aliens[i].position_initial.x + offset_aliens.x, aliens[i].position_initial.y + offset_aliens.y);
            if (aliens[i].Alive) {
                aliens[i].Draw(batch);
                if (aliens[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                    offset_aliens = new Vector2(0, 0);
                    speed_aliens = 100;
                    resetAliens();
                    GameStateManager.setCurrentState(GameState.GAME_OVER);
                }
            }
        }
        font.draw(batch, "Score: " + score, 20, Gdx.graphics.getHeight() - 20);
    }

    /**
     * Zwolnij zasoby gry.
     */
    public void dispose() {
        img_bullet.dispose();
        img_alien.dispose();
        font.dispose();
    }
}
