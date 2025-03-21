package gdx.jam.template.screens.shell;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import gdx.jam.template.utils.AssetLoader;
import gdx.jam.template.utils.BaseScreen;


public class LevelSelectScreen extends BaseScreen {
    @Override
    public void initialize() {
        addTextButtons();
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.Q)
            Gdx.app.exit();
        return super.keyDown(keycode);
    }

    private void addTextButtons() {
        uiTable.defaults()
                .width(Gdx.graphics.getWidth() * .15f)
                .height(Gdx.graphics.getHeight() * .075f)
                .spaceTop(Gdx.graphics.getHeight() * .01f);
//        for (int i = 0; i < AssetLoader.maps.size; i++)
//            uiTable.add(levelButton(i)).row();
        uiTable.defaults().reset();
    }

    private TextButton levelButton(Integer levelNumber) {
        String buttonText = "Level " + levelNumber;
        if (levelNumber == 0) buttonText = "Test level";
        TextButton button = new TextButton(buttonText, AssetLoader.mySkin);
//        button.addListener(
//                (Event event) -> {
//                    if (GameUtils.isTouchDownEvent(event))
//                        BaseGame.setActiveScreen(new LevelScreen(AssetLoader.maps.get(levelNumber)));
//                    return false;
//                }
//        );
        return button;
    }
}
