# Quarkus Cache with Redis

Distributed caching with Redis and JCache (JSR-107).

> Many thanks for [Redisson JCache](https://redisson.org/glossary/jcache.html).

## Supported features

- Caching at method level
  - Method return as cache value
- JSR-107 supported annotations
  - `@CachePut`
    - cacheName: attribute to define the cache's name
  - `@CacheKey`: to define the cache's key, instead of `cacheName` as default one.
- Redis Server modes:
  - Single Server: [see all available options](https://github.com/redisson/redisson/wiki/2.-Configuration#262-single-instance-yaml-config-format)
  - Cluster Servers: [see all available options](https://github.com/redisson/redisson/wiki/2.-Configuration#242-cluster-yaml-config-format)
  - > Remember: `CamelCase`  options' names must be converted to `camel-case`
- Custom types must implements `java.io.Serializable`

## Redis configuration with Redisson

TODO:
