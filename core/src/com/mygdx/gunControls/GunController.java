package com.mygdx.gunControls;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.gunControls.guns.Gun;

public class GunController {
    private static GunController instance;
    private ArrayList<Gun> guns;
    private int gunIndex;

    public static GunController get() {
        if (instance == null)
            instance = new GunController();
        return instance;
    }

    private GunController() {
    }

    public void loadGun(Gun gun) {
        guns.add(gun);
    }

    public void loadGuns(ArrayList<Gun> guns) {
        this.guns.addAll(guns);
    }

    public void removeGun(Gun gun) {
        guns.remove(gun);
    }

    public void act() {
        if (guns.isEmpty()) return;
        Gun currentGun = guns.get(gunIndex);
        int j = 0;
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            j = currentGun.leftTrigger();
        } else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            j = currentGun.rightTrigger();
        }
        gunIndex = (gunIndex + j) % guns.size();
    }
}