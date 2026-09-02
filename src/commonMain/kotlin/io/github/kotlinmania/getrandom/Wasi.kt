// port-lint: source wasi.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for WASI targets.
 *
 * Mirrors upstream Rust `wasi.rs`.
 */
public object Wasi {
    /**
     * Fills [dest] with random bytes under WASI.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
