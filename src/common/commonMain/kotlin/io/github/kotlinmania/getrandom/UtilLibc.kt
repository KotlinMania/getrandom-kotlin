// port-lint: source util_libc.rs
package io.github.kotlinmania.getrandom

/**
 * Libc helper utilities mirroring upstream Rust libc utility module.
 */
public object UtilLibc {
    /**
     * Retrieves the last OS error sentinel or returns non-positive errno fallback.
     */
    public fun lastOsError(): GetrandomError = GetrandomError.ERRNO_NOT_POSITIVE

    /**
     * Fills the buffer using a system provider function.
     */
    public fun sysFillExact(
        dest: ByteArray,
        sysFill: (ByteArray) -> Int,
    ): GetrandomResult {
        if (dest.isEmpty()) return GetrandomResult.Ok
        val written = sysFill(dest)
        return if (written >= 0) {
            GetrandomResult.Ok
        } else {
            GetrandomResult.Failure(lastOsError())
        }
    }
}
