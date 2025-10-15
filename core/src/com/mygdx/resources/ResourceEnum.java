package com.mygdx.resources;

public enum ResourceEnum {

    //ENTITIES
    PLAYER_IDLE_DOWN("assets/player/idle_down.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_UP("assets/player/idle_up.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_LEFT("assets/player/idle_left.png", TypeEnum.TEXTURE),
    PLAYER_IDLE_RIGHT("assets/player/idle_right.png", TypeEnum.TEXTURE),
    PLAYER_WALK_DOWN("assets/player/walk_down.png", TypeEnum.TEXTURE),
    PLAYER_WALK_UP("assets/player/walk_up.png", TypeEnum.TEXTURE),
    PLAYER_WALK_LEFT("assets/player/walk_left.png", TypeEnum.TEXTURE),
    PLAYER_WALK_RIGHT("assets/player/walk_right.png", TypeEnum.TEXTURE),


    JERKINS_IDLE_DOWN("assets/npcs/jerkins/jenkins_idle_down.png", TypeEnum.TEXTURE),
    JERKINS_IDLE_UP("assets/npcs/jerkins/jenkins_idle_up.png", TypeEnum.TEXTURE),
    JERKINS_IDLE_LEFT("assets/npcs/jerkins/jenkins_idle_left.png", TypeEnum.TEXTURE, 0.2f, 1),
    JERKINS_IDLE_RIGHT("assets/npcs/jerkins/jenkins_idle_right.png", TypeEnum.TEXTURE),
    JERKINS_WALK_DOWN("assets/npcs/jerkins/jenkins_walk_down.png", TypeEnum.TEXTURE),
    JERKINS_WALK_UP("assets/npcs/jerkins/jenkins_walk_up.png", TypeEnum.TEXTURE),
    JERKINS_WALK_LEFT("assets/npcs/jerkins/jenkins_walk_left.png", TypeEnum.TEXTURE),
    JERKINS_WALK_RIGHT("assets/npcs/jerkins/jenkins_walk_right.png", TypeEnum.TEXTURE),


    BLACKMARKETEER_IDLE_DOWN("assets/npcs/blackmarketeer/blackmarketeer_idle_down.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_UP("assets/npcs/blackmarketeer/blackmarketeer_idle_up.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_LEFT("assets/npcs/blackmarketeer/blackmarketeer_idle_left.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_IDLE_RIGHT("assets/npcs/blackmarketeer/blackmarketeer_idle_right.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_DOWN("assets/npcs/blackmarketeer/blackmarketeer_walk_down.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_UP("assets/npcs/blackmarketeer/blackmarketeer_walk_up.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_LEFT("assets/npcs/blackmarketeer/blackmarketeer_walk_left.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_WALK_RIGHT("assets/npcs/blackmarketeer/blackmarketeer_walk_right.png", TypeEnum.TEXTURE),
    BLACKMARKETEER_SLEEPING("assets/npcs/blackmarketeer/blackmarketeer_sleeping.png", TypeEnum.TEXTURE, 0.01f, 0),


    //PROJECTILES AND WEAPONS
    STONE("assets/bullets/stone.png", TypeEnum.TEXTURE),
    BULLET("assets/bullets/bullet.png", TypeEnum.TEXTURE),
    DEFAULT("assets/weapons/default.png", TypeEnum.TEXTURE),
    CHAINGUN("assets/weapons/sniper.png", TypeEnum.TEXTURE),
    USBLADE("assets/weapons/usblade.png", TypeEnum.TEXTURE),

    //MENU
    ITAFLAG("assets/dialogues/images/itaFlag.png", TypeEnum.TEXTURE),
    ENGFLAG("assets/dialogues/images/engFlag.jpg", TypeEnum.TEXTURE),
    PLAY_BUTTON("assets/menu/play.png", TypeEnum.TEXTURE),
    SETTINGS_BUTTON("assets/menu/settings.png", TypeEnum.TEXTURE),
    QUIT_BUTTON("assets/menu/quit.png", TypeEnum.TEXTURE),
    BACKGROUND_1("assets/menu/background.jpg", TypeEnum.TEXTURE),
    BACKGROUND_2("assets/menu/background-2.jpg", TypeEnum.TEXTURE),

    //hud
    HEALTH_BAR("assets/hud/health.png", TypeEnum.TEXTURE),

    //MAP
    SLUMS("assets/map/slums//slums.tmx", TypeEnum.MAP),
    RICH_DISTRICT("assets/map/rich_disctirct/rich_district.tmx", TypeEnum.MAP),
    PARK("assets/map/park/park.tmx", TypeEnum.MAP),
    REFLECTION_ARENA("assets/map/reflection_arena/reflection_arena.tmx", TypeEnum.MAP),

    //SMALL BUILDINGS
    ABANDONED_TURNED_ON("assets/map/city/buildings/abandoned_turned_on.png", TypeEnum.TEXTURE),
    ABANDONED_TURNED_OFF("assets/map/city/buildings/abandoned_turned_off.png", TypeEnum.TEXTURE),
    SYNTH_TURNED_ON("assets/map/city/buildings/synth_turned_on.png", TypeEnum.TEXTURE),
    SYNTH_TURNED_OFF("assets/map/city/buildings/synth_turned_off.png", TypeEnum.TEXTURE),
    ENERGYPLANT_TURNED_ON("assets/map/city/buildings/energyplant_turned_on.png", TypeEnum.TEXTURE),
    ENERGYPLANT_TURNED_OFF("assets/map/city/buildings/energyplant_turned_off.png", TypeEnum.TEXTURE),
    MARMOT_PIZZA("assets/map/city/buildings/pizza_marmot.png", TypeEnum.TEXTURE),

    //MEDIUM BUILDINGS
    SKYSCRAPER_MEDIUM("assets/map/city/buildings/skyscraper_medium.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_FRONT("assets/map/city/buildings/skyscraper_1_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_BACK("assets/map/city/buildings/skyscraper_1_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_1_SIDE("assets/map/city/buildings/skyscraper_1_side.png", TypeEnum.TEXTURE),

    //BIG BUILDINGS
    ELECTRO("assets/map/city/buildings/electro.png", TypeEnum.TEXTURE),
    SKYSCRAPER_HIGH("assets/map/city/buildings/skyscraper_high.png", TypeEnum.TEXTURE),
    SKYSCRAPER_SMALL("assets/map/city/buildings/skyscraper_small.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2_FRONT("assets/map/city/buildings/skyscraper_2_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_2_BACK("assets/map/city/buildings/skyscraper_2_back.png", TypeEnum.TEXTURE),
    SKYSCRAPER_3_FRONT("assets/map/city/buildings/skyscraper_3_front.png", TypeEnum.TEXTURE),
    SKYSCRAPER_L("assets/map/city/buildings/SKYSCRAPER_L.png", TypeEnum.TEXTURE),
    SKYSCRAPER_U("assets/map/city/buildings/SKYSCRAPER_U.png", TypeEnum.TEXTURE),

    //MAP COMPONENTS
    LAMP("assets/map/city/components/lamp.png", TypeEnum.TEXTURE),
    LONG_LAMP("assets/map/city/components/long_lamp.png", TypeEnum.TEXTURE),

    //OTHERS
    MARKER("assets/player/marker.png", TypeEnum.TEXTURE),
    SCOPE("assets/npcs/scope.png", TypeEnum.TEXTURE),


    //DIALOGUES
    COMPLEX_DIALOGUE("assets/dialogues/images/bossDialogueBox.png", TypeEnum.TEXTURE),
    SIMPLE_DIALOGUE("assets/dialogues/images/dialogueBox.jpg", TypeEnum.TEXTURE),
    CHOICE("assets/dialogues/images/choice.png", TypeEnum.TEXTURE),

    ADEPTUS_1("adeptus1_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_2("adeptus2_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_3("adeptus3_JSON.json", TypeEnum.DIALOGUE),
    ADEPTUS_4("adeptus4_JSON.json", TypeEnum.DIALOGUE),
    
    //MUSIC
    REFLECTION_1("assets/audio/reflection_1.mp3", TypeEnum.AUDIO),
    REFLECTION_2("assets/audio/reflection_2.mp3", TypeEnum.AUDIO),
    REFLECTION_3("assets/audio/reflection_3.mp3", TypeEnum.AUDIO),
    REFLECTION_4("assets/audio/reflection_4.mp3", TypeEnum.AUDIO),
    REFLECTION_5("assets/audio/reflection_5.mp3", TypeEnum.AUDIO),


    //SCRIPTS
    TEST_SCRIPT("assets/scripts/npcs/reflection/test.gcs", TypeEnum.SCRIPT),
    TEST_2("assets/scripts/npcs/reflection/test2.gcs", TypeEnum.SCRIPT),
    TEST_3("assets/scripts/npcs/reflection/test3.gcs", TypeEnum.SCRIPT),
    MEET_1("assets/scripts/npcs/reflection/meet1.gcs", TypeEnum.SCRIPT),
    MEET_2("assets/scripts/npcs/reflection/meet2.gcs", TypeEnum.SCRIPT),



    //EFFECTS
    EXPLOSION("assets/effects/explosion.png", TypeEnum.TEXTURE, 0.05f, 0);

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
