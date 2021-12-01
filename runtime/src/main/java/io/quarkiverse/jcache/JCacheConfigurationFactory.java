package io.quarkiverse.jcache;

import javax.cache.configuration.Configuration;

/**
 * @author fabiojose@gmail.com
 */
public interface JCacheConfigurationFactory {

    Configuration<Object, Object> create(String cacheName);

}
