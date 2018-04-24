# Queue集合
1. 用于模拟队列，队列通常指“先进先出”的容器。
2. 方法
> * void add(Object e)：将指定元素加入此队列的尾部。
> * Object element()：获取队列的头部的元素，但不删除该元素。
> * boolean offer(Object e)：将指定元素加入此队列的尾部。当使用用容量限制的队列时，此方法通常比add(Object e)方法更好。
> * Object peek()：获取队列头部的元素，但不删除该元素，如果队列为空，则返回null。
> * Object poll()：获取队列头部的元素，并删除该元素，如果队列为空，则返回null。
> * Object remove()：获取队列头部的元素，并删除该元素。
## Deque接口与ArrayDeque实现类
1. Duque接口是Queue接口的子接口，代表一个双端队列。
2. 方法
> * void addFirst(Object e)：将指定元素插入该双端队列的开头。
> * void addLast(Object e)：将指定元素插入该双端队列的末尾。
> *Iterator descendingIterator()：返回该双端队列对应的迭代器，该迭代器将以逆向顺序来迭代队列中的元素。
> * Object getFirst()：获取但不删除双端队列的第一个元素。
> * Object getLast()：获取但不删除双端队列的最后一个元素。
> * boolean offerFirst(Object e)：将指定元素插入该双端队列的开头。
> * boolean offerLast(Object e)：将指定元素插入该双端队列的开头。
> * Object peekFirst()：获取但不删除该双端队列的第一个元素；如果此双端队列为空，则返回null。
> * Object peekLast()：获取但不删除该双端队列的最后一个元素；如果此双端队列为空，则返回null。
> * Object pollFirst()：获取并删除该双端队列的第一个元素；如果此双端队列为空，则返回null。
> * Object pollLast()：获取并删除该双端队列的最后一个元素；如果此双端队列为空，则返回null。
> * Object pop()：pop出该双端队列所表示的栈的栈顶元素。相当于removeFirst();
> * void push(Object e)：将一个元素push进该双端队列所表示的栈的栈顶。相当与addFirst(e)。
> * Object removeFirst()：获取并删除该双端队列的第一个元素。
> * Object removeFirstOccurrence(Object o)：删除该双端队列的第一次出现的元素o。
> * Object removeLast()：获取并删除该双端队列的最后一个元素。
> * Object removeLastOccurrence(Object o)：删除该双端队列的最后一次出现的元素o。