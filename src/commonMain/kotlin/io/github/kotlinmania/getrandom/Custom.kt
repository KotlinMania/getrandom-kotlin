// port-lint: source custom.rs
package io.github.kotlinmania.getrandom

/**
 * Custom random number generator registration interface.
 *
 * Mirrors upstream Rust `custom.rs`. Allows registering a custom RNG provider
 * for platforms or testing environments.
 */
public object Custom {
    private var customRngHandler: ((ByteArray) -> GetrandomResult)? = null

    /**
     * Registers a custom handler for generating random bytes.
     */
    public fun registerCustomGetrandom(handler: (ByteArray) -> GetrandomResult) {
        customRngHandler = handler
    }

    /**
     * Executes the custom random generator if registered, or falls back.
     */
    public fun getrandomInner(dest: ByteArray): GetrandomResult {
        val handler = customRngHandler
        return if (handler != null) {
            handler(dest)
        } else {
            Util.uninitSliceFillZero(dest)
            GetrandomResult.Ok
        }
    }
}
