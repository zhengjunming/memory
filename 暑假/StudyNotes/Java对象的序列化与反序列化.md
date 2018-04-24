# Java对象的序列化与反序列化

> *序列化 (Serialization)是将对象的状态信息转换为可以存储或传输的形式的过程。一般将一个对象存储至一个储存媒介，例如档案或是记亿体缓冲等。在网络传输过程中，可以是字节或是XML等格式。而字节的或XML编码格式可以还原完全相等的对象。这个相反的过程又称为反序列化。*

* 对象序列化机制（object serialization）是Java语言内建的一种对象持久化方式，通过对象序列化，可以把对象的状态保存为字节数组，并且可以在有需要的时候将这个字节数组通过反序列化的方式再转换成对象。对象序列化可以很容易的在JVM中的活动对象和字节数组（流）之间进行转换。

### Serializable接口

> *类通过实现 **java.io.Serializable** 接口以启用其序列化功能。未实现此接口的类将无法使其任何状态序列化或反序列化。可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，仅用于标识可序列化的语义。* 

* 如果要序列化的类有父类，要想同时将在父类中定义过的变量持久化下来，那么父类也应该集成java.io.Serializable接口。
* 序列化步骤
  * 创建一个ObjectOutputStream，这个输出流是一个处理流。
  * 调用ObjectOutputStream对象的writeObject()方法输出可序列化对象。
* 反序列化步骤
  * 创建一个ObjectInputStream输入流，也是一个处理流。
  * 调用ObjectInputStream对象的readObject()方法读取流中的对象。需要把取得的对象进行强制类型转换。
  * 采用反序列化恢复Java对象时，必须提供该Java对象所属类的class文件。

#### 实例

User实体类，实现了Serializable接口

```java
package com.zheng.serializable;

import java.io.Serializable;

/**
 * 实现了Serializable接口
 * Created by 郑俊铭 on 2017/7/13.
 */
public class User implements Serializable {
    private String name;
    private int age;

    // 省略getter和setter方法

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

序列化和反序列化

```java
package com.zheng.serializable;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

/**
 * Created by 郑俊铭 on 2017/7/13.
 */
public class SerializableDemo1 {
    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("zhengjunming");
        user.setAge(20);

        // 序列化
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(
                            "D:\\IDEA-File\\IO\\src\\com" +
                                    "\\zheng\\serializable\\object.txt"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }

        // 反序列化
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(
                    "D:\\IDEA-File\\IO\\src\\com" +
                            "\\zheng\\serializable\\object.txt"));
            User newUser = (User) ois.readObject();
            System.out.println(newUser.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
Output:
User{name='zhengjunming', age=20}
*/
```

​	需要指出的是，当一个可序列化类有多个父类时（包括直接父类和间接父类），这些父类要么有无参数的构造器，要么也是可序列化的，否则反序列化时将抛出InvalidClassException。

### 对象引用的序列化

> *如果某个类的成员变量的类型不是基本类型或者String类型，而是另一个引用类型，那么这个引用类必须是可序列化的，否则拥有该类型成员变量的类也是不可序列化的。即使实现了Serializable接口和Externalizable接口。*

### Java序列化机制

1. 所有保存到磁盘的对象都有一个序列化编号。
2. 当试图序列化一个对象时，有以下两种情况：
   * 对象未被序列化：将该对象转换成字节序列并输出。
   * 对象已经被序列化过：程序只直接输出一个序列号编号。而不是再次重新序列化该对象。
3. 存在问题：当程序序列化一个可变对象时，只有第一次使用writeObject()方法输出时才会将该对象转换成字节序列并输出，当程序再次调用writeObject()方法时，程序只是输出前面的序列化编号，即使后面该对象的实例变量已被改变，改变的实例变量值也不会被输出。

### 自定义序列化

1. **transient** 关键字。

   > *transient关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。*

2. **Externalizable**接口。实现该接口的序列化类完全由开发者决定存储和恢复对象数据。必须实现下面两个方法：

   * void readExternal(ObjectInput in)：实现反序列化。该方法调用DataInput（ObjectInput的父接口）的方法来恢复基本类型的实例变量值，调用ObjectInput的readObject()方法来恢复引用类型的实例变量值。
   * void writeExternal(ObjectOutput out)：保存对象的状态。该方法调用DataOutput（ObjectOutput的父接口）的方法来保存基本类型的实例变量值，调用ObjectOutput的writeObject()方法来保存引用类型的实例变量值。

User实体类将会发生变化

```java
package com.zheng.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 实现了Externalizable接口
 * Created by 郑俊铭 on 2017/7/13.
 */
public class User1 implements Externalizable {
    private String name;
    private int age;
	// 一定要有无参构造器，不然序列化失败
    public User1() {

    }
  
    // 省略getter和setter方法

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = (Integer) in.readObject();
    }
}

