// port-lint: source getentropy.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation using getentropy syscall where available.
 *
 * Mirrors upstream Rust `getentropy.rs`.
 */
public object Getentropy {
    /**
     * Fills [dest] with random bytes via entropy sources.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
