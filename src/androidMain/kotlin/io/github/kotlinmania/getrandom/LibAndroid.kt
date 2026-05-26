// port-lint: source src/linux_android.rs
package io.github.kotlinmania.getrandom

import java.security.SecureRandom as JavaSecureRandom

/**
 * Android (JVM-side) backend: `java.security.SecureRandom`. On real
 * Android the default provider is `AndroidOpenSSL` whose `nextBytes`
 * goes through `BoringSSL::RAND_bytes`, which is in turn seeded from
 * the kernel `getrandom(2)` syscall — the same path the upstream Rust
 * `getrandom` takes via the `linux_android` module.
 */
private val secureRandom = JavaSecureRandom()

internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    secureRandom.nextBytes(dest)
    return GetrandomResult.Ok
}
