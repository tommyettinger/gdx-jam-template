package gdx.jam.template.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import gdx.jam.template.utils.BaseActor;


public class Element extends BaseActor {
    public boolean isDead;
    public enum Type {RED, YELLOW, BLUE}

    public Element(float x, float y, Stage stage) {
        super(x, y, stage);
        loadImage("whitePixel");
        setSize(4, 4);
    }


    public static void centerPositionCamera(Stage stage) {
        float mapTileWidth = stage.getWidth();
        float mapTileHeight = stage.getHeight();
        System.out.println(mapTileWidth / 2 + ", " + mapTileHeight / 2);
        OrthographicCamera camera = (OrthographicCamera) stage.getCamera();
        camera.zoom = 1f;
        camera.position.set(new Vector3(
            mapTileWidth / 2,
            mapTileHeight / 2,
            0f
        ));
        camera.update();
    }

    private void shakeCamera(float duration) {
        isShakyCam = true;
        new BaseActor(0f, 0f, getStage()).addAction(Actions.sequence(
                Actions.delay(duration),
                Actions.run(() -> {
                    isShakyCam = false;
                    centerPositionCamera(getStage());
                })
        ));
    }
}
