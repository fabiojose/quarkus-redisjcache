package com.github.fabiojose.quarkus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class CacheRedisSingleServerConfig {

    /**
     * To enable single server configuration.
     */
    @ConfigItem(defaultValue = "false")
    @JsonIgnore
    public Boolean enabled;

    /**
     * If pooled connection not used for a timeout time and current connections amount bigger than minimum idle connections pool size, then it will closed and removed from pool.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "10000")
    public Integer idleConnectionTimeout;

    /**
     * Timeout during connecting to any Redis server.
     */
    @ConfigItem(defaultValue = "10000")
    public Integer connectTimeout;

    /**
     * Redis server response timeout.
     * <br/>
     * Starts to countdown when Redis command was succesfully sent.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "3000")
    public Integer timeout;

    /**
     * Error will be thrown if Redis command can't be sended to Redis server after <code>retryAttempts</code>.
     * <br/>
     * But if it sent succesfully then <code>timeout</code> will be started.
     */
    @ConfigItem(defaultValue = "3")
    public Integer retryAttempts;

    /**
     * Time interval after which another one attempt to send Redis command will be executed.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "1500")
    public Integer retryInterval;

    /**
     * Password for Redis server authentication.
     */
    @ConfigItem(defaultValue = "null")
    String password = null;

    /**
     * Subscriptions per Redis connection limit.
     */
    @ConfigItem(defaultValue = "5")
    public Integer subscriptionsPerConnection;

    /**
     * Name of client connection.
     */
    @ConfigItem(defaultValue = "null")
    String clientName = null;

    /**
     * Redis server address in <code>host:port</code> format. Use <code>rediss://</code> protocol for SSL connection.
     */
    @ConfigItem(defaultValue = "redis://127.0.0.1:6379")
    public String address;

    /**
     * Minimum idle Redis subscription connection amount.
     */
    @ConfigItem(defaultValue = "1")
    public Integer subscriptionConnectionMinimumIdleSize;

    /**
     * Redis subscription connection maximum pool size.
     */
    @ConfigItem(defaultValue = "50")
    public Integer subscriptionConnectionPoolSize;

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

    public String getPassword() {
        return  ("null".equals(password) ? null : password);
    }

    public String getClientName() {
        return ("null".equals(clientName) ? null : clientName);
    }
}
