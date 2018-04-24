package com.zheng.server;

import java.util.*;

/**
 * Created by 郑俊铭 on 2017/7/14.
 */
public class MyMap<K, V> {
    public Map<K, V> map = Collections.synchronizedMap(new HashMap<>());

    /**
     * 根据value来删除指定项
     *
     * @param value 属性
     */
    public synchronized void removeByValue(Object value) {
        for (Object key : map.keySet()) {
            if (map.get(key) == value) {
                map.remove(key);
                break;
            }
        }
    }

    /**
     * 获取所有value组成的Set集合
     *
     * @return Set集合
     */
    public synchronized Set<V> valueSet() {
        Set<V> result = new HashSet<>();
        map.forEach((key, value) -> result.add(value));
        return result;
    }

    /**
     * 根据value查找key
     *
     * @param value 属性
     * @return key
     */
    public synchronized K getKeyByValue(V value) {
        for (K key : map.keySet()) {
            if (map.get(key) == value || map.get(key).equals(value)) {
                return key;
            }
        }
        return null;
    }

    /**
     * 实现put方法，不允许value重复
     *
     * @param key   key
     * @param value value
     * @return
     */
    public synchronized V put(K key, V value) {
        for (V val : valueSet()) {
            if (val.equals(value) && val.hashCode() == value.hashCode()) {
                throw new RuntimeException("MyMap实例中不允许有重复的value");
            }
        }
        return map.put(key, value);
    }
}
