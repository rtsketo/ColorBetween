package eu.rtskseto.cb.core

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Developer: Aristoteles Pasalides
 * Company: Axiom Consulting LTD
 * Date: 10, 10, 2021
 */

class ColorBetween(@ColorInt val first: Int, @ColorInt val second: Int) {
    val alpha = IntBetween(Color.alpha(first), Color.alpha(second))
    val green = IntBetween(Color.green(first), Color.green(second))
    val blue = IntBetween(Color.blue(first), Color.blue(second))
    val red = IntBetween(Color.red(first), Color.red(second))

    fun get(ratio: Float) = Color.argb(
        alpha.get(ratio),
        red.get(ratio),
        green.get(ratio),
        blue.get(ratio)
    )
}
