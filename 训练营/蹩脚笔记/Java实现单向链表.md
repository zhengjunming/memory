### Java实现单向链表
1. 实现方法
*  首先定义一个接口，接口里写操作链表的基本方法。例如求长度，判断是否为空，增删查改等操作。
```
public interface list {
	//获取链表长度
	public int size();
	//判断是否是空表
	public boolean isEmpty();
	//插入元素
	public void insert(int index, Object obj) throws Exception;
	//删除元素
	public void delete(int index) throws Exception;
	//获取指定位置的元素
	public Object get(int index) throws Exception;
	//修改指定位置的元素
	public void modify(int index, Object obj) throws Exception;
}
```
*  接下来就要定义一个结点类，里面有两个成员变量，数据域Object element；指针域Node next;类的实现就需要以下几点。
> * 1. 头结点的构造方法
> * 2. 非头结点的构造方法
> * 3. element和next的getter和setter方法，也就是获得当前结点的信息以及设置当前结点的信息。
```
public class Node {
	Object element;
	Node next;
	//头结点构造方法
	public Node(Node nextval) {
		this.next = nextval;
	}
	//非头结点构造方法
	public Node(Object obj, Node nextval) {
		this.element = obj;
		this.next = nextval;
	}
        //省略getter和setter方法
}
```
*  第三点也是最重要的一点：实现单链表的功能，这就需要重写接口的所有方法，通过getter和setter方法以及this关键字来对链表的信息进行操控，并在用户输入错误时能够捕获异常。
```
public class LinkList implements list {
	Node head;
	Node current;
	int size;
	
	public LinkList() {
		this.head = current = new Node(null);
		this.size = 0;
	}
	
	public void index(int index) throws Exception {
		if (index < -1 || index > size - 1) {
			throw new Exception("参数错误！");
		}
		if (index == -1) {
			return;
		}
		
		current = head.next;
		int j = 0;
		while (current != null && j < index) {
			current = current.next;
			j++;
		}
	}
	
	@Override
	public void delete(int index) throws Exception {
		if (isEmpty()) {
			throw new Exception("链表为空，无法删除");
		} 
		if (index < 0 || index > size) {
			throw new Exception("参数错误");
		}
		index(index - 1);
		current.setNext(current.next.next);
		size--;
	}
	
	@Override
	public Object get(int index) throws Exception {
		if (index < -1 || index > size - 1) {
			throw new Exception("参数错误");
		}
		index(index);
		return current.getElement();
	}
	
	@Override
	public void insert(int index, Object obj) throws Exception {
		if (index < 0 || index > size) {
			throw new Exception("参数错误");
		}
		index(index - 1);
		current.setNext(new Node(obj, current.next));
		size++;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void modify(int index, Object obj) throws Exception {
		if (index < 0 || index > size - 1) {
			throw new Exception("参数错误");
		}
		index(index);
		current.setElement(obj);
	}
}
```