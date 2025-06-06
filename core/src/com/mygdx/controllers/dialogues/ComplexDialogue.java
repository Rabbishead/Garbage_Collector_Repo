package com.mygdx.controllers.dialogues;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.bladecoder.ink.runtime.Choice;
import com.bladecoder.ink.runtime.Story;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;
import java.util.ArrayList;
import java.util.List;

public class ComplexDialogue extends Actor {
    private Story story;

    private Table table;

    private TypewriterEffect typer;

    public ComplexDialogue(Story story) {
        setX(0);
        setY(0);
        Utils.getCurrentHud().addComponent(this);
        this.story = story;
        try {
            story.resetState();
        } catch (Exception e) {
            e.printStackTrace();
        }

        var labelStyle = Data.skin.get("default", Label.LabelStyle.class);

        var labelColor = new Pixmap(1, 1, Pixmap.Format.RGB888);
        labelColor.setColor(1, 0, 0, 1);
        labelColor.fill();

        var tableColor = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        tableColor.setColor(0, 0, 0, 0.5f); // semi-transparent black
        tableColor.fill();
        var bg = new TextureRegionDrawable(new TextureRegion(new Texture(tableColor)));

        var dialogueLabel = new Label("", labelStyle);
        dialogueLabel.setWrap(true);
        dialogueLabel.getStyle().background = new Image(new Texture(labelColor)).getDrawable();

        typer = new TypewriterEffect(dialogueLabel);

        table = new Table();
        table.setFillParent(true);
        table.defaults().expand();
        table.setBackground(bg);
        //table.debug();

        table.add(dialogueLabel)
                .width(600)
                .pad(20)
                .top()
                .padTop(100)
                .colspan(100);
        table.row();

        Utils.getCurrentHud().addComponent(table);

        continueStory();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (typer.isRunning() && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
            typer.skip();
        }
    }

    public String getQuestion() {
        try {
            return story.getCurrentText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<String> getResponses() {
        var responses = new ArrayList<String>();
        story.getCurrentChoices().forEach(element -> responses.add(element.getText()));
        return responses;
    }

    public void continueStory() {
        if (!story.canContinue()){
            removeDialogue();
            return;
        }
        try {
            story.Continue();
            typer.start(getQuestion(), 0.0005f, () -> {
                if (story.getCurrentChoices().size() == 0)
                    waitForClickToContinue();
            });

            if (story.getCurrentChoices().size() > 0) {
                showChoices(story.getCurrentChoices());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waitForClickToContinue() {
        // Full-screen transparent actor that catches a single click
        Image clickCatcher = new Image();
        clickCatcher.setFillParent(true);
        clickCatcher.setTouchable(Touchable.enabled);
        clickCatcher.debug();

        Gdx.input.setInputProcessor(getStage());
        clickCatcher.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickCatcher.remove();
                continueStory();
            }
        });

        Utils.getCurrentHud().addComponent(clickCatcher);
    }

    public void showChoices(List<Choice> choicesList) {

        for (int i = 0; i < choicesList.size(); i++) {
            final int index = i;
            Choice choice = choicesList.get(i);

            var labelStyle = Data.skin.get("default", Label.LabelStyle.class);

            var labelColor = new Pixmap(1, 1, Pixmap.Format.RGB888);
            labelColor.setColor(1, 0, 0, 1);
            labelColor.fill();

            var choiceLabel = new Label(choice.getText(), labelStyle);
            choiceLabel.setWrap(true);
            choiceLabel.setTouchable(Touchable.enabled);
            choiceLabel.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
            choiceLabel.setAlignment(Align.center);

            choiceLabel.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        story.chooseChoiceIndex(index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    continueStory();
                }
            });

            table.add(choiceLabel)
                    .pad(20)
                    .fillX()
                    .expandX()
                    .center();
        }
    }

    public boolean canContinue() {
        return story.canContinue();
    }

    public void chose(int optionNumber) {
        try {
            story.chooseChoiceIndex(optionNumber);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public int choiceNumber() {
        return story.getCurrentChoices().size();
    }

    private void removeDialogue() {
        StateManager.updateBoolState(StateEnum.PAUSE, false);
        table.remove();
        remove();
    }
}
