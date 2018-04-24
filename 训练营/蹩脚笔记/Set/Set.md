# Set集合
## 一、HashSet类
1. 特点
> * 不能保证元素的排列顺序，顺序可能与添加顺序不同，顺序也有可能发生变化。
> * HashSet不是同步的，如果多个线程同时访问一个HashSet，假设有两个或者两个以上线程同时修改了HashSet集合时，则必须通过代码来保证同步。
> * 集合元素值可以是null。
> * HashSet集合判断两个元素相等的标准是两个对象通过equals()方法比较相等，并且两个对象的hashCode()方法返回值也相等。
2. 重写hashCode()方法的基本规则
> * 在程序运行过程中，同一个对象多次调用hashCode()方法应该返回相同的值。
> * 当两个对象通过equals()方法比较返回true时，这两个对象的hashCode()方法应返回相等的值。
> * 对象中用作equals()方法比较标准的实例变量时，都应该用于计算hashCode值。

## TreeSet类
1. 方法
> * Comparator comparator()：如果TreeSet采用了定制排序，则该方法返回定制排序所使用的Comparator；如果TreeSet采用了自然排序，则返回null。
> * Object first()：返回集合中的第一个元素。
> * Object last(): 返回集合中的最后一个元素。
> * Object lower(Object e)：返回集合中位于指定元素之前的元素。（元素值大小）
> * Object higher(Object e)：返回集合中位于指定元素之后的元素。
> * SortedSet subSet(Object fromElement， Object toElement)：返回Set的子集合，范围从fromElement（包含）到toElement（不包含）。
> * SortedSet headSet(Object。 toElement)：返回此Set的子集，由小于toElement的元素组成。
> * SortedSet tailSet(Object fromElement)：返回此Set的子集，由大于或等于fromElement的元素组成。
2. 自然排序
> * 调用compareTO(Object obj)方法比较元素之间的大小关系。
> * 如果试图把一个对象添加到TreeSet时，则对象的类必须实现Comparable接口，否则程序将会抛出异常。
> * 实现compareTo(Object obj)方法时，需要将被比较对象obj强制类型转换成相同类型。
3. 判断对象相等的唯一标准：两个对象通过comparaTo(Object obj)方法比较是否返回0，如果是则认为对象相等。
4. TreeSet可以删除没有被修改实例变量，切不与其他被修改实例变量的对象重复的对象。
5. 定制排序
> * 比如实现降序排列，则可以通过Comparator接口的帮助。该接口里包含一个int compare(T o1, T o2)方法，该方法用于比较o1和o2的大小。
> * Comparator是一个函数式接口，可使用Lambda表达式来代替Comparator对象。

    import java.util.*;
    class M {
    	int age;
    	public M(int age) {
    		this.age = age;
    	}
    	
    	public String toString() {
    		return "M[age:" + age + "]";
    	}
    }
    
    public class TreeSetTest4 {
    	public static void main(String[] args) {
    		TreeSet ts = new TreeSet((o1, o2) -> {
    			M m1 = (M)o1;
    			M m2 = (M)o2;
    			return m1.age > m2.age ? -1
    					: m1.age < m2.age ? 1 :0;
    		});
    		ts.add(new M(9));
    		ts.add(new M(-3));
    		ts.add(new M(5));
    		System.out.println(ts);
    	}
    }
## EnumSet类
1. EnumSet中的所有元素都必须是指定枚举类型的枚举值。
2. 方法
> * EnumSet allOf(Class elementType)：创建一个包含指定枚举类里所有枚举值的EnumSet集合。
> * EnumSet complementOf(EnumSet s)：创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，新EnumSet集合包含原EnumSet集合所不包含的、此枚举类剩下的枚举值。
> * EnumSet copyOf(Colletion c)：使用一个普通集合来创建EnumSet集合。
> * EnumSet copyOf(EnumSet s)：创建一个与指定EnumSet具有相同元素类型、相同集合元素的EnumSet集合。
> * EnumSet noneOf(Class elementType)：创建一个元素类型为指定枚举类型的空EnumSet。
> * EnumSet of (E from, E... rest)：创建一个包含一个或多个枚举值的EnumSet集合，传入的多个枚举值必须属于同一个枚举类。
> * EnumSet rang(E from, E to)：创建一个包含从from枚举值到to枚举值范围内所有枚举值的EnumSet集合。

    import java.util.*;
    enum Season {
    	SPRING,SUMMER,FALL,WINTER
    }
    
    public class EnumSetTest {
    	public static void main(String[] args) {
    		EnumSet es1 = EnumSet.allOf(Season.class);
    		System.out.println(es1);
    		EnumSet es2 = EnumSet.noneOf(Season.class);
    		System.out.println(es2);
    		es2.add(Season.WINTER);
    		es2.add(Season.SPRING);
    		System.out.println(es2);
    		EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
    		System.out.println(es3);
    		EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
    		System.out.println(es4);
    		EnumSet es5 = EnumSet.complementOf(es4);
    		System.out.println(es5);
    	}
    }