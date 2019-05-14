package org.medium.examples.memcached.cache;

import java.io.IOException;
import java.util.concurrent.Callable;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.SerializingTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class Memcached implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(Memcached.class);

    private final String name = "personCache";

    private MemcachedClient cache;


    public Memcached(String memcachedAddresses) {
        try {
            cache = new MemcachedClient(
                new ConnectionFactoryBuilder()
                    .setTranscoder(new SerializingTranscoder())
                    .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY)
                    .build(),
                AddrUtil.getAddresses(memcachedAddresses));

        } catch (IOException ex) {
            // the Memcached client could not be initialized.
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(final Object key) {
        System.out.println("getting from cache");
        Object value = null;
        try {
            value = cache.get(key.toString());
        } catch (final Exception e) {
            LOGGER.warn(e.getMessage());
        }
        if (value == null) {
            return null;
        }
        return new SimpleValueWrapper(value);
    }


    @Override
    public void put(final Object key, final Object value) {
        cache.set(key.toString(), 7 * 24 * 3600, value);
        System.out.println("added to cache");
    }


    @Override
    public void evict(final Object key) {
        this.cache.delete(key.toString());
    }

    @Override
    public void clear() {
        cache.flush();
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return (T) get(o);
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }
}