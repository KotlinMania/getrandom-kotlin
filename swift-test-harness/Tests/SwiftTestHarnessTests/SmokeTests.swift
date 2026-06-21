import XCTest
import GetRandom
import ExportedKotlinPackages

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
//
// The file's mere existence and successful compilation prove three layers
// of the pipeline:
//
//   1. `embedSwiftExportForXcode` produced `GetRandom.swiftmodule/`
//      and the supporting KotlinRuntimeSupport / ExportedKotlinPackages /
//      KotlinRuntime swiftmodule bundles.
//
//   2. The static archive `libGetRandom.a` supplied every symbol the
//      Swift modules reference.
//
//   3. The Kotlin `swiftExport { moduleName = "GetRandom" }` configuration
//      produced a valid module reachable from this Package.swift.
final class SmokeTests: XCTestCase {
    func testSwiftModuleLoads() throws {
        XCTAssertTrue(true, "GetRandom swift module imported cleanly")
    }

    func testGetrandomResultOk() throws {
        let ok = ExportedKotlinPackages.io.github.kotlinmania.getrandom.GetrandomResult.Ok.shared
        XCTAssertTrue(ok.isOk, "Ok should report isOk == true")
    }
}