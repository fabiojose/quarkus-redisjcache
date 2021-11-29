package com.github.fabiojose.quarkus;

import com.esotericsoftware.kryo.Kryo;
import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Recorder;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Recorder
public class CacheRedisRecorder {

    private static final Logger log = LoggerFactory.getLogger(
        CacheRedisRecorder.class
    );

    @Inject
    CacheRedisConfig config;

    private void createCache(String cacheName, CacheManager manager) {
        Cache cache = manager.getCache(cacheName);
        if (null == cache) {
            //TODO: Configuration
            manager.createCache(cacheName, null);
        }
    }

    public void prepare(
        BeanContainer container,
        String cacheName,
        Class cacheValueType
    ) {
        log.info(
            "preparing cache {} and registering the {} for object serializing",
            cacheName,
            cacheValueType
        );

        //TODO: Obter cache manager
        //final CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

        final CacheManager cacheManager = container.instance(
            CacheManager.class
        );
        log.info("using cache manager {}", cacheManager);

        //TODO: Obter serializador
        final Kryo kryo = container.instance(Kryo.class);
        log.info("using java object serializer {}", kryo);

        //TODO: Criar e/ou obter cache

        //TODO: Registrar cacheValueType no kryo
        kryo.register(cacheValueType);

    }
}
