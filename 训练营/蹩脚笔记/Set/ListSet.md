# List集合
* Collection接口的子接口
* 方法
> * void add(int index, Object element)：将元素element插入到List集合的index处。
> * boolean addAll(int index, Collection c)：将集合c所包含的所有元素都插入到List集合的index处。
> * Object get(int index)：返回集合index索引处的元素。
> * int indexOf(Object o)：返回对象o在List集合中第一次出现的位置索引。
> * int lastIndexOf(int index)：返回对象o在List集合中最后一次出现的位置索引。
> * Object remove(int index)：删除并返回index索引处的元素。
> * Object set(int index, Object element)：将index索引处的元素替换成element对象，返回被替换的旧元素。
> * List subList(int fromIndex, int toIndex)：返回从索引fromindex（包含）到索引toIndex（不包含）处所有集合元素组成的子集合。
> * void replaceAll(UnaryOperator operator)：根据operator指定的计算规则重新设置List集合的所有元素。
> * void sort(Comparator c)：根据Comparator参数对List集合的元素排序。