package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.ForegroundMapComponent;
import com.mygdx.entities.Player;
import com.mygdx.entities.npcs.ComplexNPC;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;

public class CityScreen extends PlayableScreen {

    private ForegroundMapComponent lamp1 = new ForegroundMapComponent(new Vector2(200, 1338));

    private ComplexNPC reflection = new ComplexNPC.ComplexNPCBuilder()
        .coordinates(new Vector2(100, 1200))
        .texture(ResourceEnum.PLAYER)
        .build();
    

    public CityScreen(){
        super("CITY_SCREEN");
        stage.addActor(lamp1);
        stage.addActor(reflection);

        stage.getCamera().translate(player.getX(),player.getY(), 0);
    }

    @Override
    public void show() {
        super.show();
        player.setMovementStyle(Player.Styles.REALTIME);
        tileSetManager = new TileSetManager("map/city/cityMap.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get("background"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
