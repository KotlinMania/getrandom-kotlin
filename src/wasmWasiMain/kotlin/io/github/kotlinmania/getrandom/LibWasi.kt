// port-lint: source src/wasi.rs
@file:OptIn(kotlin.wasm.unsafe.UnsafeWasmMemoryApi::class, kotlin.wasm.ExperimentalWasmInterop::class)
package io.github.kotlinmania.getrandom

import kotlin.wasm.WasmImport
import kotlin.wasm.unsafe.withScopedMemoryAllocator

/**
 * WASI backend. The upstream Rust `src/wasi.rs` calls
 * `wasi_snapshot_preview1::random_get(buf, buf_len) -> errno`.
 * Wire the same ABI through `@WasmImport`. The WASI host fills
 * the buffer with bytes the same way `getrandom(2)` would on Linux
 * (cryptographically-strong RNG sourced by the runtime).
 */
@WasmImport("wasi_snapshot_preview1", "random_get")
private external fun wasiRandomGet(buf: Int, bufLen: Int): Int

internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    return withScopedMemoryAllocator { allocator ->
        val ptr = allocator.allocate(dest.size).address.toInt()
        val errno = wasiRandomGet(ptr, dest.size)
        if (errno != 0) return@withScopedMemoryAllocator GetrandomResult.Failure(GetrandomError.WASI_RANDOM_GET)
        for (i in dest.indices) {
            dest[i] = (kotlin.wasm.unsafe.Pointer(ptr.toUInt()) + i).loadByte()
        }
        GetrandomResult.Ok
    }
}

private operator fun kotlin.wasm.unsafe.Pointer.plus(offset: Int): kotlin.wasm.unsafe.Pointer =
    kotlin.wasm.unsafe.Pointer((this.address + offset.toUInt()))
