# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 24/24 (100.0%)
- **Function parity:** 33/58 matched (target 37) — 56.9%
- **Class/type parity:** 2/8 matched (target 28) — 25.0%
- **Combined symbol parity:** 35/66 matched (target 65) — 53.0%
- **Average inline-code cosine:** 0.06 (function body across 24 matched files)
- **Average documentation cosine:** 0.42 (doc text across 24 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 24 files with <0.60 function similarity
- **Needs Review:** 0 files with 0.60-0.84 function similarity
- **Excellent:** 0 files with >=0.85 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

### 1. error
- **Similarity:** 0.08 (needs 77% improvement)
- **Dependencies:** 11
- **Priority Score:** 11050909.0
- **Functions:** 4/8 matched (target 5)
- **Missing functions:** `raw_os_error`, `code`, `fmt`, `fmt`
- **Types:** 0/1 matched (target 2)
- **Missing types:** `Error`
- **Symbol Deficit:** 5 (functions: 4, types: 1)
- **Action:** Deep review - likely missing major functionality

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. error

- **Target:** `getrandom.Error`
- **Similarity:** 0.08
- **Dependents:** 11
- **Priority Score:** 11050909.0
- **Functions:** 4/8 matched (target 5)
- **Missing functions:** `raw_os_error`, `code`, `fmt`, `fmt`
- **Types:** 0/1 matched (target 2)
- **Missing types:** `Error`
- **Tests:** 1/1 matched

### 2. use_file

- **Target:** `getrandom.UseFile`
- **Similarity:** 0.01
- **Dependents:** 0
- **Priority Score:** 91009.9
- **Functions:** 1/8 matched (target 1)
- **Missing functions:** `get_rng_fd`, `get_fd`, `wait_until_rng_ready`, `new`, `lock`, `unlock`, `drop`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Mutex`, `DropGuard`

### 3. util_libc

- **Target:** `getrandom.UtilLibc`
- **Similarity:** 0.01
- **Dependents:** 0
- **Priority Score:** 50709.9
- **Functions:** 2/6 matched (target 2)
- **Missing functions:** `new`, `ptr`, `open_readonly`, `getrandom_syscall`
- **Types:** 0/1 matched
- **Missing types:** `Weak`

### 4. rdrand

- **Target:** `getrandom.Rdrand`
- **Similarity:** 0.01
- **Dependents:** 0
- **Priority Score:** 40509.9
- **Functions:** 1/5 matched (target 1)
- **Missing functions:** `rdrand`, `self_test`, `is_rdrand_good`, `rdrand_exact`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. js

- **Target:** `getrandom.Js`
- **Similarity:** 0.01
- **Dependents:** 0
- **Priority Score:** 30409.9
- **Functions:** 1/3 matched (target 1)
- **Missing functions:** `getrandom_init`, `is_node`
- **Types:** 0/1 matched
- **Missing types:** `RngSource`

### 6. lazy

- **Target:** `getrandom.Lazy`
- **Similarity:** 0.04
- **Dependents:** 0
- **Priority Score:** 20609.6
- **Functions:** 2/4 matched (target 2)
- **Missing functions:** `new`, `new`
- **Types:** 2/2 matched
- **Missing types:** _none_

### 7. netbsd

- **Target:** `getrandom.Netbsd`
- **Similarity:** 0.02
- **Dependents:** 0
- **Priority Score:** 20309.8
- **Functions:** 1/2 matched (target 1)
- **Missing functions:** `kern_arnd`
- **Types:** 0/1 matched
- **Missing types:** `GetRandomFn`

### 8. error_impls

- **Target:** `getrandom.ErrorImpls`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10110.0
- **Functions:** 0/1 matched (target 2)
- **Missing functions:** `from`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 9. util

- **Target:** `getrandom.Util`
- **Similarity:** 0.02
- **Dependents:** 0
- **Priority Score:** 409.8
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 10. linux_android_with_fallback

- **Target:** `getrandom.LinuxAndroidWithFallback`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 208.7
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 11. lib

- **Target:** `getrandom.Lib`
- **Similarity:** 0.21
- **Dependents:** 0
- **Priority Score:** 207.9
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 3)
- **Missing types:** _none_

### 12. windows

- **Target:** `getrandom.Windows`
- **Similarity:** 0.02
- **Dependents:** 0
- **Priority Score:** 109.8
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 13. vxworks

- **Target:** `getrandom.Vxworks`
- **Similarity:** 0.03
- **Dependents:** 0
- **Priority Score:** 109.7
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 14. wasi

- **Target:** `getrandom.Wasi`
- **Similarity:** 0.04
- **Dependents:** 0
- **Priority Score:** 109.6
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 15. solaris

- **Target:** `getrandom.Solaris`
- **Similarity:** 0.05
- **Dependents:** 0
- **Priority Score:** 109.5
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 16. hermit

- **Target:** `getrandom.Hermit`
- **Similarity:** 0.06
- **Dependents:** 0
- **Priority Score:** 109.4
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 17. custom

- **Target:** `getrandom.Custom`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 109.3
- **Functions:** 1/1 matched (target 2)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 18. getrandom

- **Target:** `getrandom.GetrandomMod`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 109.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 19. solid

- **Target:** `getrandom.Solid`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 109.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 20. linux_android

- **Target:** `getrandom.LinuxAndroid`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 109.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 21. getentropy

- **Target:** `getrandom.Getentropy`
- **Similarity:** 0.07
- **Dependents:** 0
- **Priority Score:** 109.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 22. apple-other

- **Target:** `getrandom.AppleOther`
- **Similarity:** 0.08
- **Dependents:** 0
- **Priority Score:** 109.2
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 23. espidf

- **Target:** `getrandom.Espidf`
- **Similarity:** 0.15
- **Dependents:** 0
- **Priority Score:** 108.5
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 24. fuchsia

- **Target:** `getrandom.Fuchsia`
- **Similarity:** 0.15
- **Dependents:** 0
- **Priority Score:** 108.5
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

