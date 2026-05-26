// port-lint: source src/apple-other.rs
package io.github.kotlinmania.getrandom

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.pin
import platform.posix.O_RDONLY
import platform.posix.close
import platform.posix.errno
import platform.posix.open
import platform.posix.read
import platform.posix.size_t

/**
 * Apple backend (macOS / iOS / tvOS / watchOS): reads bytes from
 * `/dev/urandom`. Upstream Rust uses `CCRandomGenerateBytes` (macOS)
 * or `getentropy` (iOS/tvOS/watchOS), both of which ultimately read
 * the same kernel RNG that backs `/dev/urandom` on every Apple
 * platform. The character device path is available on every Apple
 * Kotlin/Native target without adding a Security-framework cinterop.
 */
@OptIn(ExperimentalForeignApi::class)
internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    val fd = open("/dev/urandom", O_RDONLY)
    if (fd < 0) return GetrandomResult.Failure(GetrandomError(errno.toUInt()))
    try {
        val pinned = dest.pin()
        try {
            var offset = 0
            while (offset < dest.size) {
                val remaining = (dest.size - offset).convert<size_t>()
                val ptr = pinned.addressOf(offset)
                val n = read(fd, ptr, remaining)
                if (n < 0) return GetrandomResult.Failure(GetrandomError(errno.toUInt()))
                if (n == 0L) return GetrandomResult.Failure(GetrandomError.UNEXPECTED)
                offset += n.toInt()
            }
        } finally {
            pinned.unpin()
        }
    } finally {
        close(fd)
    }
    return GetrandomResult.Ok
}
