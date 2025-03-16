package gdx.jam.template.actors.map;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import gdx.jam.template.utils.AssetLoader;
import gdx.jam.template.utils.BaseActor;


public class Background extends BaseActor {
    private Array<TextureAtlas.AtlasRegion> animationImages = new Array();

    public Background(float x, float y, Stage stage) {
        super(x, y, stage);
        animationImages.add(AssetLoader.textureAtlas.findRegion("grass"));
        animation = new Animation(2f, animationImages, Animation.PlayMode.LOOP);
        setAnimation(animation);/*
        setSize(TiledMapActor.mapWidth, TiledMapActor.mapHeight);*/
    }
}
