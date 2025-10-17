package com.mygdx;

import com.mygdx.hud.Hud;

public class Money {
    private static int money;

    public static int getMoney() {
        return money;
    }
    public static void setMoney(int amount) {
        money = amount;
    }
    public static void gainMoney(int amount){
        money += amount;
        Hud.get().setMoney(amount, true);
    }
    public static void loseMoney(int amount){
        money -= amount;
        Hud.get().setMoney(amount, false);
    }
}
