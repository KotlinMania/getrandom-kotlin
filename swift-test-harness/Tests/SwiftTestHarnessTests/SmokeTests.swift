import XCTest
import GetrandomLibrary

final class SmokeTests: XCTestCase {
    /// Smoke-tests the Swift Export bridge: fill a non-empty buffer with
    /// OS-provided random bytes and confirm the call returns success.
    /// Doesn't assert on the byte values themselves; the kernel RNG output
    /// isn't testable for content, only for "the call worked."
    func testGetRandomFillsBuffer() throws {
        let result = Getrandom.shared.fillBytes(count: Int32(32))
        XCTAssertNotNil(result, "getrandom returned nil")
        XCTAssertEqual(result?.count, 32)
    }
}
