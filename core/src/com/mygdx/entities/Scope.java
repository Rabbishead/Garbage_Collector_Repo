package com.mygdx.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.controllers.hitboxes.Hitbox;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.stage.GCStage;

public class Scope extends GameActor{
    private Texture texture;
    private Hitbox hitbox = new Hitbox();


    public Scope(Vector2 coords){
        this.texture = RM.get().getTexture(ResourceEnum.SCOPE);
        setCoords(coords);
        setSize(texture.getWidth(), texture.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);


        hitbox = new Hitbox(center, getWidth(), getHeight(), 0, false);
        hitbox.setTags(Tags.PLAYER);
        hitbox.setOnHit((collider) -> {
            System.out.println("Colpito!");
            System.out.println(collider.getExtraInfo().getIntegerInfo("damage"));
        });
        hitbox.register();

        System.out.println(center);

        debug();
    }
    public Scope(float x, float y){
        this(new Vector2(x, y));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, center.x, center.y);
    }

    @Override
    public void act(float delta) {
        addAction(Actions.moveTo(GCStage.get().getPlayer().center.x, GCStage.get().getPlayer().center.y, 2));

        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }
}
