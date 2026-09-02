// port-lint: source util.rs
package io.github.kotlinmania.getrandom

/**
 * Buffer and slice utilities mirroring upstream Rust `util.rs`.
 */
public object Util {
    /**
     * Fills the provided [buffer] with zero bytes.
     */
    public fun uninitSliceFillZero(buffer: ByteArray): ByteArray {
        buffer.fill(0)
        return buffer
    }

    /**
     * Slices the byte array as an initialized buffer.
     */
    public fun sliceAssumeInitMut(buffer: ByteArray): ByteArray = buffer

    /**
     * Returns the buffer viewed as uninitialized storage.
     */
    public fun sliceAsUninit(buffer: ByteArray): ByteArray = buffer

    /**
     * Returns the mutable buffer viewed as uninitialized storage.
     */
    public fun sliceAsUninitMut(buffer: ByteArray): ByteArray = buffer
}
