package io.quarkiverse.jcache.redis;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.quarkiverse.jcache.CacheCreationStrategy;
import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "cache.redis", phase = ConfigPhase.RUN_TIME)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedisConfig {

    /**
     * Threads amount shared across all listeners of RTopic object, invocation handlers of RRemoteService, RTopic object and
     * RExecutorService tasks.
     */
    @ConfigItem(defaultValue = "16")
    public Integer threads;

    /**
     * Threads amount shared between all internal redis clients used by Redisson.
     * <br/>
     * Netty threads used in Redis response decoding and command sending. <code>0 = cores_amount * 2</code>
     */
    @ConfigItem(defaultValue = "32")
    public Integer nettyThreads;

    /**
     * Transport mode
     */
    @ConfigItem(defaultValue = "NIO")
    public String transportMode;

    /**
     * Time-to-live to maintain entries per cache name.
     * <br/>
     * Values in milliseconds.
     */
    @ConfigItem
    @JsonIgnore
    public Map<String, Long> ttl;

    /**
     * Strategy to create and configure caches.
     */
    @ConfigItem(defaultValue = "ON_DEMAND")
    @JsonIgnore
    public CacheCreationStrategy creation;

    /**
     * Single instance configuration
     */
    public RedisSingleServerConfig singleServerConfig;

    /**
     * Cluster configuration
     */
    public RedisClusterServersConfig clusterServersConfig;

    public RedisSingleServerConfig getSingleServerConfig() {
        if (singleServerConfig.enabled) {
            return singleServerConfig;
        }

        return null;
    }

    public RedisClusterServersConfig getClusterServersConfig() {
        if (clusterServersConfig.enabled) {
            return clusterServersConfig;
        }

        return null;
    }
}
