// port-lint: tests error.rs
package io.github.kotlinmania.getrandom

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ErrorTest {
    @Test
    fun testSize() {
        // In Rust, size of Error is 4 bytes.
        // In Kotlin, GetrandomError carries a 32-bit unsigned code.
        val err = GetrandomError(1u)
        assertEquals(1u, err.code)
        assertEquals(1, err.rawOsError)
    }

    @Test
    fun testErrorConstants() {
        assertEquals("getrandom: this target is not supported", GetrandomError.UNSUPPORTED.displayMessage)
        assertEquals("errno: did not return a positive value", GetrandomError.ERRNO_NOT_POSITIVE.displayMessage)
        assertEquals("unexpected situation", GetrandomError.UNEXPECTED.displayMessage)
        assertNotNull(GetrandomError.INTERNAL_START)
        assertNotNull(GetrandomError.CUSTOM_START)
    }
}
