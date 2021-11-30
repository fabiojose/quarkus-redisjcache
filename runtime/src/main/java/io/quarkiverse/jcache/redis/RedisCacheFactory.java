package io.quarkiverse.jcache.redis;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkiverse.jcache.CacheFactory;

/**
 * @author fabiojose@gmail.com
 */
@Singleton
public class RedisCacheFactory implements CacheFactory {
    
    private static final Logger log = LoggerFactory.getLogger(
        RedisCacheFactory.class
    );

    @Inject
    RedisConfig redisConfig;

    @Inject
    RedisJCacheConfigurationFactory configFactory;

    @Inject
    CacheManager cacheManager;

    @Override
    public Cache<Object, Object> getOrCreate(String cacheName) {

        var result = cacheManager.getCache(cacheName);
        if(null== result) {
            log.debug("cache {} not found, creating . . .", cacheName);
            result = cacheManager.createCache(cacheName, configFactory.create(cacheName));
            log.debug("cache {} created", cacheName);
        } else {
            log.debug("cache {} already created and configured.");
        }

        return result;
    }
    
}
