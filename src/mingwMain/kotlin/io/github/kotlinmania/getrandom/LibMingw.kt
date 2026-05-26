// port-lint: source src/windows.rs
package io.github.kotlinmania.getrandom

/**
 * Windows backend (mingwX64). Upstream Rust uses `BCryptGenRandom`
 * from the Windows CNG API. Kotlin/Native's bundled mingw sysroot
 * doesn't ship the bcrypt.h cinterop headers, so adding a real
 * cinterop binding to `bcrypt.dll` is deferred to a follow-up
 * release.
 *
 * Until the bcrypt cinterop lands, return
 * [GetrandomError.WINDOWS_RTL_GEN_RANDOM] so callers see an explicit
 * failure rather than silently-weak randomness from `rand()`.
 * Documented in README.
 */
internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    return GetrandomResult.Failure(GetrandomError.WINDOWS_RTL_GEN_RANDOM)
}
