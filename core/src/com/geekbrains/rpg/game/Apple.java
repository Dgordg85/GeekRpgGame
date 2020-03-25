package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Apple {
    private TextureRegion texture;
    private Vector2 position;
    private Boolean active;

    public Apple(TextureAtlas atlas) {
        this.texture = atlas.findRegion("apple");
        this.position = new Vector2(0, 0);
        this.active = false;
    }

    public void render(SpriteBatch batch){
        if (active){
            batch.draw(texture, position.x - 128, position.y - 128, 128, 128, 256, 256,0.1f,0.1f, 0);
        }
    }

    public void update(float dt) {
        Random random = new Random();
        if (!active){
            position.x = random.nextInt(1280);
            position.y = random.nextInt(720);
            active = true;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Boolean getActive() {
        return active;
    }

    public void deactivate(){
        active = false;
    }
}
