# getrandom-kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fgetrandom--kotlin-blue.svg)](https://github.com/KotlinMania/getrandom-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/getrandom-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/getrandom-kotlin)

Kotlin Multiplatform port of the
[`getrandom`](https://crates.io/crates/getrandom) Rust crate (version
`0.2.15`) — a small cross-platform library for retrieving
cryptographically-secure random bytes from the OS-provided RNG.

## Scope

`getrandom` mirrors the upstream Rust crate's single public function:

```rust
pub fn getrandom(dest: &mut [u8]) -> Result<(), Error>
```

becomes Kotlin:

```kotlin
public fun getrandom(dest: ByteArray): Result<Unit>
```

Each platform's `actual` delegates to the most appropriate OS-provided
entropy source:

| Source set       | Backend                                                       |
|------------------|---------------------------------------------------------------|
| `linuxMain`      | `getrandom(2)` syscall via `platform.posix.getrandom`         |
| `mingwMain`      | `BCryptGenRandom` via Windows CNG                             |
| `appleMain`      | `CCRandomGenerateBytes` from CommonCrypto                     |
| `androidNativeMain` | Bionic `getrandom(2)` syscall                              |
| `androidMain`    | `java.security.SecureRandom`                                  |
| `jvmMain`        | `java.security.SecureRandom`                                  |
| `wasmWasiMain`   | `random_get` from WASI preview1                               |
| `jsMain`         | `crypto.getRandomValues` (browser) / `crypto.randomFillSync` (node) |
| `wasmJsMain`     | same as `jsMain` via `js(...)`                                |

## Install

```kotlin
commonMain.dependencies {
    implementation("io.github.kotlinmania:getrandom-kotlin:0.1.0")
}
```

## Upstream

- Crate: [`getrandom 0.2.15`](https://crates.io/crates/getrandom/0.2.15)
- Source: [`rust-random/getrandom`](https://github.com/rust-random/getrandom)
- License: MIT OR Apache-2.0

## License

Dual-licensed under the MIT license and the Apache License, Version 2.0,
matching the upstream crate.
