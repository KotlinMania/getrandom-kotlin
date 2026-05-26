// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "SwiftTestHarness",
    dependencies: [
        .package(name: "Getrandom", path: "../build/SPMPackage/macosArm64/Debug")
    ],
    targets: [
        .testTarget(
            name: "SwiftTestHarnessTests",
            dependencies: [
                .product(name: "GetrandomLibrary", package: "Getrandom")
            ],
            linkerSettings: [
                .unsafeFlags([
                    "-L", "../build/swift-test",
                    "-lGetrandom",
                ]),
            ]
        ),
    ]
)
