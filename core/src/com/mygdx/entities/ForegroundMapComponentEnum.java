package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;

public enum ForegroundMapComponentEnum {

    // SMALL BUILDINGS
    ABANDONED_BUILDING(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.ABANDONED_BUILDING)
                    .singlePieceWidth(3)
                    .singlePieceHeight(2)
                    .animationRate(0.5f)
                    .startingAnimationCode(1)
                    .fade()),

    SYNTH(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SYNTH)
                    .singlePieceWidth(3)
                    .singlePieceHeight(2)
                    .animationRate(0.5f)
                    .startingAnimationCode(1)
                    .fade()),

    MARMOT_PIZZA(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.MARMOT_PIZZA)
                    .singlePieceWidth(3)
                    .singlePieceHeight(3)
                    .animationRate(0.2f)
                    .delay(1000)
                    .fade()),

    ENERGYPLANT_BUILDING(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.ENERGYPLANT_BUILDING)
                    .singlePieceWidth(3)
                    .singlePieceHeight(2)
                    .animationRate(0.5f)
                    .startingAnimationCode(1)
                    .fade()),

    // MEDIUM BUILDNGS
    SKYSCRAPER_MEDIUM(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_MEDIUM)
                    .singlePieceWidth(3)
                    .singlePieceHeight(7)
                    .fade()),

    SKYSCRAPER_1(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1)
                    .singlePieceWidth(5)
                    .singlePieceHeight(4)
                    .fade()),

    SKYSCRAPER_1_BACK(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_BACK)
                    .singlePieceWidth(5)
                    .singlePieceHeight(4)
                    .fade()),
    SKYSCRAPER_1_SIDE(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_1_SIDE)
                    .singlePieceWidth(4)
                    .singlePieceHeight(4)
                    .fade()),

    // BIG BUILDINGS
    SKYSCRAPER_2(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2)
                    .singlePieceWidth(5)
                    .singlePieceHeight(7)
                    .fade()),
    SKYSCRAPER_2_BACK(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_2_BACK)
                    .singlePieceWidth(5)
                    .singlePieceHeight(7)
                    .fade()),
    SKYSCRAPER_3(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_3)
                    .singlePieceWidth(5)
                    .singlePieceHeight(7)
                    .fade()),
    SKYSCRAPER_L(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_L)
                    .singlePieceWidth(5)
                    .singlePieceHeight(7)
                    .fade()),
    SKYSCRAPER_U(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_U)
                    .singlePieceWidth(5)
                    .singlePieceHeight(7)
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
                    .singlePieceWidth(1)
                    .singlePieceHeight(2)
                    .animationRate(1f)
                    .delay(1000)),
    LONG_LAMP(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.LONG_LAMP)
                    .singlePieceWidth(2)
                    .singlePieceHeight(2));

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
