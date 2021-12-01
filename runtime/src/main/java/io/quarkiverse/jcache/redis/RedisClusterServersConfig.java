package io.quarkiverse.jcache.redis;

import java.util.List;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

/**
 * This type follow the definition of Redission for single nome configuration:
 * https://github.com/redisson/redisson/wiki/2.-Configuration#241-cluster-settings
 * 
 * @author fabiojose@gmail.com
 */
@ConfigGroup
public class RedisClusterServersConfig extends AbstractRedisServerConfig {

    /**
     * Enables cluster slots check during Redisson startup.
     */
    @ConfigItem(defaultValue = "false")
    public Boolean checkSlotsCoverage;

    /**
     * Interval of Redis Slave reconnection attempt when it was excluded from internal list of available servers. On each
     * timeout event Redisson tries to connect to disconnected Redis server.
     * <br>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "3000")
    public Long failedSlaveReconnectionInterval;

    /**
     * Redis Slave node failing to execute commands is excluded from the internal list of available nodes when the time interval
     * from the moment of first Redis command execution failure on this server reaches defined value.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "60000")
    public Long failedSlaveCheckInterval;

    /**
     * Redis 'slave' node minimum idle connection amount for each slave node.
     */
    @ConfigItem(defaultValue = "24")
    public Integer slaveConnectionMinimumIdleSize;

    /**
     * Redis 'slave' node maximum connection pool size for each slave node.
     */
    @ConfigItem(defaultValue = "64")
    public Integer slaveConnectionPoolSize;

    /**
     * Redis 'master' node minimum idle connection amount for each slave node.
     */
    @ConfigItem(defaultValue = "24")
    public Integer masterConnectionMinimumIdleSize;

    /**
     * Redis 'master' node maximum connection pool size.
     */
    @ConfigItem(defaultValue = "64")
    public Integer masterConnectionPoolSize;

    /**
     * Set node type used for read operation.
     * <br/>
     * Available values:
     * <br/>
     * <code>SLACE</code> - Read from slave nodes, uses MASTER if no SLAVES are available.
     * <br/>
     * <code>MASTER</code> - Read from master node
     * <br/>
     * <code>MASTER_SLAVE</code> - Read from master and slave nodes
     */
    @ConfigItem(defaultValue = "SLAVE")
    public String readMode;

    /**
     * Set node type used for subscription operation.
     * <br/>
     * Available values:
     * <br/>
     * <code>SLAVE</code> - Subscribe to slave nodes
     * <br/>
     * <code>MASTER</code> - Subscribe to master node
     */
    @ConfigItem(defaultValue = "SLAVE")
    public String subscriptionMode;

    /**
     * Add Redis cluster node address in <code>host:port</code> format.
     * <br/>
     * Multiple nodes could be added at once. At least one node from Redis Cluster should be specified.
     * <br/>
     * Redisson discovers automatically cluster topology. Use <code>rediss://</code> protocol for SSL connection.
     */
    @ConfigItem(defaultValue = "redis://127.0.0.1:6379")
    public List<String> nodeAddresses;

    /**
     * Redis cluster scan interval.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "1000")
    public Long scanInterval;

    /**
     * This setting allows to detect and reconnect broken connections using PING command.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "30000")
    public Long pingConnectionInterval;

    /**
     * Enables TCP keepAlive for connection.
     */
    @ConfigItem(defaultValue = "false")
    public Boolean keepAlive;

    /**
     * Enables TCP noDelay for connection.
     */
    @ConfigItem(defaultValue = "false")
    public Boolean tcpNoDelay;

}
