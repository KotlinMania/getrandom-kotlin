// port-lint: source js.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for JavaScript and Web environments.
 *
 * Mirrors upstream Rust `js.rs`.
 */
public object Js {
    /** Buffer size for Web Crypto batch calls. */
    public const val WEB_CRYPTO_BUFFER_SIZE: Int = 256

    /**
     * Fills [dest] with random bytes in JS / browser environments.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
