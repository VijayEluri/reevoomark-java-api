package com.reevoo.client;

import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class Cache {

    private static final ConcurrentMap<String, ReevooMarkRecord> backingCache = new MapMaker().expiration(4 * 60 * 60, TimeUnit.SECONDS).softValues().makeMap();

    public static void put(String key, ReevooMarkRecord tcv) {
        backingCache.put(key, tcv);
    }

    public static ReevooMarkRecord get(String key) {
        return backingCache.get(key);
    }

    public static int size() {
        return backingCache.size();
    }

    public static void invalidate() {
        synchronized (backingCache) {
            backingCache.clear();
        }
    }
}
