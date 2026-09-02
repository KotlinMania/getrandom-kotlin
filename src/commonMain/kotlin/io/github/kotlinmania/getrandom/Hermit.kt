// port-lint: source hermit.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Hermit.
 *
 * Mirrors upstream Rust Hermit implementation.
 */
public object Hermit {
    /**
     * Fills [dest] with random bytes under Hermit.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
