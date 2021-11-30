package io.quarkusverse.jcache.redis;

import io.quarkiverse.jcache.CacheManagerProducer;
import io.quarkiverse.jcache.CachePutInterceptor;
import io.quarkiverse.jcache.redis.CacheRedisRecorder;
import io.quarkiverse.jcache.redis.RedisJCacheConfigurationFactory;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.arc.deployment.InterceptorBindingRegistrarBuildItem;
import io.quarkus.arc.processor.InterceptorBindingRegistrar;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import java.util.List;
import javax.cache.annotation.CachePut;
import javax.inject.Inject;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationTarget;
import org.jboss.jandex.AnnotationTarget.Kind;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;
import org.jboss.jandex.MethodInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class QuarkusCacheRedisProcessor {

    private static final String CACHE_NAME = "cacheName";
    private static final String FEATURE = "quarkus-cache-redis";
    private static final DotName CACHE_PUT = DotName.createSimple(
        CachePut.class.getName()
    );

    private static final Logger log = LoggerFactory.getLogger(
        QuarkusCacheRedisProcessor.class
    );

    @Inject
    CombinedIndexBuildItem combinedIndexBuildItem;

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    InterceptorBindingRegistrarBuildItem addInterceptorBindings() {
        return new InterceptorBindingRegistrarBuildItem(
            new InterceptorBindingRegistrar() {
                @Override
                public List<InterceptorBinding> getAdditionalBindings() {
                    return List.of(InterceptorBinding.of(CachePut.class));
                }
            }
        );
    }

    @BuildStep
    AdditionalBeanBuildItem additionalBeans() {
        return new AdditionalBeanBuildItem(
            CacheManagerProducer.class,
            CachePutInterceptor.class,
            RedisJCacheConfigurationFactory.class
        );
    }

    @BuildStep
    @Record(value = ExecutionTime.RUNTIME_INIT)
    public void bootstrap(
        CacheRedisRecorder recorder,
        BeanContainerBuildItem containerBuildItem
    ) {
        final IndexView index = combinedIndexBuildItem.getIndex();

        for (AnnotationInstance cachePutInstance : index.getAnnotations(
            CACHE_PUT
        )) {
            if (Kind.METHOD.equals(cachePutInstance.target().kind())) {
                final AnnotationTarget target = cachePutInstance.target();
                final MethodInfo method = target.asMethod();
                final ClassInfo clazz = method.declaringClass();

                log.info(
                    "processing annotation instance {} at {}#{}",
                    cachePutInstance,
                    clazz,
                    target
                );

                // cacheName
                String cacheName = cachePutInstance
                    .value(CACHE_NAME)
                    .asString();

                //TODO: Cache do cache

                recorder.prepare(
                    containerBuildItem.getValue(),
                    cacheName
                );

            } else {
                log.warn("unsupported target {}", cachePutInstance.target());
            }
        }
    }
}
