package com.w.proandroid.common.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class EnumDigest<E extends Enum<E>, K> {

    private final Map<K,E> mCache = new HashMap<>();
    private final E mDefault;

    public EnumDigest(E[] values) {
        this(values, null);
    }

    public EnumDigest(E[] values, E def) {
        for (E value : values) {
            K key = getKey(value);
            mCache.put(key, value);
        }
        mDefault = def;
    }

    protected abstract K getKey(E value);

    public E from(K key) {
        E value = mCache.get(key);
        return (value == null) ? mDefault : value;
    }
}
