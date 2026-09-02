// port-lint: source getrandom.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation using the standard OS getrandom system interface.
 *
 * Mirrors upstream Rust `getrandom.rs`.
 */
public object GetrandomMod {
    /**
     * Fills [dest] with random bytes via the default random system source.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
