// port-lint: source lazy.rs
package io.github.kotlinmania.getrandom

/**
 * Lazily initialized values mirroring upstream Rust `lazy.rs`.
 */
public class LazyUsize {
    private var value: Int = UNINIT

    public fun unsyncInit(init: () -> Int): Int {
        if (value == UNINIT) {
            value = init()
        }
        return value
    }

    public companion object {
        public const val UNINIT: Int = -1
    }
}

/**
 * Lazily initialized boolean flag mirroring upstream Rust `lazy.rs`.
 */
public class LazyBool {
    private var initialized: Boolean = false
    private var value: Boolean = false

    public fun unsyncInit(init: () -> Boolean): Boolean {
        if (!initialized) {
            value = init()
            initialized = true
        }
        return value
    }
}
