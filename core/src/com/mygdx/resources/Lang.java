package com.mygdx.resources;

public enum Lang {
    ITA("assets/dialogues/entities/ita"),
    ENG("assets/dialogues/entities/eng");

    public String label;

    Lang(String string) {
        this.label = string;
    }

    private static Lang current = Lang.ITA;

    public static Lang getCurrent() {
        return current;
    }

    public static void setCurrent(Lang lang) {
        current = lang;
        RM.get().updateLang();
    }


}
