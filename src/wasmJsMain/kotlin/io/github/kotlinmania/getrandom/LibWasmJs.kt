// port-lint: source src/js.rs
@file:OptIn(kotlin.js.ExperimentalWasmJsInterop::class)
package io.github.kotlinmania.getrandom

/**
 * Kotlin/Wasm-JS backend. Mirrors the `jsMain` path against the same
 * `crypto.getRandomValues` / `crypto.randomFillSync` surface,
 * differing only in how `js(...)` is shaped:
 *
 * - Return type is the typed `Boolean` (success flag) — the bridge
 *   can't express `dynamic`.
 * - Body is wrapped in `{ … }` because `js(…)` on Wasm compiles to
 *   `(args) => BODY` (workspace AGENTS.md §10 — `require` hidden from
 *   webpack via the `new Function` trick).
 *
 * The byte transfer goes through a series of single-element fetches
 * via `jsByteAt(...)` rather than a typed-array bridge, which keeps
 * us off the Wasm-JS interop surfaces that are still in flux.
 */
internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    val ok = jsCryptoBegin(dest.size)
    if (!ok) return GetrandomResult.Failure(GetrandomError.WEB_CRYPTO)
    for (i in dest.indices) dest[i] = jsByteAt(i).toByte()
    return GetrandomResult.Ok
}

private fun jsCryptoBegin(count: Int): Boolean = js(
    """{
        try {
            var c = (typeof crypto !== 'undefined') ? crypto : null;
            if (c == null) {
                var rq = (new Function('return typeof require === "function" ? require : null'))();
                if (rq) { c = rq('crypto'); }
            }
            if (c == null) return false;
            var buf = new Uint8Array(count);
            if (typeof c.getRandomValues === 'function') { c.getRandomValues(buf); }
            else if (typeof c.randomFillSync === 'function') { c.randomFillSync(buf); }
            else { return false; }
            globalThis.__getrandomKotlinScratch = buf;
            return true;
        } catch (e) { return false; }
    }""",
)

private fun jsByteAt(i: Int): Int = js("{ return globalThis.__getrandomKotlinScratch[i]; }")
