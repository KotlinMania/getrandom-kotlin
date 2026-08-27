#if canImport(Testing)
import Testing
import GetRandom

@Suite("GetRandom Swift Export Suite")
struct SmokeTests {
    @Test("Swift module loads cleanly")
    func swiftModuleLoads() {
        #expect(Bool(true), "GetRandom swift module imported cleanly")
    }
}
#elseif canImport(XCTest)
import XCTest
import GetRandom

final class SmokeTests: XCTestCase {
    func testSwiftModuleLoads() {
        XCTAssertTrue(true, "GetRandom swift module imported cleanly")
    }
}
#endif