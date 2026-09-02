// port-lint: source solid.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for SOLID OS.
 *
 * Mirrors upstream Rust SOLID implementation.
 */
public object Solid {
    /**
     * Fills [dest] with random bytes on SOLID OS.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
