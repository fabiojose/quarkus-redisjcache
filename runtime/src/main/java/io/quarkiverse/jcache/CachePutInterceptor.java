package io.quarkiverse.jcache;

import io.quarkus.cache.runtime.AbstractCache;
import io.quarkus.cache.runtime.CacheInterceptionContext;
import io.quarkus.cache.runtime.CacheInterceptor;
import io.smallrye.mutiny.Uni;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.annotation.Priority;
import javax.cache.Cache;
import javax.cache.annotation.CachePut;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fabiojose@gmail.com
 */
@CachePut
@Interceptor
@Priority(1000)
public class CachePutInterceptor extends CacheInterceptor {

    private static final String INTERCEPTOR_BINDING_ERROR_MSG =
        "No binding type";

    private static final Logger log = LoggerFactory.getLogger(
        CachePutInterceptor.class
    );

    @Inject
    CacheFactory cacheFactory;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        final CacheInterceptionContext<CachePut> interceptionContext = getInterceptionContext(
            context,
            CachePut.class,
            true
        );

        if (interceptionContext.getInterceptorBindings().isEmpty()) {
            log.warn(INTERCEPTOR_BINDING_ERROR_MSG);
            return context.proceed();
        }

        final CachePut binding = interceptionContext
            .getInterceptorBindings()
            .iterator()
            .next();

        //TODO: CacheKey
        //TODO: Cache Key as String
        Object key = getCacheKey(
            new DefaultCache(binding.cacheName()),
            interceptionContext.getCacheKeyParameterPositions(),
            context.getParameters()
        );
        key = key.toString();

        log.debug(
            "loading entry with key {} from cache {}",
            key,
            binding.cacheName()
        );

        final Cache<Object, Object> cache = cacheFactory.getOrCreate(binding.cacheName());

        Object value = cache.get(key);
        log.debug("loaded value cache key {}: {}", key, value);

        Object result = value;

        if (null == result) {
            log.debug(
                "no entry found for key {} in the cache {}",
                key,
                binding.cacheName()
            );
            result = context.proceed();
            log.debug("value from called context {}", result);

            cache.put(key, result);
        }

        return result;
    }

    private final class DefaultCache extends AbstractCache {

        private String cacheName;

        DefaultCache(String cacheName) {
            this.cacheName = cacheName;
        }

        @Override
        public String getName() {
            return cacheName;
        }

        @Override
        public String toString(){
            return getName();
        }

        @Override
        public CompletableFuture<Object> get(
            Object key,
            Function<Object, Object> valueLoader
        ) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void invalidate(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void invalidateAll() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Uni<Void> replaceUniValue(Object key, Object emittedValue) {
            throw new UnsupportedOperationException();
        }
    }
}
