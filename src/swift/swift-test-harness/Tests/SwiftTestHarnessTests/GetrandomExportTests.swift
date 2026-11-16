#if canImport(Testing)
import Testing
import Getrandom

@Suite("Getrandom Swift Export Tests")
struct GetrandomExportTests {
    @Test("Fill 32-byte buffer and handle empty buffer")
    func testGetrandomFillsBuffer() {
        let empty = ExportedKotlinPackages.kotlin.ByteArray(size: 0)
        let emptyRes = getrandom(dest: empty)
        #expect(emptyRes.isOk)
        #expect(emptyRes.errorMessage == nil)

        let buf = ExportedKotlinPackages.kotlin.ByteArray(size: 32)
        let res = getrandom(dest: buf)
        #expect(res.isOk)
        #expect(res.errorMessage == nil)
        #expect(buf.size == 32)

        var hasNonZero = false
        for i in Int32(0) ..< 32 {
            if buf[i] != 0 {
                hasNonZero = true
                break
            }
        }
        #expect(hasNonZero)
    }
}
#elseif canImport(XCTest)
import XCTest
import Getrandom

final class GetrandomExportTests: XCTestCase {
    func testGetrandomFillsBuffer() {
        let empty = ExportedKotlinPackages.kotlin.ByteArray(size: 0)
        let emptyRes = getrandom(dest: empty)
        XCTAssertTrue(emptyRes.isOk)
        XCTAssertNil(emptyRes.errorMessage)

        let buf = ExportedKotlinPackages.kotlin.ByteArray(size: 32)
        let res = getrandom(dest: buf)
        XCTAssertTrue(res.isOk)
        XCTAssertNil(res.errorMessage)
        XCTAssertEqual(buf.size, 32)

        var hasNonZero = false
        for i in Int32(0) ..< 32 {
            if buf[i] != 0 {
                hasNonZero = true
                break
            }
        }
        XCTAssertTrue(hasNonZero)
    }
}
#endif
