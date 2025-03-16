package gdx.jam.template.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.textra.FWSkin;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.KnownFonts;
import com.github.tommyettinger.textra.Styles;

public class AssetLoader implements AssetErrorListener {

    public static TextureAtlas textureAtlas;
    public static Skin mySkin;

    public static String defaultShader;
    public static String shockwaveShader;

    public static Sound clickSound;
    public static Sound hoverOverEnterSound;

    public static Array<Music> music;
    public static Music menuMusic;

    static {
        long time = System.currentTimeMillis();
        BaseGame.assetManager = new AssetManager();
        BaseGame.assetManager.setErrorListener(new AssetLoader());

        loadAssets();
        BaseGame.assetManager.finishLoading();
        assignAssets();

        Gdx.app.log(AssetLoader.class.getSimpleName(), "Asset manager took " + (System.currentTimeMillis() - time) + " ms to load all game assets.");
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(AssetLoader.class.getSimpleName(), "Could not load asset: " + asset.fileName, throwable);
    }

    public static Styles.LabelStyle getLabelStyle(String fontName) {
        return new Styles.LabelStyle(AssetLoader.mySkin.get(fontName, Font.class), null);
    }

    private static void loadAssets() {
        // images
        BaseGame.assetManager.setLoader(Text.class, new TextLoader(new InternalFileHandleResolver()));
        BaseGame.assetManager.load("images/included/packed/images.atlas", TextureAtlas.class);

        // music
        // menuMusic = assetManager.get("audio/music/587251__lagmusics__epic-and-aggressive-percussion.mp3", Music.class);

        // sounds
        BaseGame.assetManager.load("audio/sound/click.wav", Sound.class);
        BaseGame.assetManager.load("audio/sound/hoverOverEnter.wav", Sound.class);

        // i18n

        // shaders
        BaseGame.assetManager.load(new AssetDescriptor("shaders/default.vert.glsl", Text.class, new TextLoader.TextParameter()));
        BaseGame.assetManager.load(new AssetDescriptor("shaders/shockwave.frag.glsl", Text.class, new TextLoader.TextParameter()));

        // skins

        // fonts

        // other
        // BaseGame.assetManager.load(AssetDescriptor("other/jentenavn.csv", Text::class.java, TextLoader.TextParameter()))
    }

    private static void assignAssets() {
        // images
        textureAtlas = BaseGame.assetManager.get("images/included/packed/images.atlas");

        // music
        music = new Array();
        // menuMusic = assetManager.get("audio/music/587251__lagmusics__epic-and-aggressive-percussion.mp3", Music.class);
        // music.add(menuMusic);

        // sounds
        clickSound = BaseGame.assetManager.get("audio/sound/click.wav", Sound.class);
        hoverOverEnterSound = BaseGame.assetManager.get("audio/sound/hoverOverEnter.wav", Sound.class);

        // i18n

        // shaders
        defaultShader = BaseGame.assetManager.get("shaders/default.vert.glsl", Text.class).getString();
        shockwaveShader = BaseGame.assetManager.get("shaders/shockwave.frag.glsl", Text.class).getString();

        // skins
        mySkin = new FWSkin(Gdx.files.internal("skins/mySkin/mySkin.json"));

        // fonts
        loadFonts();

        // other
    }

    private static void loadFonts() {
        KnownFonts.setAssetPrefix("skins/mySkin/fonts/");
        KnownFonts.JSON_NAMES.add("Sancreek");
        KnownFonts.JSON_NAMES.add("Special-Elite");

        // magic number ensures scale ~= 1, based on screen width
        // 1.01f can be raised to make fonts fuzzier.
        float scale = Gdx.graphics.getWidth() * (1.01f/1920f);

        mySkin.get("Play-Bold20white", Font.class).scaleHeightTo(scale);
        mySkin.get("Play-Bold40white", Font.class).scaleHeightTo(scale);
        mySkin.get("Play-Bold59white", Font.class).scaleHeightTo(scale);
    }

}
