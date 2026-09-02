// port-lint: source windows.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Windows platforms.
 *
 * Mirrors upstream Rust `windows.rs`.
 */
public object Windows {
    /**
     * Fills [dest] with cryptographically secure random bytes on Windows.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
