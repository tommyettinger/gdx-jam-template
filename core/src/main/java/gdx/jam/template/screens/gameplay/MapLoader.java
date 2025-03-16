package gdx.jam.template.screens.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import gdx.jam.template.actors.Element;

public class MapLoader {
    public Element player;
    private Stage mainStage;

    public MapLoader(Stage mainStage, Element player) {
        this.mainStage = mainStage;

        this.player = player;

        initializeElement();
    }

    private void initializeElement() {
        String layerName = "actors";
        String propertyName = "player";
    }
}
