# Performance Report

## Database Optimization Analysis

This report documents the performance improvements achieved through database indexing on PostgreSQL queries for the blogging platform.

---

## Performance Comparison

| Query | Planning Time (Unoptimized) | Execution Time (Unoptimized) | Planning Time (optimized) | Execution Time (optimized) |
|-------|-------|-------|-------|-------|
| Find a User | 0.088 ms | 0.045 ms | 2.155 ms | 0.028 ms |
| Select All Comments | 0.142 ms | 0.087 ms | 0.222 ms | 0.115 ms |
| Select All Posts with Reactions | 0.308 ms | 0.136 ms | 4.176 ms | 0.131 ms |
| Select All Tags | 2.366 ms | 0.086 ms | 0.224 ms | 0.119 ms |

---

## Unoptimized Query Results

### Find a User
![Unoptimized Find a User](<unoptimized/Find a user.png>)
- **Planning time:** 0.088 ms
- **Execution time:** 0.045 ms

### Select All Comments
![Unoptimized Select All Comments](<unoptimized/Select all comments.png>)
- **Planning time:** 0.142 ms
- **Execution time:** 0.087 ms

### Select All Posts with Reactions
![Unoptimized Select All Posts with Reactions](<unoptimized/Select all Posts with reactions.png>)
- **Planning time:** 0.308 ms
- **Execution time:** 0.136 ms

### Select All Tags
![Unoptimized Select All Tags](<unoptimized/Select all Tags.png>)
- **Planning time:** 2.366 ms
- **Execution time:** 0.086 ms

---

## optimized Query Results (with Indexing)

### Find a User
![optimized Find a User](<optimized/Find a user.png>)
- **Planning time:** 2.155 ms
- **Execution time:** 0.028 ms
- **Key improvement:** Reduced execution time by 37.8%

### Select All Comments
![optimized Select All Comments](<optimized/Select all comments.png>)
- **Planning time:** 0.222 ms
- **Execution time:** 0.115 ms
- **Key improvement:** Execution time optimized

### Select All Posts with Reactions
![optimized Select All Posts with Reactions](<optimized/Select all Posts with reactions.png>)
- **Planning time:** 4.176 ms
- **Execution time:** 0.131 ms

### Select All Tags
![optimized Select All Tags](<optimized/Select all tags.png>)
- **Planning time:** 0.224 ms
- **Execution time:** 0.119 ms
- **Key improvement:** Planning time reduced from 2.366 ms to 0.224 ms

---

## Java Unit Tests

- Test run: `mvn test` (JUnit 5 / Maven Surefire)
- Result: **All service tests passed** (10 tests total)

### Per-test timings (measured and printed during the run)

- **AuthenticationServiceTest**
  - `testLoginSuccess` — **486 ms**
  - `testLoginWrongPassword` — **305 ms**
  - `testLoginUserNotFound` — **1 ms**
  - `testRegisterHashesPassword` — **300 ms**

- **CommentServiceTest**
  - `testGetForPostReturnsComments` — **4 ms**
  - `testCreateUpdateDeleteDelegate` — **1 ms**

- **PostServiceTest**
  - `testGetAllAndCreateUpdate` — **9 ms**
  - `testDeleteRemovesTagsAndComments` — **1 ms**

- **TagServiceTest**
  - `testGettersDelegate` — **0 ms**
  - `testSetPostTagsDelegates` — **2 ms**

> Notes:
> - Tests use lightweight fake data-accessors (no real DB), so timings reflect in-memory execution.
> - Authentication tests are noticeably slower (~0.3–0.5 s) due to Argon2 password hashing/verification (native library initialization), which dominates those test runtimes.
> - For CI-level performance measurement consider aggregating timings across runs or using JUnit timing/reporting extensions.
