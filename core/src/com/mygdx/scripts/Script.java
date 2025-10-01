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
                if(func.equals("SKIP")){ //has no params
                    actions.add(new SkipAction());
                    return;

                }
                String[] args = splitted[1].split(",");
                actions.add(
                    switch (func){
                        case "MOV" -> new MovAction(Float.parseFloat(args[0]), Float.parseFloat(args[1]), false);
                        case "MOVREL" -> new MovAction(Float.parseFloat(args[0]), Float.parseFloat(args[1]), true);
                        case "ANI" -> new AniAction(ResourceEnum.valueOf(args[0]));
                        case "WAIT" -> new WaitAction(Float.parseFloat(args[0]));
                        case "SND" -> new SndAction(MSG.valueOf(args[0]));
                        case "LISTEN" -> new ListenAction(MSG.valueOf(args[0]));
                        case "DO" -> new DoAction(ResourceEnum.valueOf(args[0]));
                        default -> null;
                    }
                );
            }
            );
        } catch(Exception ignored){ignored.printStackTrace();}
    }

    public void proceed(ScriptableActor a){
        if(actions.isEmpty()) return;

        ScriptAction action = actions.get(0);
        actions.remove(0);
        action.perform(a);
        
    }
}
