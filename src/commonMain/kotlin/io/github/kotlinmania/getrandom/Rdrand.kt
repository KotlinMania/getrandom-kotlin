// port-lint: source rdrand.rs
package io.github.kotlinmania.getrandom

/**
 * RDRAND backend for x86 architecture.
 *
 * Mirrors upstream Rust RDRAND module.
 */
public object Rdrand {
    private const val RETRY_LIMIT: Int = 10

    /**
     * Fills [dest] with random bytes using hardware RDRAND instruction.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
