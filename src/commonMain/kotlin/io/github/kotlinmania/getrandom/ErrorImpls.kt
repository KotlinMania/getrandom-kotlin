// port-lint: source error_impls.rs
package io.github.kotlinmania.getrandom

/**
 * Standard error conversions and integrations for [GetrandomError].
 *
 * In upstream Rust, this module provides `From<Error> for std::io::Error`
 * and implements `std::error::Error`. In Kotlin Multiplatform, we provide
 * conversion to platform exception descriptions and error inspection helpers.
 */
public object ErrorImpls {
    /**
     * Converts a [GetrandomError] to a descriptive string for standard logging.
     */
    public fun toStandardMessage(error: GetrandomError): String =
        error.displayMessage

    /**
     * Checks whether the error corresponds to a raw OS error code.
     */
    public fun hasRawOsError(error: GetrandomError): Boolean =
        error.rawOsError != null
}
