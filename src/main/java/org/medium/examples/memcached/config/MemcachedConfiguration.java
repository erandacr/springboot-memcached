package org.medium.examples.memcached.config;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import org.medium.examples.memcached.cache.Memcached;
import org.medium.examples.memcached.cache.MemcachedManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemcachedConfiguration implements CachingConfigurer {

    @Value("${memcached.addresses}")
    private String memcachedAddresses;

    @Override
    @Bean
    public CacheManager cacheManager() {
        CacheManager cacheManager;
        try {
            cacheManager = new MemcachedManager(internalCaches());
            return cacheManager;
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<Memcached> internalCaches() throws URISyntaxException {
        System.out.println("creating cache");
        final Collection<Memcached> caches = new ArrayList<>();
        caches.add(new Memcached(memcachedAddresses));
        return caches;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }
}
