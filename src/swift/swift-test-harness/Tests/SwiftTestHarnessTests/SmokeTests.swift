#if canImport(Testing)
import Testing
import Getrandom

@Suite("Getrandom Swift Export Suite")
struct SmokeTests {
    @Test("Swift module loads cleanly")
    func swiftModuleLoads() {
        #expect(Bool(true), "Getrandom swift module imported cleanly")
    }
}
#elseif canImport(XCTest)
import XCTest
import Getrandom

final class SmokeTests: XCTestCase {
    func testSwiftModuleLoads() {
        XCTAssertTrue(true, "Getrandom swift module imported cleanly")
    }
}
#endif