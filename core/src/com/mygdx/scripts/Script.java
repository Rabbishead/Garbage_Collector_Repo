package com.mygdx.scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.entities.ScriptableActor;
import com.mygdx.resources.ResourceEnum;

public class Script {

    private ArrayList<ScriptAction> actions = new ArrayList<>();
    
    public Script(ResourceEnum e){
        try(var br = new BufferedReader(new FileReader(e.label))){
            br.lines().forEach( line -> {
                String[] splitted = line.split(" ");
                String func = splitted[0];
                String[] args = splitted[1].split(",");
                actions.add(
                    switch (func){
                        case "MOV" -> new MovAction(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
                        case "ANI" -> new AniAction(ResourceEnum.valueOf(args[0]));
                        case "WAIT" -> new WaitAction(Integer.parseInt(args[0]));
                        case "SND" -> new SndAction(MSG.BLOCK_WALLS);
                        case "LISTEN" -> new ListenAction();
                        case "DO" -> new DoAction(ResourceEnum.TEST_SCRIPT);
                        default -> null;
                    }
                );
            }
            );
        } catch(Exception ignored){ignored.printStackTrace();}
    }

    public void proceed(ScriptableActor a){
        if(actions.isEmpty()) return;

        actions.get(0).perform(a);
        actions.remove(0);
    }
}
