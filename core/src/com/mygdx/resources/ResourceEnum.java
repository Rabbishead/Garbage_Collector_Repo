package com.mygdx.resources;

public enum ResourceEnum {

    //ENTITIES
    PLAYER_IDLE_DOWN("player/idle_down.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_UP("player/idle_up.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_LEFT("player/idle_left.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_RIGHT("player/idle_right.png", TypeEnum.TEXTURE),
    PLAYER_WALK_DOWN("player/walk_down.png", TypeEnum.TEXTURE),
    PLAYER_WALK_UP("player/walk_up.png", TypeEnum.TEXTURE),
    PLAYER_WALK_LEFT("player/walk_left.png", TypeEnum.TEXTURE),
    PLAYER_WALK_RIGHT("player/walk_right.png", TypeEnum.TEXTURE),


    JERKINS_IDLE_DOWN("npcs/jerkins/jenkins_idle_down.png", TypeEnum.TEXTURE),
    JERKINS_IDLE_UP("npcs/jerkins/jenkins_idle_up.png", TypeEnum.TEXTURE),
    JERKINS_IDLE_LEFT("npcs/jerkins/jenkins_idle_left.png", TypeEnum.TEXTURE),
    JERKINS_IDLE_RIGHT("npcs/jerkins/jenkins_idle_right.png", TypeEnum.TEXTURE),
    JERKINS_WALK_DOWN("npcs/jerkins/jenkins_walk_down.png", TypeEnum.TEXTURE),
    JERKINS_WALK_UP("npcs/jerkins/jenkins_walk_up.png", TypeEnum.TEXTURE),
    JERKINS_WALK_LEFT("npcs/jerkins/jenkins_walk_left.png", TypeEnum.TEXTURE),
    JERKINS_WALK_RIGHT("npcs/jerkins/jenkins_walk_right.png", TypeEnum.TEXTURE),


    BLACKMARKETEER_IDLE_DOWN("npcs/blackmarketeer/blackmarketeer_idle_down.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_UP("npcs/blackmarketeer/blackmarketeer_idle_up.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_LEFT("npcs/blackmarketeer/blackmarketeer_idle_left.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_RIGHT("npcs/blackmarketeer/blackmarketeer_idle_right.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_DOWN("npcs/blackmarketeer/blackmarketeer_walk_down.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_UP("npcs/blackmarketeer/blackmarketeer_walk_up.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_LEFT("npcs/blackmarketeer/blackmarketeer_walk_left.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_RIGHT("npcs/blackmarketeer/blackmarketeer_walk_right.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_SLEEPING("npcs/blackmarketeer/blackmarketeer_sleeping.png", TypeEnum.TEXTURE, 0.01f, 1),

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
    ABANDONED_TURNED_ON("map/city/buildings/abandoned_turned_on.png", TypeEnum.TEXTURE),
    ABANDONED_TURNED_OFF("map/city/buildings/abandoned_turned_off.png", TypeEnum.TEXTURE),
    SYNTH_TURNED_ON("map/city/buildings/synth_turned_on.png", TypeEnum.TEXTURE),
    SYNTH_TURNED_OFF("map/city/buildings/synth_turned_off.png", TypeEnum.TEXTURE),
    ENERGYPLANT_TURNED_ON("map/city/buildings/energyplant_turned_on.png", TypeEnum.TEXTURE),
    ENERGYPLANT_TURNED_OFF("map/city/buildings/energyplant_turned_off.png", TypeEnum.TEXTURE),
    MARMOT_PIZZA("map/city/buildings/pizza_marmot.png", TypeEnum.TEXTURE),

    //MEDIUM BUILDINGS
    SKYSCRAPER_MEDIUM("map/city/buildings/skyscraper_medium.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_FRONT("map/city/buildings/skyscraper_1_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_BACK("map/city/buildings/skyscraper_1_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_SIDE("map/city/buildings/skyscraper_1_side.png", TypeEnum.TEXTURE),

    //BIG BUILDINGS
    ELECTRO("map/city/buildings/electro.png", TypeEnum.TEXTURE),
    SKYSCRAPER_HIGH("map/city/buildings/skyscraper_high.png", TypeEnum.TEXTURE),
    SKYSCRAPER_SMALL("map/city/buildings/skyscraper_small.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2_FRONT("map/city/buildings/skyscraper_2_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2_BACK("map/city/buildings/skyscraper_2_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_3_FRONT("map/city/buildings/skyscraper_3_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_L("map/city/buildings/SKYSCRAPER_L.png", TypeEnum.TEXTURE),
    SKYSCRAPER_U("map/city/buildings/SKYSCRAPER_U.png", TypeEnum.TEXTURE),

    //OTHERS
    LAMP("map/city/components/lamp.png", TypeEnum.TEXTURE),
    LONG_LAMP("map/city/components/long_lamp.png", TypeEnum.TEXTURE),
    MARKER("player/marker.png", TypeEnum.TEXTURE),
    SCOPE("assets/npcs/scope.png", TypeEnum.TEXTURE),


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
    TEST_2("assets/scripts/npcs/reflection/test2.gcs", TypeEnum.SCRIPT),
    TEST_3("assets/scripts/npcs/reflection/test3.gcs", TypeEnum.SCRIPT),
    MEET_1("assets/scripts/npcs/reflection/meet1.gcs", TypeEnum.SCRIPT),
    MEET_2("assets/scripts/npcs/reflection/meet2.gcs", TypeEnum.SCRIPT);



    //SOUND EFFECTS

    public String label;
    public TypeEnum type;
    public float animationRate = -1;
    public float delay = -1;

    ResourceEnum(String label, TypeEnum type) {
        this.label = label;
        this.type = type;
        
    }

    ResourceEnum(String label, TypeEnum type, float animationRate, float delay) {
        this(label, type);
        this.animationRate = animationRate;
        this.delay = delay;
        
    }
}
