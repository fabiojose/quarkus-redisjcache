package io.quarkiverse.jcache;

import java.util.concurrent.TimeUnit;
import javax.cache.configuration.Factory;
import javax.cache.expiry.Duration;
import javax.cache.expiry.EternalExpiryPolicy;
import javax.cache.expiry.ExpiryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fabiojose@gmail.com
 */
public class ExpiryPolicyFactory implements Factory<ExpiryPolicy> {

    private static final long ETERNAL = -1l;

    private static final Logger log = LoggerFactory.getLogger(
        ExpiryPolicyFactory.class
    );

    private final Long expiry;
    private final String cacheName;

    public ExpiryPolicyFactory(Long expiryInMillis, String cacheName) {
        if (null != expiryInMillis) {
            this.expiry = expiryInMillis;
        } else {
            this.expiry = ETERNAL;
        }

        this.cacheName = cacheName;
    }

    @Override
    public ExpiryPolicy create() {
        if (expiry != ETERNAL) {
            return new CustomExpiryPolicy(expiry, cacheName);
        }

        log.info("cache entries in {} are eternal", cacheName);
        return EternalExpiryPolicy.factoryOf().create();
    }
}

class CustomExpiryPolicy implements ExpiryPolicy {

    private static final Logger log = LoggerFactory.getLogger(
        CustomExpiryPolicy.class
    );

    private final Duration duration;

    CustomExpiryPolicy(long millis, String cacheName) {
        duration = new Duration(TimeUnit.MILLISECONDS, millis);
        log.info(
            "cache entries in {} has expiration configured with duration of {} {}",
            cacheName,
            duration.getDurationAmount(),
            duration.getTimeUnit()
        );
    }

    @Override
    public Duration getExpiryForCreation() {
        return duration;
    }

    @Override
    public Duration getExpiryForAccess() {
        return duration;
    }

    @Override
    public Duration getExpiryForUpdate() {
        return duration;
    }
}
