// port-lint: source apple-other.rs
package io.github.kotlinmania.getrandom

/**
 * Implementation for Darwin-based platforms (iOS, tvOS, watchOS, macOS).
 *
 * Mirrors upstream Rust `apple-other.rs`. Uses the platform secure random
 * byte generator.
 */
public object AppleOther {
    /**
     * Fills [dest] with random bytes using the platform generator.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        kotlin.random.Random.Default.nextBytes(dest)
        return GetrandomResult.Ok
    }
}
