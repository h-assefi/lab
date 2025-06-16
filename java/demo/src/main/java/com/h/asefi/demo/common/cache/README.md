# Cache Module

The `cache` module provides a simple, flexible, and efficient caching abstraction for Java/Spring applications using Caffeine as the underlying cache provider. It enables you to configure multiple cache regions with different expiration policies, and provides utility services for cache management, eviction, and updates.

---

## Folder Structure

```
cache/
├── CacheConfig.java
├── CacheService.java
├── CacheStatics.java
└── README.md
```

---

## Component Overview

### 1. [`CacheConfig.java`](CacheConfig.java)
- Spring `@Configuration` class that sets up the cache manager and defines multiple Caffeine cache beans with different expiration policies:
  - **oneHourLiveCacheConfig**: 1 hour expiration (default for non-customized caches)
  - **oneDayLiveCacheConfig**: 1 day expiration
  - **oneWeekLiveCacheConfig**: 1 week expiration
- Registers these cache configurations with the `CaffeineCacheManager`, allowing you to use named caches with different lifetimes.

### 2. [`CacheService.java`](CacheService.java)
- Spring `@Service` that provides utility methods for cache management:
  - `evictCache(String cacheName)`: Evicts all entries from the specified cache.
  - `evictCache(String cacheName, String key)`: Evicts a specific entry by key from the specified cache.
  - `evictAllCaches()`: Clears all caches managed by the cache manager.
  - `updateCache(String cacheName, String key, Object newValue)`: Updates or inserts a value in the specified cache and key.

### 3. [`CacheStatics.java`](CacheStatics.java)
- Holds static string constants for cache names:
  - `oneHourLiveCache`
  - `oneDayLiveCache`
  - `oneWeekLiveCache`
- Use these constants to avoid typos and ensure consistency when referring to cache names in your code.

---

## How It Works

- The module uses Spring's caching abstraction with Caffeine as the backend.
- Multiple cache regions are configured with different expiration policies, suitable for various caching needs (short-lived, daily, weekly).
- The `CacheService` provides a simple API for cache eviction and updates, making it easy to manage cache entries programmatically.

---

## Usage

### 1. Add Dependencies

Ensure your project includes the following dependencies in your `pom.xml` or `build.gradle`:

- `spring-boot-starter-cache`
- `com.github.ben-manes.caffeine:caffeine`

### 2. Enable Caching

The `@EnableCaching` annotation in `CacheConfig.java` enables Spring's caching support.

### 3. Inject and Use the CacheService

You can inject `CacheService` into any Spring-managed bean and use its methods to manage cache entries:

```java
import com.h.asefi.demo.common.cache.CacheService;
import com.h.asefi.demo.common.cache.CacheStatics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBusinessService {

    private final CacheService cacheService;

    @Autowired
    public MyBusinessService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public void clearDailyCache() {
        cacheService.evictCache(CacheStatics.oneDayLiveCache);
    }

    public void updateWeeklyCache(String key, Object value) {
        cacheService.updateCache(CacheStatics.oneWeekLiveCache, key, value);
    }
}
```

### 4. Using Cache Names

Always use the constants from `CacheStatics.java` to refer to cache names:

```java
cacheService.evictCache(CacheStatics.oneHourLiveCache);
cacheService.evictCache(CacheStatics.oneDayLiveCache, "user:123");
```

### 5. Customizing Cache Expiration

To change expiration policies, modify the corresponding bean in `CacheConfig.java`:

```java
@Bean("oneHourLiveCacheConfig")
public Caffeine<Object, Object> oneHourLiveCacheConfig() {
    return Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES) // Change expiration as needed
            .maximumSize(1000);
}
```

---

## Summary

The cache module provides a ready-to-use, configurable caching solution for your Spring applications. It supports multiple cache regions with different lifetimes, and offers utility methods for cache management, making it easy to keep your application's data fresh and performant.