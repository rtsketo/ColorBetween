package eu.rtskseto.cb.core

/**
 * Developer: Aristoteles Pasalides
 * Company: Axiom Consulting LTD
 * Date: 10, 10, 2021
 */

class IntBetween(private val first: Int, private val second: Int) {
    fun get(ratio: Float) = (first + (second - first) * ratio).toInt()
}
