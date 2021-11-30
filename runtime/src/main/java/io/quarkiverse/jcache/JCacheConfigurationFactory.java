package io.quarkiverse.jcache;

import javax.cache.configuration.Configuration;

public interface JCacheConfigurationFactory {
    
    Configuration<Object, Object> create(String cacheName);

}
