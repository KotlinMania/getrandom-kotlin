// port-lint: source vxworks.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for VxWorks.
 *
 * Mirrors upstream Rust VxWorks implementation.
 */
public object Vxworks {
    /**
     * Fills [dest] with random bytes on VxWorks.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
