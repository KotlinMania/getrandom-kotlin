// port-lint: source fuchsia.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Fuchsia Zircon.
 *
 * Mirrors upstream Rust Fuchsia implementation.
 */
public object Fuchsia {
    /**
     * Fills [dest] with random bytes on Fuchsia.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
