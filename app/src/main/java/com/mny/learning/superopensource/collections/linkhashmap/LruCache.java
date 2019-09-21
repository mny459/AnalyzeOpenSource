package com.mny.learning.superopensource.collections.linkhashmap;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author mny on 2019-09-01.
 * Email：mny9@outlook.com
 * Desc:
 */
public class LruCache<K, V> {
    /**
     * 用于真正的存储数据
     */
    private final LinkedHashMap<K, V> map;
    /**
     * 当前数据长度
     */
    private int size;
    /**
     * 最大的数据长度
     */
    private int maxSize;
    /**
     *
     */
    private int putCount;
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private int missCount;

    /**
     *
     * @param maxSize 最大数据长度
     */
    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else {
            this.maxSize = maxSize;
            this.map = new LinkedHashMap(0, 0.75F, true);
        }
    }

    /**
     * 长度
     * @param maxSize
     */
    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else {
            synchronized (this) {
                this.maxSize = maxSize;
            }

            this.trimToSize(maxSize);
        }
    }

    @Nullable
    public final V get(@NonNull K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        } else {
            Object mapValue;
            synchronized (this) {
                mapValue = this.map.get(key);
                if (mapValue != null) {
                    ++this.hitCount;
                    return (V) mapValue;
                }

                ++this.missCount;
            }

            V createdValue = this.create(key);
            if (createdValue == null) {
                return null;
            } else {
                synchronized (this) {
                    ++this.createCount;
                    mapValue = this.map.put(key, createdValue);
                    if (mapValue != null) {
                        this.map.put(key, (V) mapValue);
                    } else {
                        this.size += this.safeSizeOf(key, createdValue);
                    }
                }

                if (mapValue != null) {
                    this.entryRemoved(false, key, createdValue, (V) mapValue);
                    return (V) mapValue;
                } else {
                    this.trimToSize(this.maxSize);
                    return createdValue;
                }
            }
        }
    }

    @Nullable
    public final V put(@NonNull K key, @NonNull V value) {
        if (key != null && value != null) {
            Object previous;
            synchronized (this) {
                ++this.putCount;
                this.size += this.safeSizeOf(key, value);
                previous = this.map.put(key, value);
                if (previous != null) {
                    this.size -= this.safeSizeOf(key, (V) previous);
                }
            }

            if (previous != null) {
                this.entryRemoved(false, key, (V) previous, value);
            }

            this.trimToSize(this.maxSize);
            return (V) previous;
        } else {
            throw new NullPointerException("key == null || value == null");
        }
    }

    public void trimToSize(int maxSize) {
        while (true) {
            K key;
            V value;
            synchronized (this) {
                if (this.size < 0 || this.map.isEmpty() && this.size != 0) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }

                if (this.size <= maxSize || this.map.isEmpty()) {
                    return;
                }

                Map.Entry<K, V> toEvict = (Map.Entry) this.map.entrySet().iterator().next();
                key = toEvict.getKey();
                value = toEvict.getValue();
                this.map.remove(key);
                this.size -= this.safeSizeOf(key, value);
                ++this.evictionCount;
            }

            this.entryRemoved(true, key, value,  null);
        }
    }

    @Nullable
    public final V remove(@NonNull K key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        } else {
            V previous;
            synchronized (this) {
                previous = this.map.remove(key);
                if (previous != null) {
                    this.size -= this.safeSizeOf(key, previous);
                }
            }

            if (previous != null) {
                this.entryRemoved(false, key, previous, null);
            }

            return (V) previous;
        }
    }

    protected void  entryRemoved(boolean evicted, @NonNull K key, @NonNull V oldValue, @Nullable V newValue) {
    }

    @Nullable
    protected V create(@NonNull K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = this.sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        } else {
            return result;
        }
    }

    protected int sizeOf(@NonNull K key, @NonNull V value) {
        return 1;
    }

    public final void evictAll() {
        this.trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    @Override
    public final synchronized String toString() {
        int accesses = this.hitCount + this.missCount;
        int hitPercent = accesses != 0 ? 100 * this.hitCount / accesses : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.maxSize, this.hitCount, this.missCount, hitPercent);
    }
}
