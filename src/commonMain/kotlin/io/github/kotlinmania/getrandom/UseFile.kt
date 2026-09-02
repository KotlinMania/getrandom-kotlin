// port-lint: source use_file.rs
package io.github.kotlinmania.getrandom

/**
 * File descriptor based entropy source.
 *
 * Mirrors upstream Rust file reading entropy provider.
 */
public object UseFile {
    public const val FILE_PATH: String = "/dev/urandom"

    /**
     * Fills [dest] with random bytes read from random device file.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
