// port-lint: source src/lib.rs
package io.github.kotlinmania.getrandom

/**
 * Result of a [getrandom] call. Either the buffer was filled with
 * cryptographically-secure bytes from the OS-provided RNG, or the call
 * failed with a platform-specific error code.
 *
 * Sealed so the Swift Export bridge stays clean (avoids the
 * `kotlin.Result<X>` → `Array<Any?>` unchecked cast warned about in
 * workspace AGENTS.md §4 "Stdlib" hazard).
 */
public sealed class GetrandomResult {
    public data object Ok : GetrandomResult()
    public data class Failure(public val error: GetrandomError) : GetrandomResult()

    public val isOk: Boolean get() = this is Ok

    /** Convenience: error message if this is a [Failure], otherwise `null`. */
    public val errorMessage: String? get() = (this as? Failure)?.error?.displayMessage
}

/**
 * Mirrors the upstream Rust `getrandom::Error`. Non-`Throwable` — exposing
 * a `Throwable` subclass in commonMain leaks `Array<Any?>` into the
 * Swift Export bridge (workspace AGENTS.md §4).
 *
 * @property code Non-zero error code. Values below [INTERNAL_START] are
 *   raw OS errors (positive `i32` from `errno`, `GetLastError`, etc.).
 *   Values at or above [INTERNAL_START] are reserved internal sentinels
 *   like [UNSUPPORTED], [WEB_CRYPTO], etc.
 */
public data class GetrandomError(public val code: UInt) {
    /** Human-readable description of this error. */
    public val displayMessage: String get() = when (code) {
        UNSUPPORTED.code -> "getrandom: this target is not supported"
        ERRNO_NOT_POSITIVE.code -> "getrandom: errno returned a non-positive value"
        UNEXPECTED.code -> "getrandom: unexpected situation"
        IOS_SEC_RANDOM.code -> "getrandom: CCRandomGenerateBytes failed"
        WINDOWS_RTL_GEN_RANDOM.code -> "getrandom: BCryptGenRandom / RtlGenRandom failed"
        FAILED_RDRAND.code -> "getrandom: RDRAND failed"
        NO_RDRAND.code -> "getrandom: RDRAND unsupported on this target"
        WEB_CRYPTO.code -> "getrandom: Web Crypto API unavailable"
        WEB_GET_RANDOM_VALUES.code -> "getrandom: crypto.getRandomValues failed"
        NODE_CRYPTO.code -> "getrandom: Node.js crypto module unavailable"
        NODE_RANDOM_FILL_SYNC.code -> "getrandom: crypto.randomFillSync failed"
        WASI_RANDOM_GET.code -> "getrandom: WASI random_get failed"
        else -> "getrandom: OS error (code=$code)"
    }

    /** Raw OS errno-style value, or `null` when this is an internal sentinel. */
    public val rawOsError: Int? get() = if (code < INTERNAL_START.code) code.toInt() else null

    public companion object {
        /** Threshold above which codes are internal sentinels (matches upstream Rust). */
        public val INTERNAL_START: GetrandomError = GetrandomError(1u shl 31)

        public val UNSUPPORTED: GetrandomError = internalError(0u)
        public val ERRNO_NOT_POSITIVE: GetrandomError = internalError(1u)
        public val UNEXPECTED: GetrandomError = internalError(2u)
        public val IOS_SEC_RANDOM: GetrandomError = internalError(3u)
        public val WINDOWS_RTL_GEN_RANDOM: GetrandomError = internalError(4u)
        public val FAILED_RDRAND: GetrandomError = internalError(5u)
        public val NO_RDRAND: GetrandomError = internalError(6u)
        public val WEB_CRYPTO: GetrandomError = internalError(7u)
        public val WEB_GET_RANDOM_VALUES: GetrandomError = internalError(8u)
        public val NODE_CRYPTO: GetrandomError = internalError(12u)
        public val NODE_RANDOM_FILL_SYNC: GetrandomError = internalError(13u)

        /** Not in upstream Rust (upstream wires WASI through a different code); kept here to surface WASI-specific failures distinctly. */
        public val WASI_RANDOM_GET: GetrandomError = internalError(15u)

        private fun internalError(n: UInt): GetrandomError = GetrandomError((1u shl 31) + n)
    }
}

/**
 * Fills [dest] with cryptographically-secure random bytes sourced from
 * the OS-provided RNG for the current target. Returns [GetrandomResult.Ok]
 * on success, or [GetrandomResult.Failure] carrying a
 * platform-specific [GetrandomError] if the kernel/OS call fails.
 *
 * Mirrors the upstream Rust `getrandom::getrandom`.
 */
public fun getrandom(dest: ByteArray): GetrandomResult = getrandomImpl(dest)

/** Per-target backend. Each Kotlin Multiplatform target supplies one `actual`. */
internal expect fun getrandomImpl(dest: ByteArray): GetrandomResult
