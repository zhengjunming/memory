## Java集合类
> java集合类大致分为Set、List、Queue、Map四种体系
* Set代表无序、不可重复的集合
* List代表有序、重复的集合
* Queue代表一种队列集合
* Map代表具有映射关系的集合
- 功能：主要负责保存、盛装其他数据，因此集合类也被称为容器类
- 集合里只能保存对象
### 一、访问集合
1. 访问List集合中的元素，可以直接根据元素的索引来访问；
2. 访问Map集合中的元素，可以根据每项元素的key来访问其value；
3. 访问Set集合中的元素，则只能根据元素本身来访问（Set集合里元素不允许重复的原因）
### 二、Collection和Iterator接口
> 操作集合元素的方法
1. boolean add(Object o)：该方法用于向集合里添加一个元素
2. boolean addAll(Collection c)：该方法把集合c里的所有元素添加到指定集合里。
3. void clear()：清除集合里的所有元素，将集合长度变为0.
4. boolean contains(Object o)：返回集合里是否包含指定元素。
5. boolean containsAll(Collection c)：返回集合里是否包含集合c里的所有元素。
6. boolean isEmpty()：返回集合是否为空。
7. Iterator iterator()：返回一个Iterator对象，用于遍历集合里的元素。
8. boolean remove(Object o)：删除集合中的指定元素o。
9. boolean removeAl(Collection c)：从集合中删除集合c里包含的所有元素。
10. boolean retainAll(Collection c)：从集合中删除集合c里不包含的元素。
11. int size()：返回集合里元素的个数。
12. Object[] toArray()：把集合转换成一个数组，所有的集合元素变成对应的数组元素。
### 三、Iterator遍历集合元素
1. boolean hasNext()：如果被迭代的集合元素还没有被遍历完，则返回true。
2. Object next()：返回集合里的下一个函数。
3. void forEachRemaining(Consumer action) ，使用Lambda表达式来遍历集合元素。