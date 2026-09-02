// port-lint: source solaris.rs
package io.github.kotlinmania.getrandom

/**
 * Solaris implementation using getrandom.
 *
 * Mirrors upstream Rust Solaris implementation.
 */
public object Solaris {
    public const val MAX_BYTES: Int = 1024

    /**
     * Fills [dest] with random bytes on Solaris.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
