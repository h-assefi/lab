package com.h.asefi.demo.common.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean("oneHourLiveCacheConfig")
    public Caffeine<Object, Object> oneHourLiveCacheConfig() {
        // Default expiration for not customized caches
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .maximumSize(1000);
    }

    @Bean("oneDayLiveCacheConfig")
    public Caffeine<Object, Object> oneDayLiveCacheConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)// 1-day expiration for widgetProperties cache
                .maximumSize(1000);
    }

    @Bean("oneWeekLiveCacheConfig")
    public Caffeine<Object, Object> oneWeekLiveCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(7, TimeUnit.DAYS) // 1-week expiration for cache
                .maximumSize(1000);
    }

    @Bean
    public CacheManager cacheManager(@Qualifier("oneHourLiveCacheConfig") Caffeine<Object, Object> oneHourLiveCacheConfig,
                                     @Qualifier("oneDayLiveCacheConfig") Caffeine<Object, Object> oneDayLiveCacheConfig,
                                     @Qualifier("oneWeekLiveCacheConfig") Caffeine<Object, Object> oneWeekLiveCacheConfig
    ) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.setCaffeine(oneHourLiveCacheConfig); //default
        cacheManager.registerCustomCache(CacheStatics.oneWeekLiveCache, oneWeekLiveCacheConfig.build());
        cacheManager.registerCustomCache(CacheStatics.oneDayLiveCache, oneDayLiveCacheConfig.build());

        return cacheManager;
    }
}
