// port-lint: source src/js.rs
package io.github.kotlinmania.getrandom

/**
 * Kotlin/JS backend. Upstream Rust's `js.rs` calls
 * `crypto.getRandomValues(buffer)` on the global `crypto` object
 * (browser Web Crypto API) and falls back to Node.js'
 * `crypto.randomFillSync` when running under Node. This port goes
 * through the same JS-level entry points via `js(...)`.
 *
 * `require('crypto')` is hidden from webpack's static scanner with
 * the `(new Function('return require'))()` trick (workspace
 * AGENTS.md §10) so the browser bundle doesn't try to bundle Node's
 * `crypto` module.
 */
internal actual fun getrandomImpl(dest: ByteArray): GetrandomResult {
    if (dest.isEmpty()) return GetrandomResult.Ok
    val bytes = jsFillBytes(dest.size) ?: return GetrandomResult.Failure(GetrandomError.WEB_CRYPTO)
    for (i in dest.indices) dest[i] = bytes[i]
    return GetrandomResult.Ok
}

private fun jsFillBytes(count: Int): ByteArray? {
    val arr = jsCryptoFill(count) ?: return null
    val out = ByteArray(count)
    for (i in 0 until count) {
        out[i] = jsArrayGet(arr, i).toByte()
    }
    return out
}

private fun jsCryptoFill(count: Int): dynamic = js(
    """(function(){
        try {
            var c = (typeof crypto !== 'undefined') ? crypto : null;
            if (c == null) {
                var rq = (new Function('return typeof require === "function" ? require : null'))();
                if (rq) { c = rq('crypto'); }
            }
            if (c == null) return null;
            var buf = new Uint8Array(count);
            if (typeof c.getRandomValues === 'function') { c.getRandomValues(buf); return buf; }
            if (typeof c.randomFillSync === 'function') { c.randomFillSync(buf); return buf; }
            return null;
        } catch (e) { return null; }
    })()""",
)

private fun jsArrayGet(arr: dynamic, i: Int): Int = js("arr[i]") as Int
