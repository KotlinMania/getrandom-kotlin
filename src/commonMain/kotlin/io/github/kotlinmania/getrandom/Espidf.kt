// port-lint: source espidf.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for ESP-IDF.
 *
 * Mirrors upstream Rust ESP-IDF implementation.
 */
public object Espidf {
    /**
     * Fills [dest] with random bytes under ESP-IDF.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
