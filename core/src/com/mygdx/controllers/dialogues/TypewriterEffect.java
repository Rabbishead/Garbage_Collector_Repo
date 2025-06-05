package com.mygdx.controllers.dialogues;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class TypewriterEffect {

    private final Label label;
    private String fullText;
    private float charInterval = 0.04f;
    private boolean isRunning = false;
    private Runnable onFinished;

    public TypewriterEffect(Label label) {
        this.label = label;
    }

    public void start(String text) {
        start(text, charInterval, null);
    }

    public void start(String text, float interval, Runnable onFinished) {
        this.fullText = text;
        this.charInterval = interval;
        this.onFinished = onFinished;
        isRunning = true;

        label.setText("");
        Array<Action> actions = new Array<>();
        final StringBuilder displayed = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            final int index = i;
            actions.add(Actions.sequence(
                    Actions.delay(charInterval * (index + 1)),
                    Actions.run(() -> {
                        displayed.append(fullText.charAt(index));
                        label.setText(displayed.toString());
                        System.out.println("Typing: " + displayed);
                    })));

        }

        // When done, mark complete and call callback if any
        // actions.add(Actions.run(() -> {
        // isRunning = false;
        // if (onFinished != null) onFinished.run();
        // }));

        label.clearActions();
        Action sequence = Actions.sequence(actions.toArray(Action.class));
        label.addAction(sequence);
    }

    public void skip() {
        if (isRunning) {
            label.clearActions();
            label.setText(fullText);
            isRunning = false;
            if (onFinished != null)
                onFinished.run();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
