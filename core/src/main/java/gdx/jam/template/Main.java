package gdx.jam.template;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.KnownFonts;
import com.github.tommyettinger.textra.Layout;
import gdx.jam.template.utils.BaseGame;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends BaseGame {
    private FitViewport gameViewport;
    private ScreenViewport uiViewport;
    private Batch batch;
    private Font fontWestern;
    private Font fontDark;
    private Font fontNoir;
    private Font fontDemonic;
    private Array<Font> fonts;
    private Layout layout = new Layout();

    @Override
    public void create() {
        super.create();

        gameViewport = new FitViewport(32f, 24f);
        uiViewport = new ScreenViewport();
        batch = new CpuSpriteBatch();

        fontWestern = KnownFonts.getFont("Sancreek", Font.DistanceFieldType.SDF).setBoldStrength(0.4f);
        fontDark = KnownFonts.getGrenze(Font.DistanceFieldType.SDF);
        fontNoir = KnownFonts.getFont("Special-Elite", Font.DistanceFieldType.SDF);
        fontDemonic = KnownFonts.getYataghan(Font.DistanceFieldType.SDF);
        fonts = Array.with(fontWestern, fontDark, fontNoir, fontDemonic);
        // sets the color when no other color has been requested to very light gray
        layout.setBaseColor(Color.toFloatBits(0.9f, 0.9f, 0.9f, 1f)); // very light gray
        // adds black outline around text, and saves that format with the name "outlined"
        // oblique style for "ready"
        // bold style and a slightly-orange, deep red color for "GAME JAM"
        // revert back to the "outlined" format, which uses the default color (very light gray)
        // store this marked-up text in the variable `layout`.
        fontWestern.markup("[%?blacken][(outlined)]Are you [/]ready[] for the [*][deep red 3 orange 1]GAME JAM[ outlined]?!", layout);

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height);
        uiViewport.update(width, height, true);
        for(Font font : fonts)
            font.resizeDistanceField(width, height, uiViewport);
    }

    @Override
    public void render() {
        // selects a random Font as the current font every 2 seconds or so.
        Font font = fonts.get((int)((System.currentTimeMillis() >>> 11) * 41L >>> 5 & 3L));
        ScreenUtils.clear(0.2f, 0.2f, 0.25f, 1f);
        gameViewport.apply();
        // draw game stuff here when you have it.
        uiViewport.apply();
        batch.begin();
        // these Font methods are only needed because we're not using a FWSkin or a TextraLabel/TypingLabel here.
        // this makes the layout use its correct size for the current font.
        font.calculateSize(layout);
        // this makes the font nice and smooth using the "SDF" (Signed Distance Field) shader.
        font.enableShader(batch);
        // with the current font, draws the already-marked-up `layout`, with the bottom center of its first line at x=320, y=360
        font.drawGlyphs(batch, layout, 320f, 360f, Align.center);
        batch.end();
    }
}
