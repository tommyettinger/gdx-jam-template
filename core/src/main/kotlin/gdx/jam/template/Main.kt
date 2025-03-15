package gdx.jam.template

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.github.tommyettinger.textra.Font
import com.github.tommyettinger.textra.KnownFonts
import com.github.tommyettinger.textra.Layout

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : ApplicationAdapter() {
    private val gameViewport by lazy { FitViewport(32f, 24f) }
    private val uiViewport by lazy { ScreenViewport() }
    private val batch by lazy { SpriteBatch() }
    // You can remove any font variables and files you don't need. Make sure to also remove them from `fonts`.
    private val fontWestern by lazy { KnownFonts.getFont("Sancreek", Font.DistanceFieldType.SDF).setBoldStrength(0.4f) }
    private val fontDark by lazy { KnownFonts.getGrenze(Font.DistanceFieldType.SDF) }
    private val fontNoir by lazy { KnownFonts.getFont("Special-Elite", Font.DistanceFieldType.SDF) }
    private val fontDemonic by lazy { KnownFonts.getYataghan(Font.DistanceFieldType.SDF) }
    private val fonts by lazy { Array.with(fontWestern, fontDark, fontNoir, fontDemonic) }
    private val layout = Layout()

    override fun create() {
        // needed only so KnownFonts.getFont() can load these easily...
        KnownFonts.JSON_NAMES.add("Sancreek")
        KnownFonts.JSON_NAMES.add("Special-Elite")
        // sets the color when no other color has been requested to very light gray
        layout.baseColor = Color.toFloatBits(0.9f, 0.9f, 0.9f, 1f); // very light gray
        // adds black outline around text, and saves that format with the name "outlined"
        // oblique style for "ready"
        // bold style and a slightly-orange, deep red color for "GAME JAM"
        // revert back to the "outlined" format, which uses the default color (very light gray)
        // store this marked-up text in the variable `layout`.
        fontWestern.markup("[%?blacken][(outlined)]Are you [/]ready[] for the [*][deep red 3 orange 1]GAME JAM[ outlined]?!", layout)
    }

    override fun render() {
        // selects a random Font as the current font every 2 seconds or so.
        val font = fonts[((System.currentTimeMillis() ushr 11) * 41L ushr 5 and 3L).toInt()];
        ScreenUtils.clear(0.2f, 0.2f, 0.25f, 1f)
        gameViewport.apply()
        // draw game stuff here when you have it.
        uiViewport.apply()
        batch.begin()
        // these Font methods are only needed because we're not using a FWSkin or a TextraLabel/TypingLabel here.
        // this makes the layout use its correct size for the current font.
        font.calculateSize(layout)
        // this makes the font nice and smooth using the "SDF" (Signed Distance Field) shader.
        font.enableShader(batch)
        // with the current font, draws the already-marked-up `layout`, with the bottom center of its first line at x=320, y=360
        font.drawGlyphs(batch, layout, 320f, 360f, Align.center)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
        uiViewport.update(width, height, true)
        // Keeps the distance field effect smooth for each Font
        fonts.forEach { it.resizeDistanceField(width.toFloat(), height.toFloat(), uiViewport) }
    }

    override fun dispose() {
        batch.dispose()
        fonts.forEach { it.dispose() }
    }
}
