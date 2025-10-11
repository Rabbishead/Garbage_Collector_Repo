package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;

public enum ForegroundMapComponentEnum {

    // SMALL BUILDINGS
    ABANDONED(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.ABANDONED_TURNED_ON)
                    .width(3)
                    .animationRate(0.5f)
                    .fade()),

    SYNTH(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SYNTH_TURNED_ON, ResourceEnum.SYNTH_TURNED_OFF)
                    .width(3)
                    .animationRate(0.5f)
                    .delay(0)
                    .startingAnimation(ResourceEnum.SYNTH_TURNED_ON)
                    .fade()),

    MARMOT_PIZZA(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.MARMOT_PIZZA)
                    .width(3)
                    .animationRate(0.2f)
                    .delay(1000)
                    .fade()),

    ENERGYPLANT(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.ENERGYPLANT_TURNED_ON, ResourceEnum.ENERGYPLANT_TURNED_OFF)
                    .width(3)
                    .animationRate(0.5f)
                    .startingAnimation(ResourceEnum.ENERGYPLANT_TURNED_ON)
                    .fade()),

    // MEDIUM BUILDNGS
    SKYSCRAPER_MEDIUM(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_MEDIUM)
                    .width(3)
                    .fade()),

    SKYSCRAPER_1_FRONT(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_FRONT)
                    .width(5)
                    .fade()),

    SKYSCRAPER_1_BACK(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_BACK)
                    .width(5)
                    .fade()),
    SKYSCRAPER_1_SIDE(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_SIDE)
                    .width(4)
                    .fade()),

    // BIG BUILDINGS
    SKYSCRAPER_2_FRONT(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2_FRONT)
                    .width(5)
                    .fade()),
    SKYSCRAPER_2_BACK(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2_BACK)
                    .width(5)
                    .fade()),
    SKYSCRAPER_3_FRONT(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_3_FRONT)
                    .width(5)
                    .fade()),
    SKYSCRAPER_L(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_L)
                    .width(5)
                    .fade()),
    SKYSCRAPER_U(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_U)
                    .width(5)
                    .fade()),
    // TODO
    // SPARK_BUILDING_1
    // SPARK_BUILDING_2
    // SKYSCRAPER_HIGH
    // SKYSCRAPER_SMALL

    // OTHERS
    LAMP(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.LAMP)
                    .width(1)
                    .animationRate(1f)
                    .delay(1000)),
    LONG_LAMP(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.LONG_LAMP)
                    .width(2));

    private Vector2 coord;
    private ForegroundMapComponent.ForegroundMapComponentBuilder builder;

    ForegroundMapComponentEnum(ForegroundMapComponent.ForegroundMapComponentBuilder builder) {
        this.builder = builder;
    }

    // SETUP things different from the template
    public ForegroundMapComponent build() {
        builder.coordinates(coord);

        return new ForegroundMapComponent(builder);
    }

    public ForegroundMapComponentEnum coord(Vector2 coord) {
        this.coord = coord;
        return this;
    }

    public ForegroundMapComponentEnum coord(float x, float y) {
        this.coord = new Vector2(x, y);
        return this;
    }

    public Vector2 getCoord() {
        return coord;
    }
}
