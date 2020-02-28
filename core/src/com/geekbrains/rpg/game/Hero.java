package com.geekbrains.rpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Hero {
    private Texture texture;
    private Vector2 position;
    private float speed;

    public Hero() {
        this.texture = new Texture("hero.png");
        this.position = new Vector2(100,100);
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x - 32, position.y - 32,32,32,64,64, 1, 1,0,0,0,64,64,false,false);
    }

    public void update(float dt, Vector2 pointerPosition){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            position.x -= speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            position.y -= speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            position.x += speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            position.y += speed * dt;
        }

        if (Math.abs(position.x - pointerPosition.x) > 2) {
            if (position.x < pointerPosition.x)
                position.x += speed * dt;
            else if (position.x > pointerPosition.x)
                position.x -= speed * dt;
        }

        if (Math.abs(position.y - pointerPosition.y) > 2) {
            if (position.y < pointerPosition.y)
                position.y += speed * dt;
            else if (position.y > pointerPosition.y)
                position.y -= speed * dt;
        }
    }
}
