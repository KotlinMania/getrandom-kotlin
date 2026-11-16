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

    public data class Failure(
        public val error: GetrandomError,
    ) : GetrandomResult()

    public val isOk: Boolean get() = this is Ok

    /** Convenience: error message if this is a [Failure], otherwise `null`. */
    public val errorMessage: String? get() = (this as? Failure)?.error?.displayMessage
}
