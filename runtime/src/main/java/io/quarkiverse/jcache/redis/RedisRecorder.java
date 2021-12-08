package io.quarkiverse.jcache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkiverse.jcache.CacheCreationStrategy;
import io.quarkiverse.jcache.CacheFactory;
import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class RedisRecorder {

    private static final Logger log = LoggerFactory.getLogger(
            RedisRecorder.class);

    public void prepare(
            BeanContainer container,
            String cacheName) {

        final RedisConfig config = container.instance(RedisConfig.class);

        if (CacheCreationStrategy.ON_STARTUP.equals(config.creation)) {
            final CacheFactory factory = container.instance(CacheFactory.class);

            log.debug(
                    "creating and configuring cache {}",
                    cacheName);

            factory.getOrCreate(cacheName);
        }

    }
}
