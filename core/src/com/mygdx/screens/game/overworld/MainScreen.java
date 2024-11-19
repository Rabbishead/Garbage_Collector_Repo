package com.mygdx.screens.game.overworld;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.entities.NPC;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.map.TileSetManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.playable.PlayableScreen;


public class MainScreen extends PlayableScreen {
    NPC testNPC1 = new NPC.NPCBuilder()
        .coordinates(new Vector2(160,160))
        .texture(ResourceEnum.PLAYER)
        .path(new String[]{"DWW--ASS", "WWDSS-A"})
        .build();

    NPC testNPC2 = new NPC.NPCBuilder()
        .coordinates(new Vector2(200,300))
        .texture(ResourceEnum.PLAYER)
        .path(new String[]{"DW-WASS", "W-WDS-SA"})
        .build();
        
    BossDialogue bossDialogue;

    public MainScreen(){
        super("MAIN_SCREEN");
        stage.addActor(testNPC1);
        stage.addActor(testNPC2);

        stage.getCamera().translate(player.getX(),player.getY(), 0);
        player.setMovementStyle(Player.Styles.REALTIME);
    }

    @Override
    public void show() {
        super.show();
        tileSetManager = new TileSetManager("map/map.tmx");
        TileMapCollisionsManager.layer = ((TiledMapTileLayer) tileSetManager.getMap().getLayers().get(0));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        /*if(Gdx.input.isKeyPressed(Keys.Y)){
            stopGame();
        }
        if(Gdx.input.isKeyPressed(Keys.U)){
            resumeGame();
            if(bossDialogue != null){
                bossDialogue.remove();
                bossDialogue = null;
            } 
        }
        if(stopGame){
            
            if(bossDialogue == null){
                bossDialogue = new BossDialogue("Ciao");
                stage.addActor(bossDialogue);
            } 
            tileSetManager.render((OrthographicCamera) stage.getCamera());
            stage.draw();
            hud.draw();
            return;
        }*/
    }
}
