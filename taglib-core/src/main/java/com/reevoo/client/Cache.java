package com.reevoo.client;

import com.google.common.cache.CacheBuilder;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class Cache {

    private static final com.google.common.cache.Cache<String, ReevooMarkRecord> backingCache =
      CacheBuilder.newBuilder()
             .maximumSize(10000)
             .softValues()
             .build();


    public static void put(String key, ReevooMarkRecord tcv) {
        backingCache.put(key, tcv);
    }

    public static ReevooMarkRecord get(String key) {
        return backingCache.getIfPresent(key);
    }

    public static long size() {
        return backingCache.size();
    }

    public static void invalidate() {
        synchronized (backingCache) {
            backingCache.invalidateAll();
        }
    }
}
