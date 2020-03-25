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
    private Projectile projectile;
    private TextureRegion texture;
    private TextureRegion texturePointer;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float speed;
    private int hp;
    private int hpMax;
    private int countApples;
    private StringBuilder strBuilder;

    public Hero(TextureAtlas atlas) {
        this.texture = atlas.findRegion("knight");
        this.texturePointer = atlas.findRegion("pointer");
        this.textureHp = atlas.findRegion("hp");
        this.position = new Vector2(100,100);
        this.projectile = new Projectile(atlas);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0,0);
        this.speed = 300.0f;
        this.hpMax = 10;
        this.hp = 5;
        this.countApples = 0;
        this.strBuilder = new StringBuilder();
    }

    public void render(SpriteBatch batch){
        batch.draw(texturePointer, dst.x - 32, dst.y - 32, 32,32, 64, 64, 0.5f, 0.5f, lifetime * 90);
        batch.draw(texture, position.x - 30, position.y - 30,30,30,60,60, 1, 1,0);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 8);
        projectile.render(batch);
    }

    public void renderGUI(SpriteBatch batch, BitmapFont font){
        strBuilder.setLength(0);
        strBuilder.append("Class: ").append("Knight").append("\n");
        strBuilder.append("HP: ").append(hp).append(" / ").append(hpMax).append("\n");
        strBuilder.append("Count apples: ").append(countApples).append("\n");
        font.draw(batch, strBuilder, 10, 710);
        //font.draw(batch, "X", position.x, position.y);

    }

    public void update(float dt, Apple apple){
        projectile.update(dt, apple, this);
        lifetime += dt;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            dst.set(Gdx.input.getX(), 720 - Gdx.input.getY());

        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT))
            projectile.setup(position.x, position.y, Gdx.input.getX(), 720.0f - Gdx.input.getY());

        tmp.set(dst).sub(position).nor().scl(speed);
        if (position.dst(dst) > speed * dt)
            position.mulAdd(tmp, dt);
        else
            position.set(dst);
    }

    public void hitApple() {
        this.countApples += 1;
    }
}
