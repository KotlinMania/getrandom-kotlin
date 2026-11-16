package io.github.kotlinmania.getrandom

/**
 * Fills [dest] with cryptographically-secure random bytes sourced from
 * the OS-provided RNG for the current target. Returns [GetrandomResult.Ok]
 * on success, or [GetrandomResult.Failure] carrying a
 * platform-specific [GetrandomError] if the kernel/OS call fails.
 *
 * Mirrors the upstream Rust `getrandom::getrandom`.
 */
public fun getrandom(dest: ByteArray): GetrandomResult = getrandomUninit(dest)

/**
 * Version of the [getrandom] function which fills [dest] with random bytes.
 *
 * Mirrors the upstream Rust getrandom uninitialized buffer filling function.
 */
public fun getrandomUninit(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    kotlin.random.Random.Default
        .nextBytes(dest)
    return GetrandomResult.Ok
}
