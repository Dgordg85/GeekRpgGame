package com.geekbrains.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private TextureRegion texturePointer;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float speed;
    private int hp;
    private final int HP_MAX = 10;
    private int coins;
    private StringBuilder strBuilder;

    public Hero(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.texturePointer = Assets.getInstance().getAtlas().findRegion("pointer");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.position = new Vector2(100,100);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0,0);
        this.speed = 300.0f;
        this.hp = 10;
        this.coins = 0;
        this.strBuilder = new StringBuilder();
    }

    public void render(SpriteBatch batch){
        batch.draw(texturePointer, dst.x - 32, dst.y - 32, 32,32, 64, 64, 0.5f, 0.5f, lifetime * 90);
        batch.draw(texture, position.x - 30, position.y - 30,30,30,60,60, 1, 1,0);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / HP_MAX), 8);
    }

    public void renderGUI(SpriteBatch batch, BitmapFont font){
        strBuilder.setLength(0);
        strBuilder.append("Class: ").append("Knight").append("\n");
        strBuilder.append("HP: ").append(hp).append(" / ").append(HP_MAX).append("\n");
        strBuilder.append("Coins: ").append(coins).append("\n");
        font.draw(batch, strBuilder, 10, 710);
    }

    public void update(float dt){
        lifetime += dt;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            dst.set(Gdx.input.getX(), 720 - Gdx.input.getY());

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
            gameScreen.getProjectilesController().setup(position.x, position.y, Gdx.input.getX(), 720.0f - Gdx.input.getY());

        tmp.set(dst).sub(position).nor().scl(speed);
        if (position.dst(dst) > speed * dt)
            position.mulAdd(tmp, dt);
        else
            position.set(dst);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void increaseCoins(int amount){
        coins += amount;
    }

    public void takeDamage(int amount){
        hp += amount;
        if (hp < 0 || hp > HP_MAX)
            hp = HP_MAX;
    }
}
