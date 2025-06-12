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
    CHAINGUN("weapons/sniper.png", TypeEnum.TEXTURE),
    USBLADE("weapons/usblade.png", TypeEnum.TEXTURE),

    //MENU
    ITAFLAG("dialogues/images/itaFlag.png", TypeEnum.TEXTURE),
    ENGFLAG("dialogues/images/engFlag.jpg", TypeEnum.TEXTURE),
    PLAY_BUTTON("menu/play.png", TypeEnum.TEXTURE),
    SETTINGS_BUTTON("menu/settings.png", TypeEnum.TEXTURE),
    QUIT_BUTTON("menu/quit.png", TypeEnum.TEXTURE),
    BACKGROUND_1("menu/background.jpg", TypeEnum.TEXTURE),
    BACKGROUND_2("menu/background-2.jpg", TypeEnum.TEXTURE),

    //HUD
    HEALTH_BAR("hud/health.png", TypeEnum.TEXTURE),

    //MAP
    SLUMS("map/slums//slums.tmx", TypeEnum.MAP),
    RICH_DISTRICT("map/rich_disctirct/rich_district.tmx", TypeEnum.MAP),
    PARK("map/park/park.tmx", TypeEnum.MAP),
    REFLECTION_ARENA("map/reflection_arena/reflection_arena.tmx", TypeEnum.MAP),

    //SMALL BUILDINGS
    ABANDONED_BUILDING("map/city/building_abandoned_tileset.png", TypeEnum.TEXTURE),
    SYNTH("map/city/building_synth_animated.png", TypeEnum.TEXTURE),
    MARMOT_PIZZA("map/city/pizza_marmot.png", TypeEnum.TEXTURE),
    ENERGYPLANT_BUILDING("map/city/building_energyplant_animated.png", TypeEnum.TEXTURE),

    //MEDIUM BUILDINGS
    SKYSCRAPER_MEDIUM("map/city/building_skyscraper_medium.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1("map/city/skyscraper_1.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_BACK("map/city/skyscraper_1_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_SIDE("map/city/skyscraper_1_side.png", TypeEnum.TEXTURE),

    //BIG BUILDINGS
    SPARK_BUILDING_1("map/city/building_skyscraper_electro_spark_animation_flow.png", TypeEnum.TEXTURE),
    SPARK_BUILDING_2("map/city/building_skyscraper_electro_spark_animation_turning_on.png", TypeEnum.TEXTURE),
    SKYSCRAPER_HIGH("map/city/building_skyscraper_high.png", TypeEnum.TEXTURE),
    SKYSCRAPER_SMALL("map/city/building_skyscraper_small.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2("map/city/skyscraper_2.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2_BACK("map/city/skyscraper_2_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_3("map/city/skyscraper_3.png", TypeEnum.TEXTURE),
    SKYSCRAPER_L("map/city/SKYSCRAPER_L.png", TypeEnum.TEXTURE),
    SKYSCRAPER_U("map/city/SKYSCRAPER_U.png", TypeEnum.TEXTURE),

    //OTHERS
    LAMP("map/city/lamp.png", TypeEnum.TEXTURE),
    LONG_LAMP("map/city/long_lamp.png", TypeEnum.TEXTURE),
    MARKER("player/marker.png", TypeEnum.TEXTURE),


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
    REFLECTION_5("audio/reflection_5.mp3", TypeEnum.AUDIO),


    //SCRIPTS
    TEST_SCRIPT("assets/scripts/npcs/reflection/test.gcs", TypeEnum.SCRIPT),

    //ANIMATIONS
    TEST_ANIMATION("", TypeEnum.TEXTURE);



    //SOUND EFFECTS

    public String label;
    public TypeEnum type;

    ResourceEnum(String label, TypeEnum type) {
        this.label = label;
        this.type = type;
        
    }
}
