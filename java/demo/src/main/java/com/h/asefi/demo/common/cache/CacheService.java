package com.h.asefi.demo.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Evict all keys of a cacheName
     *
     * @param cacheName String type. for simplicity use CacheStatics
     */
    public void evictCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            synchronized (cache) {
                cache.invalidate();
            }
        }
    }

    /**
     * Evict cache of a single associated with cacheName
     *
     * @param cacheName String type. for simplicity use CacheStatics
     * @param key
     */
    public void evictCache(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    /**
     * Evicts all cache entries.
     */
    public void evictAllCaches() {
        cacheManager.getCacheNames().forEach(cacheName -> {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
            }
        });
    }

    /**
     * Update value of a cache with specific name and specific key
     *
     * @param cacheName String type. for simplicity use CacheStatics
     * @param key
     * @param newValue  Object type.
     */
    public void updateCache(String cacheName, String key, Object newValue) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, newValue);
        }
    }
}