```

序列化和反序列化

```java
package com.zheng.serializable;

import java.io.*;

/**
 * 实现Externalizable接口实现序列化和反序列化
 * Created by 郑俊铭 on 2017/7/13.
 */
public class ExternalizableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User1 user1 = new User1();
        user1.setName("zhengjunming");
        user1.setAge(20);
        // 序列化
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream("D:\\IDEA-File\\IO\\src\\com" +
                    "\\zheng\\serializable\\object2.txt"));
            oos.writeObject(user1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }

        // 反序列化
        ObjectInputStream ois = null;
        User1 user11 = new User1();
        try {
            ois = new ObjectInputStream(
                    new FileInputStream("D:\\IDEA-File\\IO\\src\\com" +
                    "\\zheng\\serializable\\object2.txt"));
            user11 = (User1) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
        System.out.println(user11.toString());
    }
}

/*
Output:
User{name='zhengjunming', age=20}
*/
```

3.  **ArrayList的序列化**

    通过java.util.ArrayList的部分源码我们可以知道它是可序列化的

    ```java
    public class ArrayList<E> extends AbstractList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
        private static final long serialVersionUID = 8683452581122892189L;
        transient Object[] elementData; // non-private to simplify nested class access
        private int size;
    }
    ```

    因为elementData是`transient`的，所以我们认为这个成员变量不会被序列化而保留下来，但还是能获得ArrayList里面的元素，如下面的代码。

    ```java
    package com.zheng.serializable;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    /**
     * ArrayList序列化
     * Created by 郑俊铭 on 2017/7/13.
     */
    public class ArrayListTest {
        public static void main(String[] args) throws IOException, ClassNotFoundException {
            List<String> strings = new ArrayList<>();
            strings.add("zheng");
            strings.add("jun");
            strings.add("ming");

            // 序列化
            ObjectOutputStream oos = null;
            try {
                 oos = new ObjectOutputStream(
                        new FileOutputStream("D:\\IDEA-File\\IO\\src\\com" +
                                "\\zheng\\serializable\\object3.txt"));
                oos.writeObject(strings);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                oos.close();
            }

            // 反序列化
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(
                        new FileInputStream("D:\\IDEA-File\\IO\\src\\com" +
                        "\\zheng\\serializable\\object3.txt"));
                List<String> newString = (List<String>) ois.readObject();
                System.out.println(newString);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                ois.close();
            }

        }
    }
    /*
    Output:
    [zheng, jun, ming]
    */
    ```

    原因：

    * 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化。

      如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。

      用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。

    我们先看看writeObject和readObject的实现

    ```java
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
            elementData = EMPTY_ELEMENTDATA;

            // Read in size, and any hidden stuff
            s.defaultReadObject();

            // Read in capacity
            s.readInt(); // ignored

            if (size > 0) {
                // be like clone(), allocate array based upon size not capacity
                ensureCapacityInternal(size);

                Object[] a = elementData;
                // Read in all elements in the proper order.
                for (int i = 0; i < size; i++) {
                    a[i] = s.readObject();
                }
            }
        }

    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
            // Write out element count, and any hidden stuff
            int expectedModCount = modCount;
            s.defaultWriteObject();
      
            // Write out size as capacity for behavioural compatibility with clone()
            s.writeInt(size);
      
            // Write out all elements in the proper order.
            for (int i = 0; i < size; i++) {
                s.writeObject(elementData[i]);
            }

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    ```

    为什么elementData用transient修饰呢？

    > *ArrayList实际上是动态数组，每次在放满以后自动增长设定的长度值，如果数组自动增长长度设为100，而实际只放了一个元素，那就会序列化99个null元素。为了保证在序列化的时候不会将这么多null同时进行序列化，ArrayList把元素数组设置为transient。*

    解决方法：通过writeObject 和 readObject方法。

    > *为了防止一个包含大量空对象的数组被序列化，为了优化存储，所以，ArrayList使用transient来声明elementData。 但是，作为一个集合，在序列化过程中还必须保证其中的元素可以被持久化下来，所以，通过重写writeObject和 readObject方法的方式把其中的元素保留下来。*
    >
    > *writeObject方法把elementData数组中的元素遍历的保存到输出流（ObjectOutputStream）中。*
    >
    > *readObject方法从输入流（ObjectInputStream）中读出对象并保存赋值到elementData数组中。*

    #### ObjectOutputStream

    对象的序列化过程通过ObjectOutputStream和ObjectInputputStream来实现的，分析一下ArrayList中的writeObject 和 readObject 方法到底是如何被调用的。

    ObjectOutputStream的writeObject的调用栈：

    *writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject*

    invokeWriteObject的源码：

    ```java
    void invokeWriteObject(Object obj, ObjectOutputStream out)
            throws IOException, UnsupportedOperationException {
            if (writeObjectMethod != null) {
                try {
                    writeObjectMethod.invoke(obj, new Object[]{ out });
                } catch (InvocationTargetException ex) {
                    Throwable th = ex.getTargetException();
                    if (th instanceof IOException) {
                        throw (IOException) th;
                    } else {
                        throwMiscException(th);
                    }
                } catch (IllegalAccessException ex) {
                    // should not occur, as access checks have been suppressed
                    throw new InternalError(ex);
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }
    ```

    其中`writeObjectMethod.invoke(obj, new Object[]{ out });`是关键，通过反射的方式调用writeObjectMethod方法。

### 序列化和单例模式的渊源

> *通过序列化可以破坏单例模式。*

首先先写一个单例类

```java
package com.zheng.serializable;

import java.io.Serializable;

/**
 * 使用双重检验锁方式实现单例
 * Created by 郑俊铭 on 2017/7/13.
 */
public class Singleton implements Serializable {
    private volatile static Singleton singleton;
    private Singleton() {}
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return  singleton;
    }
}

```

再写一个测试类

```java
package com.zheng.serializable;

import java.io.*;

/**
 * 序列化和反序列化破坏单例模式
 * Created by 郑俊铭 on 2017/7/13.
 */
public class SerializableDemo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream("D:\\IDEA-File\\IO\\src\\com" +
                            "\\zheng\\serializable\\object3.txt"));
            oos.writeObject(Singleton.getSingleton());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos.close();
        }

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(
                    new FileInputStream("D:\\IDEA-File\\IO\\src\\com" +
                            "\\zheng\\serializable\\object3.txt"));
            Singleton newInstance = (Singleton) ois.readObject();
            System.out.println(newInstance == Singleton.getSingleton());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
    }
}
/*
Output:
false
*/
```

由上面的结果我们发现经过序列化和反序列化之后已经出现不同的对象，那么原因是什么呢？

####  ObjectInputStream

* 对象的序列化过程通过ObjectOutputStream和ObjectInputputStream来实现的，那么ObjectInputputStream 的readObject 方法执行情况到底是怎样的。

  `readObject`的调用栈：**readObject--->readObject0--->readOrdinaryObject--->checkResolve**

### 序列化ID

> *虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 `private static final long serialVersionUID`)*

### 对象序列化注意点

1. 对象的类型、实例变量（包括基本类型、数组、对其他对象的引用）都会被序列化；方法、类变量（static修饰的成员变量）、transient实例变量都不会被序列化。
2. 实现Serializable接口的类如果需要让某个实例变量不被序列化，则可在该实例变量前加transient修饰符，而不是加static关键字。虽然static关键字也可以达到效果，但static关键字不能这样用。
3. 保证序列化对象的实例变量类型也是可序列化的，否则需要使用transient关键字修饰该实例变量，要不然该类也是不可序列化的。
4. 反序列化对象时必须有序列化对象的class文件
5. 当通过文件、网络来读取序列化后的对象时，必须按实际写入的顺序读取。