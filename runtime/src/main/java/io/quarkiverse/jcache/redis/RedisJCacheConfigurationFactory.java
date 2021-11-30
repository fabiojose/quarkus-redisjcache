package io.quarkiverse.jcache.redis;

import java.io.IOException;
import java.io.UncheckedIOException;

import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkiverse.jcache.ExpiryPolicyFactory;
import io.quarkiverse.jcache.JCacheConfigurationFactory;

@Singleton
public class RedisJCacheConfigurationFactory implements JCacheConfigurationFactory {
    
    private static final Logger log = LoggerFactory.getLogger(
        RedisJCacheConfigurationFactory.class
    );
    
    @Inject
    RedisConfig redisConfig;

    private ObjectMapper yamlMapper;
    private ObjectMapper getYamlMapper() {
        if (null == yamlMapper) {
            yamlMapper = new ObjectMapper(new YAMLFactory());
        }

        return yamlMapper;
    }

    private Config redissonYamlConfiguration()
        throws IOException, JsonProcessingException {
        final String yaml = getYamlMapper().writeValueAsString(redisConfig);
        if (log.isDebugEnabled()) {
            log.info("loaded redisson yaml configuration \n{}", yaml);
        }

        return Config.fromYAML(yaml);
    }

    @Override
    public Configuration<Object, Object> create(String cacheName) {

        try {
            final Config cfg = redissonYamlConfiguration();
            final MutableConfiguration<Object, Object> mutable = new MutableConfiguration<>();

            mutable.setExpiryPolicyFactory(
                new ExpiryPolicyFactory(
                    redisConfig.ttl.get(cacheName), cacheName
                )
            );

            return RedissonConfiguration.fromConfig(cfg, mutable);

        }catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
