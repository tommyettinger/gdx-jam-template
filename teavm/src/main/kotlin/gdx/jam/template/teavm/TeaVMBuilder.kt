package gdx.jam.template.teavm

import java.io.File
import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder
import com.github.xpenatan.gdx.backends.teavm.config.plugins.TeaReflectionSupplier
import com.github.xpenatan.gdx.backends.teavm.gen.SkipClass
import org.teavm.vm.TeaVMOptimizationLevel

/** Builds the TeaVM/HTML application. */
@SkipClass
object TeaVMBuilder {
    @JvmStatic fun main(arguments: Array<String>) {
        val teaBuildConfiguration = TeaBuildConfiguration().apply {
            assetsPath.add(AssetFileHandle("../assets"))
            webappPath = File("build/dist").canonicalPath
            // Register any extra classpath assets here:
            // additionalAssetsClasspathFiles += "gdx/jam/template/asset.extension"
        }

        // Register any classes or packages that require reflection here:
//        TeaReflectionSupplier.addReflectionClass("com.github.tommyettinger.textra.effects") // Do we need this?

        val tool = TeaBuilder.config(teaBuildConfiguration)
        tool.mainClass = "gdx.jam.template.teavm.TeaVMLauncher"
        tool.optimizationLevel = TeaVMOptimizationLevel.FULL
        tool.setObfuscated(false)
        TeaBuilder.build(tool)
    }
}
