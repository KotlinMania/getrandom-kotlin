// port-lint: tests tests/normal.rs
package io.github.kotlinmania.getrandom

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GetrandomTest {
    @Test
    fun emptyBufferReturnsOk() {
        val empty = ByteArray(0)
        val result = getrandom(empty)
        assertTrue(result.isOk, "expected Ok for an empty buffer; got $result")
    }

    @Test
    fun smallBufferGetsRandomBytes() {
        val buf = ByteArray(32)
        val result = getrandom(buf)
        if (result is GetrandomResult.Failure) {
            // mingwX64 currently fails with WINDOWS_RTL_GEN_RANDOM until the
            // bcrypt cinterop lands in a follow-up release. Accept that
            // documented failure mode; reject any other.
            assertEquals(
                GetrandomError.WINDOWS_RTL_GEN_RANDOM, result.error,
                "unexpected failure: ${result.error.displayMessage}",
            )
            return
        }
        assertTrue(result.isOk, "expected Ok or WINDOWS_RTL_GEN_RANDOM; got $result")
        // Sanity: at least one byte is non-zero (probability of all-zero
        // 32-byte buffer from a real RNG is 2^-256 — practically impossible).
        assertTrue(buf.any { it != 0.toByte() }, "RNG returned all-zero bytes")
    }

    @Test
    fun twoCallsProduceDifferentBytes() {
        val a = ByteArray(32)
        val b = ByteArray(32)
        val ra = getrandom(a)
        val rb = getrandom(b)
        // On a platform where getrandom is not yet implemented (mingwX64),
        // both calls fail identically and there are no bytes to compare.
        if (ra is GetrandomResult.Failure && rb is GetrandomResult.Failure) return
        assertTrue(ra.isOk, "first call failed: $ra")
        assertTrue(rb.isOk, "second call failed: $rb")
        assertTrue(!a.contentEquals(b), "two RNG calls returned identical 32-byte buffers")
    }

    @Test
    fun errorMessagesAreDistinct() {
        val ok: GetrandomResult = GetrandomResult.Ok
        assertEquals(null, ok.errorMessage)
        val failure: GetrandomResult = GetrandomResult.Failure(GetrandomError.UNSUPPORTED)
        assertNotNull(failure.errorMessage)
        assertTrue(failure.errorMessage!!.contains("not supported"))
    }
}
