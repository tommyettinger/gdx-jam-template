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
        KnownFonts.JSON_NAMES.add("Sancreek")
        KnownFonts.JSON_NAMES.add("Special-Elite")
        layout.baseColor = Color.toFloatBits(0.9f, 0.9f, 0.9f, 1f);
        fontWestern.markup("[%?blacken]Are you [/]ready[] for the [*][deep red 3 orange 1]GAME JAM[ ]?!", layout)
    }

    override fun render() {
        // selects a random new Font every 2 seconds or so.
        val font = fonts[((System.currentTimeMillis() ushr 11) * 41L ushr 5 and 3L).toInt()];
        ScreenUtils.clear(0.2f, 0.2f, 0.25f, 1f)
        gameViewport.apply()

        uiViewport.apply()
        batch.begin()
        // These Font methods are only needed because we're not using a FWSkin or a TextraLabel/TypingLabel here.
        font.calculateSize(layout)
        font.enableShader(batch)
        font.drawGlyphs(batch, layout, 320f, 360f, Align.center)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        uiViewport.update(width, height, true)
        fonts.forEach { it.resizeDistanceField(width.toFloat(), height.toFloat(), uiViewport) }
    }

    override fun dispose() {
        batch.dispose()
        fonts.forEach { it.dispose() }
    }
}
