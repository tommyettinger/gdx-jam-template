package gdx.jam.template.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.Styles;
import com.github.tommyettinger.textra.TypingLabel;

import gdx.jam.template.utils.AssetLoader;
import gdx.jam.template.utils.BaseGame;
import gdx.jam.template.utils.GameUtils;

public class MadeByLabel extends TypingLabel {

    public MadeByLabel() {
        super("Made by Sandra Moen 2025", new Styles.LabelStyle(AssetLoader.mySkin.get("Play-Bold20white", Font.class), null));
        setAlignment(Align.center);
        setColor(Color.GRAY);
        addClickListener();
        GameUtils.setWidgetHoverColor(this);
    }

    private void addClickListener() {
        addListener(
                (Event event) -> {
                    if (GameUtils.isTouchDownEvent(event))
                        openURIWithDelay();
                    return false;
                }
        );
    }

    private void openURIWithDelay() {
        AssetLoader.clickSound.play(BaseGame.soundVolume);
        addAction(Actions.sequence(
                Actions.delay(.5f),
                Actions.run(() -> Gdx.net.openURI("https://sandramoen.no"))
        ));
    }
}
