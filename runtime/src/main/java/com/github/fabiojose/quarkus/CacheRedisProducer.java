package com.github.fabiojose.quarkus;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CacheRedisProducer {

    @Produces
    @ApplicationScoped
    public CacheManager produceCacheManager() {
        return Caching.getCachingProvider().getCacheManager();
    }
}
