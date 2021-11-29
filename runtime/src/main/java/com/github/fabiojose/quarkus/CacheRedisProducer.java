package com.github.fabiojose.quarkus;

import com.esotericsoftware.kryo.Kryo;

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

    @Produces
    @ApplicationScoped
    public Kryo kryoProducer() {
        return new Kryo();
    }
}
