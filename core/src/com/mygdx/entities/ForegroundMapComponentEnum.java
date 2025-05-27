package com.mygdx.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.resources.ResourceEnum;

public enum ForegroundMapComponentEnum {
    ABANDONED_BUILDING(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.ABANDONED_BUILDING)
                    .singlePieceWidth(3)
                    .singlePieceHeight(2)
                    .animationRate(0.5f)
                    .delay(0)
                    .startingAnimationCode(1)),

    SYNTH(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SYNTH)
                    .singlePieceWidth(3)
                    .singlePieceHeight(2)
                    .animationRate(0.5f)
                    .delay(0)
                    .startingAnimationCode(1)),

    SKYSCRAPER_MEDIUM(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.SKYSCRAPER_MEDIUM)
                    .singlePieceWidth(3)
                    .singlePieceHeight(4)
                    .animationRate(0.2f)
                    .delay(1000)),

    MARMOT_PIZZA(
            new ForegroundMapComponent.ForegroundMapComponentBuilder()
                    .texture(ResourceEnum.MARMOT_PIZZA)
                    .singlePieceWidth(3)
                    .singlePieceHeight(3)
                    .animationRate(0.2f)
                    .delay(1000));

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
