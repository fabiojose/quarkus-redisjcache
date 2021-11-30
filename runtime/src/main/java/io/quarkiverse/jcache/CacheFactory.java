package io.quarkiverse.jcache;

import javax.cache.Cache;

public interface CacheFactory {

    Cache<Object, Object> getOrCreate(String cacheName);

}
