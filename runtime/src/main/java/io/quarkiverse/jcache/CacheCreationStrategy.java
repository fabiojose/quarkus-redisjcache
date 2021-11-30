package io.quarkiverse.jcache;

public enum CacheCreationStrategy {

    /**
     * Create and configure caches on application startup.
     * <br/>
     * Eager evaluation
     */
    ON_STARTUP,

    /**
     * Create and configura caches on demand.
     * <br/>
     * Lazy evaluation.
     */
    ON_DEMAND;

}
