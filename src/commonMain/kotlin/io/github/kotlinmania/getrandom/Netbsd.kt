// port-lint: source netbsd.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for NetBSD.
 *
 * Mirrors upstream Rust NetBSD implementation.
 */
public object Netbsd {
    /**
     * Fills [dest] with random bytes under NetBSD.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
