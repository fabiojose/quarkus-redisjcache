package io.quarkiverse.jcache.redis;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.runtime.annotations.ConfigItem;

public abstract class AbstractRedisServerConfig {

    /**
     * To enable this kind of configuration.
     */
    @ConfigItem(defaultValue = "false")
    @JsonIgnore
    public Boolean enabled;

    /**
     * Username for Redis server authentication.
     * <br/>
     * <b>Requires Redis 6.0+</b>
     */
    @ConfigItem(defaultValue = "null")
    public String username;

    /**
     * Password for Redis server authentication.
     */
    @ConfigItem(defaultValue = "null")
    public String password;

    /**
     * Name of client connection.
     */
    @ConfigItem(defaultValue = "null")
    public String clientName;

    /**
     * If pooled connection not used for a timeout time and current connections amount bigger than minimum idle connections pool
     * size, then it will closed and removed from pool.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "10000")
    public Long idleConnectionTimeout;

    /**
     * Timeout during connecting to any Redis server.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "10000")
    public Long connectTimeout;

    /**
     * Redis server response timeout. Starts to countdown when Redis command was succesfully sent.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "3000")
    public Long timeout;

    /**
     * Error will be thrown if Redis command can't be sended to Redis server after this number of attempts.
     */
    @ConfigItem(defaultValue = "3")
    public Integer retryAttempts;

    /**
     * Time interval after which another one attempt to send Redis command will be executed.
     * <br/>
     * Value in milliseconds.
     */
    @ConfigItem(defaultValue = "1500")
    public Long retryInterval;

    /**
     * Subscriptions per subscribe connection limit.
     */
    @ConfigItem(defaultValue = "5")
    public Integer subscriptionsPerConnection;

    /**
     * Minimum idle connection pool size for subscription (pub/sub) channels.
     */
    @ConfigItem(defaultValue = "1")
    public Integer subscriptionConnectionMinimumIdleSize;

    /**
     * Maximum connection pool size for subscription (pub/sub) channels.
     */
    @ConfigItem(defaultValue = "50")
    public Integer subscriptionConnectionPoolSize;

    /**
     * Defines array of allowed SSL protocols.
     * <br>
     * Example values: TLSv1.3, TLSv1.2, TLSv1.1, TLSv1
     */
    @ConfigItem(defaultValue = "null")
    public String sslProtocols;

    /**
     * Enables SSL endpoint identification during handshaking, which prevents man-in-the-middle attacks.
     */
    @ConfigItem(defaultValue = "true")
    public Boolean sslEnableEndpointIdentification;

    /**
     * Defines SSL provider (<code>JDK<code> or <code>OPENSSL</code>) used to handle SSL connections.
     * <br/>
     * <code>OPENSSL</code> considered as faster implementation and requires
     * <a href="https://repo1.maven.org/maven2/io/netty/netty-tcnative-boringssl-static/">netty-tcnative-boringssl-static</a> to
     * be added in classpath.
     */
    @ConfigItem(defaultValue = "JDK")
    public String sslProvider;

    /**
     * Defines path to SSL truststore.
     * <br/>
     * It's stores certificates which is used to identify server side of SSL connection.
     */
    @ConfigItem(defaultValue = "null")
    public String sslTruststore;

    /**
     * Defines password for SSL truststore
     */
    @ConfigItem(defaultValue = "null")
    public String sslTruststorePassword;

    /**
     * Defines path to SSL keystore.
     * <br/>
     * It's stores private key and certificates corresponding to their public keys. Used if server side of SSL connection
     * requires client authentication.
     */
    @ConfigItem(defaultValue = "null")
    public String sslKeystore;

    /**
     * Defines password for SSL keystore.
     */
    @ConfigItem(defaultValue = "null")
    public String sslKeystorePassword;

    public String getUsername() {
        return ("null".equals(username) ? null : username);
    }

    public String getPassword() {
        return ("null".equals(password) ? null : password);
    }

    public String getClientName() {
        return ("null".equals(clientName) ? null : clientName);
    }

    public String getSslProtocols() {
        return ("null".equals(sslProtocols) ? null : sslProtocols);
    }

    public String getSslTruststore() {
        return ("null".equals(sslTruststore) ? null : sslTruststore);
    }

    public String getSslTruststorePassword() {
        return ("null".equals(sslTruststorePassword) ? null : sslTruststorePassword);
    }

    public String getSslKeystore() {
        return ("null".equals(sslKeystore) ? null : sslKeystore);
    }

    public String getSslKeystorePassword() {
        return ("null".equals(sslKeystorePassword) ? null : sslKeystorePassword);
    }
}
