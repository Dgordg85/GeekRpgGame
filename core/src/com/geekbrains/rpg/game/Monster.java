package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Monster {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float attackTime;
    private float speed;
    private int hp;
    private final int HP_MAX = 30;
    private final float ATTACK_TIME = 0.5f;

    public Vector2 getPosition() {
        return position;
    }

    public boolean takeDamage(int amount){
        boolean result = false;
        if ((hp -= amount) <= 0)
            result = true;
        return result;
    }

    public Monster(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.position = new Vector2(800,300);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0,0);
        this.speed = 100.0f;
        this.hp = HP_MAX;
    }

    public void render(SpriteBatch batch){
        batch.setColor(1, 0, 0, 1);
        batch.draw(texture, position.x - 30, position.y - 30,30,30,60,60, 1, 1,0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / HP_MAX), 8);
    }

    public void update(float dt){
        Hero hero = gameScreen.getHero();
        lifetime += dt;
        tmp.set(hero.getPosition()).sub(position).nor().scl(speed);
        if (hero.getPosition().dst(position) > 35){
            position.mulAdd(tmp, dt);
            attackTime = 0;
        }
        else
            attackTime += dt;

        if (attackTime > ATTACK_TIME){
            hero.takeDamage(-1);
            attackTime = 0;
        }
    }

    public void setNewPos(){
        hp = HP_MAX;
        position.x = new Random().nextInt(1280);
        position.y = new Random().nextInt(720);
    }
}

