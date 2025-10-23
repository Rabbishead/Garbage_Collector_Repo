package com.mygdx.entities.mapComponents;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Data;
import com.mygdx.resources.ResourceEnum;

public enum MapComponentEnum {

    // SMALL BUILDINGS
    ABANDONED(
            new MapComponentBuilder()
                    .texture(ResourceEnum.ABANDONED_TURNED_ON)
                    .size(Data.TILE * 3, Data.TILE * 2)),

    SYNTH(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SYNTH_TURNED_ON, ResourceEnum.SYNTH_TURNED_OFF)
                    .size(Data.TILE * 3, Data.TILE * 2)
                    .animationRate(0.5f)
                    .startingAnimation(ResourceEnum.SYNTH_TURNED_ON)),

    MARMOT_PIZZA(
            new MapComponentBuilder()
                    .texture(ResourceEnum.MARMOT_PIZZA)
                    .size(Data.TILE * 3, Data.TILE * 2)
                    .animationRate(0.2f)),

    ENERGYPLANT(
            new MapComponentBuilder()
                    .texture(ResourceEnum.ENERGYPLANT_TURNED_ON,
                            ResourceEnum.ENERGYPLANT_TURNED_OFF)
                    .size(Data.TILE * 3, Data.TILE * 2)
                    .animationRate(0.5f)
                    .startingAnimation(ResourceEnum.ENERGYPLANT_TURNED_ON)),

    // MEDIUM BUILDNGS
    SKYSCRAPER_MEDIUM(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_MEDIUM)
                    .size(Data.TILE * 3, Data.TILE * 2)),

    SKYSCRAPER_1_FRONT(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_FRONT)
                    .size(Data.TILE * 5, Data.TILE * 2)),

    SKYSCRAPER_1_BACK(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_BACK)
                    .size(Data.TILE * 5, Data.TILE * 2)),

    SKYSCRAPER_1_SIDE(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_SIDE)
                    .size(Data.TILE * 4, Data.TILE * 2)),

    // BIG BUILDINGS
    SKYSCRAPER_2_FRONT(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2_FRONT)
                    .size(Data.TILE * 5, Data.TILE * 2)),
    SKYSCRAPER_2_BACK(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2_BACK)
                    .size(Data.TILE * 5, Data.TILE * 2)),
    SKYSCRAPER_3_FRONT(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_3_FRONT)
                    .size(Data.TILE * 5, Data.TILE * 2)),
    SKYSCRAPER_L(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_L)
                    .size(Data.TILE * 5, Data.TILE * 2)),
    SKYSCRAPER_U(
            new MapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_U)
                    .size(Data.TILE * 5, Data.TILE * 2)),
    // TODO
    // SPARK_BUILDING_1
    // SPARK_BUILDING_2
    // SKYSCRAPER_HIGH
    // SKYSCRAPER_SMALL

    // OTHERS
    LAMP(
            new MapComponentBuilder()
                    .texture(ResourceEnum.LAMP)
                    .atlas(ResourceEnum.COMPONENTS)
                    .height(Data.TILE * 2)
                    .animationRate(1f)
                    .delay(1000)),

    LONG_LAMP(
            new MapComponentBuilder()
                    .texture(ResourceEnum.LONG_LAMP)
                    .atlas(ResourceEnum.COMPONENTS)
                    .size(Data.TILE * 2, Data.TILE * 2));

    private Vector2 coord;
    private MapComponentBuilder builder;

    MapComponentEnum(MapComponentBuilder builder) {
        this.builder = builder;
    }

    // SETUP things different from the template
    public MapComponent build() {
        builder.coordinates(coord);

        return new MapComponent(builder);
    }

    public MapComponentEnum coord(Vector2 coord) {
        this.coord = coord;
        return this;
    }

    public MapComponentEnum coord(float x, float y) {
        this.coord = new Vector2(x, y);
        return this;
    }

    public Vector2 getCoord() {
        return coord;
    }
}
