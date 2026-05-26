// port-lint: source src/lib.rs
package io.github.kotlinmania.getrandom

import java.security.SecureRandom as JavaSecureRandom

/**
 * JVM backend: `java.security.SecureRandom`. The default SecureRandom
 * provider on every JVM Sydney's workspace cares about (HotSpot, OpenJDK,
 * Zulu, GraalVM) sources its entropy from the same OS-provided RNG that
 * the upstream Rust `getrandom` calls — `/dev/urandom` on Linux + macOS,
 * `BCryptGenRandom` on Windows. Wrapping the JDK API here avoids the
 * cinterop dance the K/Native targets have to do.
 */
private val secureRandom = JavaSecureRandom()

internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    secureRandom.nextBytes(dest)
    return GetrandomResult.Ok
}
