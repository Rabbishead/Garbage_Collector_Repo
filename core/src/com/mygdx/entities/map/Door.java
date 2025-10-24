package com.mygdx.entities.map;

import com.badlogic.gdx.math.Vector2;

public class Door {
    private String name;
    private String destination;
    private String orientation;
    private Vector2 center;
    private Vector2 exitPoint;
    private boolean isCenterDoor;

    public Door(String name, String destination, String orientation, Vector2 center, boolean isCenterDoor) {
        this.name = name;
        this.destination = destination;
        this.orientation = orientation;
        this.center = center;
        this.isCenterDoor = isCenterDoor;

        switch (orientation) {
            case "u" -> {
                exitPoint = center.cpy().add(0, -32);
            }
            case "d" -> {
                exitPoint = center.cpy().add(0, 32);
            }
            case "l" -> {
                exitPoint = center.cpy().add(32, 0);
            }
            case "r" -> {
                exitPoint = center.cpy().add(-32, 0);
            }

            default -> {
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrientation() {
        return orientation;
    }

    public Vector2 getCenter() {
        return center;
    }

    public Vector2 getExitPoint() {
        return exitPoint;
    }
    public boolean isCenterDoor() {
        return isCenterDoor;
    }

    public void print() {
        System.out.println(
                "Name: " + name + "\n" +
                        "Destination: " + destination + "\n" +
                        "Orientation: " + orientation + "\n" +
                        "Center: " + center.toString() + "\n" +
                        "ExitPoint: " + exitPoint.toString() + "\n" + 
                        "Is Center Door: " + isCenterDoor + "\n"
                        );
    }
}
