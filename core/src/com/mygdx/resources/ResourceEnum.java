package com.mygdx.resources;

public enum ResourceEnum {

    //ENTITIES
    PLAYER("player/playerSpritesheet.png", TypeEnum.TEXTURE),
    TESTACTOR("menu/play.png", TypeEnum.TEXTURE),
    JERKINS("npcs/jenkins_spritesheet.png", TypeEnum.TEXTURE),
    BLACKMARKETEER("npcs/blackmarketeer_spritesheet.png", TypeEnum.TEXTURE),

    //PROJECTILES AND WEAPONS
    STONE("bullets/stone.png", TypeEnum.TEXTURE),
    BULLET("bullets/bullet.png", TypeEnum.TEXTURE),
    DEFAULT("weapons/default.png", TypeEnum.TEXTURE),
    SNIPER("weapons/sniper.png", TypeEnum.TEXTURE),

    //MENU
    ITAFLAG("dialogues/images/itaFlag.png", TypeEnum.TEXTURE),
    ENGFLAG("dialogues/images/engFlag.jpg", TypeEnum.TEXTURE),
    PLAYBUTTON("menu/play.png", TypeEnum.TEXTURE),

    //HUD
    HEALTH_BAR("hud/health.png", TypeEnum.TEXTURE),

    //MAP
    LAMP("map/city/lamp.png", TypeEnum.TEXTURE),
    LONG_LAMP("map/city/long_lamp.png", TypeEnum.TEXTURE),
    PALACE("map/city/palace.png", TypeEnum.TEXTURE),
    ABANDONED_BUILDING("map/city/building_abandoned_tileset.png", TypeEnum.TEXTURE),
    ENERGYPLANT_BUILDING("map/city/building_energyplant_animated.png", TypeEnum.TEXTURE),
    SPARK_BUILDING_1("map/city/building_skyscraper_electro_spark_animation_flow.png", TypeEnum.TEXTURE),
    SPARK_BUILDING_2("map/city/building_skyscraper_electro_spark_animation_turning_on.png", TypeEnum.TEXTURE),
    SKYSCRAPER("map/city/building_skyscraper_high.png", TypeEnum.TEXTURE),

    //DIALOGUES
    COMPLEX_DIALOGUE("dialogues/images/bossDialogueBox.png", TypeEnum.TEXTURE),
    SIMPLE_DIALOGUE("dialogues/images/dialogueBox.jpg", TypeEnum.TEXTURE),
    CHOICE("dialogues/images/choice.png", TypeEnum.TEXTURE),
    ADEPTUS_1("adeptus1_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_2("adeptus2_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_3("adeptus3_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_4("adeptus4_JSON.json", TypeEnum.DIALOGUE),

    
    //MUSIC
    REFLECTION_1("audio/reflection_1.mp3", TypeEnum.AUDIO),
    REFLECTION_2("audio/reflection_2.mp3", TypeEnum.AUDIO),
    REFLECTION_3("audio/reflection_3.mp3", TypeEnum.AUDIO),
    REFLECTION_4("audio/reflection_4.mp3", TypeEnum.AUDIO),
    REFLECTION_5("audio/reflection_5.mp3", TypeEnum.AUDIO);

    //SOUND EFFECTS

    public String label;
    public TypeEnum type;

    ResourceEnum(String label, TypeEnum type) {
        this.label = label;
        this.type = type;
        
    }
}
