# Map集合
> * 用于保存具有映射关系的数据，Map集合里保存着两组值——key和value。其中key不允许重复。
1. 方法
> * void clear()：删除所有的key-value对。
> * boolean containsKey(Object key)：查询Map中是否包含指定的key，如果包含则返回true。
> * boolean containsValue(Object value)：查询Map中是否包含一个或多个value，如果包含则返回true。
> * Set entrySet()：返回Map中包含的key-value对所组成的Set集合，每个集合元素都是Map.Entry（Entry是Map的内部类）对象。
> * Object get(Object key)：返回指定key所对应的value；如果此Map中不包含该key，则返回null。
> * boolean isEmpty()：查询Map是否为空。
> * Set keySet()：返回该Map中所有key组成的Set集合。
> * Object put(Object key, Object value)：添加一个key-value对，如果当前Map中已有一个与该key相等的key-value对，则新的key-value对会覆盖原来的key-value对。
> * void putAll(Map m)：将指定Map中的key-value对复制到本Map中。
> * Object remove(Object key)：删除指定key所对应的key-value对，返回被删除key所关联的value，如果该key不存在，则返回null。
> * boolean remove(Object key, Object value)：删除指定key、value所对应的key-value对，如果从该Map中成功删除该key-value对，返回true，否则返回false。
> * int size()：返回该Map里的key-value对的个数。
> * Collection values()：返回该Map里所有value组成的Collection。
2. Map中包括一个内部类Entry。
> * Object getKey()：返回该Entry里包含的key值。
> * Object getValue()：返回该Entry里包含的value值。
> * Object setValue(V value)：设置该Entry里包含的value值，并返回新设置的value值。
## SortedMap接口和TreeMap实现类
> * Map.Entry firstEntry()：返回该Map中最小key所对应的key-value对，如果Map为空，则返回null。
> * Object firstKey()：返回该Map中的最小key值，如果该Map为空，则返回null。
> * Map.Entry lastEntry()：返回该Map中最大key所对应的key-value对，如果Map为空，则返回null。
> * Object lastKey()：返回该Map中的最小key值，如果该Map为空或不存在这样的key，则返回null。
> * Map.Entry higherEntry(Object key)：返回该Map中位于key后一位的key-value对（即大于指定key的最小key所对应的key-value）。如果该Map为空，则返回null。
> * Object higherKey(Object key)：返回该Map中国位于key后一位的key值（即大于指定key的最小key值）。如果Map为空或不存在这样的key-value对，返回null。
> * Map.Entry lowerEntry(Object key)：返回该Map中位于key前一位的key-value对（即小于指定key的最大key所对应的key-value）。如果该Map为空，则返回null。
> * Object lowerKey(Object key)：返回该Map中位于key前一位的key值（即小于指定key的最大key值）。如果Map为空或不存在这样的key-value对，返回null。
