// port-lint: source error.rs
package io.github.kotlinmania.getrandom

/**
 * A small and compatible error type.
 *
 * Mirrors upstream Rust `getrandom::Error`. Non-`Throwable` — exposing
 * a `Throwable` subclass in commonMain leaks `Array<Any?>` into the
 * Swift Export bridge (workspace AGENTS.md §4).
 *
 * @property code Non-zero error code. Values below [INTERNAL_START] are
 *   raw OS errors (positive `Int` from errno, GetLastError, etc.).
 *   Values at or above [INTERNAL_START] are reserved internal sentinels
 *   like [UNSUPPORTED], [WEB_CRYPTO], etc.
 */
public data class GetrandomError(
    public val code: UInt,
) {
    /** Human-readable description of this error. */
    public val displayMessage: String
        get() = internalDesc(this) ?: "getrandom: OS error (code=$code)"

    /** Raw OS errno-style value, or `null` when this is an internal sentinel. */
    public val rawOsError: Int?
        get() = if (code < INTERNAL_START.code) code.toInt() else null

    public companion object {
        /** Threshold above which codes are internal sentinels (matches upstream Rust). */
        public val INTERNAL_START: GetrandomError = GetrandomError(1u shl 31)

        /** Threshold for custom user-defined errors. */
        public val CUSTOM_START: GetrandomError = GetrandomError((1u shl 31) + (1u shl 30))

        public val UNSUPPORTED: GetrandomError = internalError(0u)
        public val ERRNO_NOT_POSITIVE: GetrandomError = internalError(1u)
        public val UNEXPECTED: GetrandomError = internalError(2u)
        public val IOS_SEC_RANDOM: GetrandomError = internalError(3u)
        public val WINDOWS_RTL_GEN_RANDOM: GetrandomError = internalError(4u)
        public val FAILED_RDRAND: GetrandomError = internalError(5u)
        public val NO_RDRAND: GetrandomError = internalError(6u)
        public val WEB_CRYPTO: GetrandomError = internalError(7u)
        public val WEB_GET_RANDOM_VALUES: GetrandomError = internalError(8u)
        public val VXWORKS_RAND_SECURE: GetrandomError = internalError(11u)
        public val NODE_CRYPTO: GetrandomError = internalError(12u)
        public val NODE_RANDOM_FILL_SYNC: GetrandomError = internalError(13u)
        public val NODE_ES_MODULE: GetrandomError = internalError(14u)

        /** WASI-specific error sentinel. */
        public val WASI_RANDOM_GET: GetrandomError = internalError(15u)

        public fun internalError(n: UInt): GetrandomError =
            GetrandomError((1u shl 31) + n)

        public fun from(code: UInt): GetrandomError = GetrandomError(code)

        public fun internalDesc(error: GetrandomError): String? =
            when (error) {
                UNSUPPORTED -> "getrandom: this target is not supported"
                ERRNO_NOT_POSITIVE -> "errno: did not return a positive value"
                UNEXPECTED -> "unexpected situation"
                IOS_SEC_RANDOM -> "SecRandomCopyBytes: iOS Security framework failure"
                WINDOWS_RTL_GEN_RANDOM -> "RtlGenRandom: Windows system function failure"
                FAILED_RDRAND -> "RDRAND: failed multiple times: CPU issue likely"
                NO_RDRAND -> "RDRAND: instruction not supported"
                WEB_CRYPTO -> "Web Crypto API is unavailable"
                WEB_GET_RANDOM_VALUES -> "Calling Web API crypto.getRandomValues failed"
                VXWORKS_RAND_SECURE -> "randSecure: VxWorks RNG module is not initialized"
                NODE_CRYPTO -> "Node.js crypto CommonJS module is unavailable"
                NODE_RANDOM_FILL_SYNC -> "Calling Node.js API crypto.randomFillSync failed"
                NODE_ES_MODULE -> "Node.js ES modules are not directly supported"
                WASI_RANDOM_GET -> "WASI random_get failed"
                else -> null
            }
    }
}

public typealias Error = GetrandomError

