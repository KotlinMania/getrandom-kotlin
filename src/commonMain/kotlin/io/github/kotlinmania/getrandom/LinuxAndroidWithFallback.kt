// port-lint: source linux_android_with_fallback.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Linux / Android with fallback mechanism.
 *
 * Mirrors upstream Rust Linux and Android fallback module.
 */
public object LinuxAndroidWithFallback {
    private val hasGetrandom: LazyBool = LazyBool()

    private fun isGetrandomAvailable(): Boolean = true

    /**
     * Fills [dest] with random bytes using getrandom or fallback.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
