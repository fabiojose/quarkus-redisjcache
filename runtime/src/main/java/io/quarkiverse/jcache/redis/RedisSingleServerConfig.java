package io.quarkiverse.jcache.redis;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

/**
 * This type follow the definition of Redission for single nome configuration: https://github.com/redisson/redisson/wiki/2.-Configuration#262-single-instance-yaml-config-format
 * @author fabiojose@gmail.com
 */
@ConfigGroup
public class RedisSingleServerConfig extends AbstractRedisServerConfig {

    /**
     * Redis server address in <code>host:port</code> format. Use <code>rediss://</code> protocol for SSL connection.
     */
    @ConfigItem(defaultValue = "redis://127.0.0.1:6379")
    public String address;

    /**
     * Minimum idle Redis connection amount.
     */
    @ConfigItem(defaultValue = "24")
    public Integer connectionMinimumIdleSize;

    /**
     * Redis connection maximum pool size.
     */
    @ConfigItem(defaultValue = "64")
    public Integer connectionPoolSize;

    /**
     * Database index used for Redis connection.
     */
    @ConfigItem(defaultValue = "0")
    public Integer database;

    /**
     * DNS change monitoring interval.
     * <br/>
     * Applications must ensure the JVM DNS cache TTL is low enough to support this.
     * <br/>
     * Set <code>-1</code> to disable.
     */
    @ConfigItem(defaultValue = "5000")
    public Integer dnsMonitoringInterval;

}
