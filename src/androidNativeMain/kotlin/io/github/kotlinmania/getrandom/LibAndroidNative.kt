// port-lint: source src/linux_android.rs
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
 * Android Native (NDK) backend: reads bytes from `/dev/urandom`.
 * Same approach as `linuxMain` — Bionic provides `/dev/urandom`
 * on every Android API level, sourced from the same kernel RNG that
 * upstream Rust's `linux_android` module reaches through `getrandom(2)`.
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
                val nInt = n.convert<Int>()
                if (nInt == 0) return GetrandomResult.Failure(GetrandomError.UNEXPECTED)
                offset += nInt
            }
        } finally {
            pinned.unpin()
        }
    } finally {
        close(fd)
    }
    return GetrandomResult.Ok
}
