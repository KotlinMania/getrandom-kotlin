# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 24/24 (100.0%)
- **Function parity:** 32/55 matched (target 37) — 58.2%
- **Class/type parity:** 3/8 matched (target 29) — 37.5%
- **Combined symbol parity:** 35/63 matched (target 66) — 55.6%
- **Average inline-code cosine:** 0.36 (function body across 24 matched files)
- **Average documentation cosine:** 0.42 (doc text across 24 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 20 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

### 1. error
- **Similarity:** 0.20 (needs 65% improvement)
- **Dependencies:** 11
- **Priority Score:** 11030808.0
- **Functions:** 4/7 matched (target 5)
- **Missing functions:** `raw_os_error`, `code`, `fmt`
- **Types:** 1/1 matched (target 3)
- **Missing types:** _none_
- **Symbol Deficit:** 3 (functions: 3, types: 0)
- **Action:** Deep review - likely missing major functionality

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. error

- **Target:** `getrandom.Error`
- **Similarity:** 0.20
- **Dependents:** 11
- **Priority Score:** 11030808.0
- **Functions:** 4/7 matched (target 5)
- **Missing functions:** `raw_os_error`, `code`, `fmt`
- **Types:** 1/1 matched (target 3)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. use_file

- **Target:** `getrandom.UseFile`
- **Similarity:** 0.05
- **Dependents:** 0
- **Priority Score:** 91009.5
- **Functions:** 1/8 matched (target 1)
- **Missing functions:** `get_rng_fd`, `get_fd`, `wait_until_rng_ready`, `new`, `lock`, `unlock`, `drop`
- **Types:** 0/2 matched (target 1)
- **Missing types:** `Mutex`, `DropGuard`

### 3. util_libc

- **Target:** `getrandom.UtilLibc`
- **Similarity:** 0.11
- **Dependents:** 0
- **Priority Score:** 50708.9
- **Functions:** 2/6 matched (target 2)
- **Missing functions:** `new`, `ptr`, `open_readonly`, `getrandom_syscall`
- **Types:** 0/1 matched
- **Missing types:** `Weak`

### 4. rdrand

- **Target:** `getrandom.Rdrand`
- **Similarity:** 0.09
- **Dependents:** 0
- **Priority Score:** 40509.1
- **Functions:** 1/5 matched (target 1)
- **Missing functions:** `rdrand`, `self_test`, `is_rdrand_good`, `rdrand_exact`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. js

- **Target:** `getrandom.Js`
- **Similarity:** 0.13
- **Dependents:** 0
- **Priority Score:** 30408.7
- **Functions:** 1/3 matched (target 1)
- **Missing functions:** `getrandom_init`, `is_node`
- **Types:** 0/1 matched
- **Missing types:** `RngSource`

### 6. netbsd

- **Target:** `getrandom.Netbsd`
- **Similarity:** 0.23
- **Dependents:** 0
- **Priority Score:** 20307.7
- **Functions:** 1/2 matched (target 1)
- **Missing functions:** `kern_arnd`
- **Types:** 0/1 matched
- **Missing types:** `GetRandomFn`

### 7. lazy

- **Target:** `getrandom.Lazy`
- **Similarity:** 0.23
- **Dependents:** 0
- **Priority Score:** 10407.7
- **Functions:** 1/2 matched
- **Missing functions:** `new`
- **Types:** 2/2 matched
- **Missing types:** _none_

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
- **Similarity:** 0.19
- **Dependents:** 0
- **Priority Score:** 408.1
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 10. linux_android_with_fallback

- **Target:** `getrandom.LinuxAndroidWithFallback`
- **Similarity:** 0.31
- **Dependents:** 0
- **Priority Score:** 206.9
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 11. lib

- **Target:** `getrandom.Lib`
- **Similarity:** 0.72
- **Dependents:** 0
- **Priority Score:** 202.8
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 3)
- **Missing types:** _none_

### 12. windows

- **Target:** `getrandom.Windows`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 106.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 13. vxworks

- **Target:** `getrandom.Vxworks`
- **Similarity:** 0.38
- **Dependents:** 0
- **Priority Score:** 106.2
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 14. getrandom

- **Target:** `getrandom.GetrandomMod`
- **Similarity:** 0.39
- **Dependents:** 0
- **Priority Score:** 106.1
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 15. solaris

- **Target:** `getrandom.Solaris`
- **Similarity:** 0.41
- **Dependents:** 0
- **Priority Score:** 105.9
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 16. linux_android

- **Target:** `getrandom.LinuxAndroid`
- **Similarity:** 0.43
- **Dependents:** 0
- **Priority Score:** 105.7
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 17. getentropy

- **Target:** `getrandom.Getentropy`
- **Similarity:** 0.45
- **Dependents:** 0
- **Priority Score:** 105.5
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 18. wasi

- **Target:** `getrandom.Wasi`
- **Similarity:** 0.47
- **Dependents:** 0
- **Priority Score:** 105.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 19. solid

- **Target:** `getrandom.Solid`
- **Similarity:** 0.53
- **Dependents:** 0
- **Priority Score:** 104.7
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 20. hermit

- **Target:** `getrandom.Hermit`
- **Similarity:** 0.56
- **Dependents:** 0
- **Priority Score:** 104.4
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 21. apple-other

- **Target:** `getrandom.AppleOther`
- **Similarity:** 0.57
- **Dependents:** 0
- **Priority Score:** 104.3
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 22. custom

- **Target:** `getrandom.Custom`
- **Similarity:** 0.61
- **Dependents:** 0
- **Priority Score:** 103.9
- **Functions:** 1/1 matched (target 2)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 23. espidf

- **Target:** `getrandom.Espidf`
- **Similarity:** 0.63
- **Dependents:** 0
- **Priority Score:** 103.7
- **Functions:** 1/1 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 24. fuchsia

- **Target:** `getrandom.Fuchsia`
- **Similarity:** 0.63
- **Dependents:** 0
- **Priority Score:** 103.7
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

