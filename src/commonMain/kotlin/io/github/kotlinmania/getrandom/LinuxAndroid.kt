// port-lint: source linux_android.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Linux and Android environments.
 *
 * Mirrors upstream Rust Linux and Android implementation.
 */
public object LinuxAndroid {
    /**
     * Fills [dest] with random bytes on Linux and Android.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
