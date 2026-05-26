// port-lint: source src/use_file.rs
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
 * Linux backend: reads bytes from `/dev/urandom`. Upstream Rust
 * prefers the `getrandom(2)` syscall and falls back to `/dev/urandom`
 * (see `src/use_file.rs`). This port goes straight to `/dev/urandom`
 * — Kotlin/Native's bundled posix headers don't ship a stable
 * `getrandom(2)` constant across every Linux sysroot variant, and
 * `/dev/urandom` is the same OS RNG source on every Linux ≥ 3.17 (the
 * floor upstream's documented fallback path supports).
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
