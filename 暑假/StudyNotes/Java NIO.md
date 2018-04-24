# Java NIO

> *新IO和传统的IO有相同的作用和目的，但实现方式不同，NIO主要用到的是块，所以NIO的效率要比IO高很多。NIO和IO最大的区别是数据打包和传输方式。IO是以**流**的方式处理数据，而NIO是以**块**的方式处理数据。*

### NIO基础

**Buffer**和**Channel**是标准NIO中的核心对象。Channel是对原IO中流的模拟，任何来源和目的数据都必须通过一个Channel对象。一个Buffer实质上是一个容器对象，发给Channel的所有对象都必须先放到Buffer中；同样的，从Channel中读取的任何数据都要读到Buffer中。

### 关于Buffer

> **Buffer** *是一个对象，它包含一些要写入或读出的数据。在NIO中，数据是放入buffer对象的，而在IO中，数据是直接写入或者读到Stream对象的。**应用程序不能直接对 Channel 进行读写操作，而必须通过 Buffer 来进行**，即 Channel 是通过 Buffer 来读写数据的。*
>
> *在NIO中，所有的数据都是用Buffer处理的，它是NIO读写数据的中转池。Buffer实质上是一个数组，通常是一个字节数据，但也可以是其他类型的数组。但一个缓冲区不仅仅是一个数组，重要的是它提供了对数据的结构化访问，而且还可以跟踪系统的读写进程。*

#### Buffer三个重要的概念：

1. 容量（capacity）：缓冲区的容量表示该Buffer的最大数据容量，缓冲区的容量不能为负值，创建后不能改变。
2. 界限（limit）：第一个不应该被读出或者写入的缓冲区位置索引。也就是说，位于limit后的数据既不可被读，也不可以被写。
3. 位置：用于指明下一个可以被读出的或者写入的缓冲区位置索引。

#### 使用Buffer读写数据一般步骤：

1. 写入数据到Buffer；
2. 调用flip()方法；
3. 从Buffer中读取数据；
4. 调用clear()方法或者compact()方法。

当向 Buffer 写入数据时，Buffer 会记录下写了多少数据。一旦要读取数据，需要通过 flip() 方法将 Buffer **从写模式切换到读模式**。在读模式下，可以读取之前写入到 Buffer 的所有数据。

一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用 clear() 或 compact() 方法。clear() 方法会清空整个缓冲区。compact() 方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。

### 关于Channel

**Channel**是一个对象，可以通过它读取和写入数据。可以把它看做IO中的流。但是它和流相比还有一些不同：

    1. Channel是双向的，既可以读又可以写，而流是单向的
    2. Channel可以进行异步的读写
    3. 对Channel的读写必须通过buffer对象

#### Channel主要类型

1. FileChannel：从文件读取数据的
2. DatagramChannel：读写UDP网络协议数据
3. SocketChannel：读写TCP网络协议数据
4. ServerSocketChannel：可以监听TCP连接

所有的Channel都不能通过构造器来直接创建，而是通过传统的节点的getChannel()方法来返回对应的Channel。

#### 从文件读取数据，需要如下三步

1. 从FileInputStream获取Channel

   `FileChannel inChannel = new FileInputStream("filename").getChannel;`

2. 创建Buffer

   `ByteBuffer buffer = ByteBuffer.allocate(capacity);`

3. 从Channel读取数据到Buffer

   `inChannel.read(buffer);`


#### 写入数据到文件

1. 获取一个通道

   ```java
   FileChannel outChannel = new FileOutputStream("filename").getChannel;
   ```

2. 创建缓冲区，将数据放入缓冲区

   ```java
   ByteBuffer buffer = ByteBuffer.allocate( capacity );

   for (int i = 0; i < message.length; ++i) {
    buffer.put( message[i] );
   }
   buffer.flip();
   ```

3. 把缓冲区数据写入通道中

   ```java
   outChannel.write(buffer);
   ```

   ​

### 需要注意的点

#### 检查状态

当没有更多的数据时，拷贝就算完成，此时 read() 方法会返回 -1 ，我们可以根据这个方法判断是否读完。

```java
int r= inChannel.read( buffer );
if (r==-1) {
     break;
}
```

#### Buffer类的flip、clear方法

flip方法

![](http://img.blog.csdn.net/20150902152400081)

```java
// 源码
public final Buffer flip() {
    limit = position;
    position = 0;
    mark = -1;
    return this;
 }
/*
把当前的指针位置position设置成了limit，再将当前指针position指向数据的最开始端，我们现在可以将数据从缓冲区写入通道了。 position 被设置为 0，这意味着我们得到的下一个字节是第一个字节。 limit 已被设置为原来的 position，这意味着它包括以前读到的所有字节，并且一个字节也不多。
*/
```

clear方法

![](http://img.blog.csdn.net/20150902153303447)

```java
// 源码 
public final Buffer clear() {
    position = 0;
    limit = capacity;
    mark = -1;
    return this;
}
/*
重设缓冲区以便接收更多的字节
*/
```



### 文件锁

> *使用文件锁可以有效地阻止多个进程并发修改同一个文件。*

1. 概念

   * 共享锁: 共享读操作，但只能一个写（读可以同时，但写不能）。共享锁防止其他正在运行的程序获得重复的独占锁，但是允许他们获得重复的共享锁。
   * 独占锁: 只有一个读或一个写（读和写都不能同时）。独占锁防止其他程序获得任何类型的锁。

2. FileLock FileChannel.lock(long position,  long size,  boolean shared)

   >  shared的含义:是否使用共享锁,一些不支持共享锁的操作系统,将自动将共享锁改成排它锁。可以通过调用isShared()方法来检测获得的是什么类型的锁。

3. lock()和tryLock()的区别：

   1. lock()阻塞的方法，锁定范围可以随着文件的增大而增加。无参lock()默认为独占锁；有参lock(0L, Long.MAX_VALUE, true)为共享锁。
   2. tryLock()非阻塞,当未获得锁时,返回null.

4.  FileLock的生命周期：在调用FileLock.release(),或者Channel.close(),或者JVM关闭

5.  FileLock是线程安全的

6. 注意事项：

   - 同一进程内，在文件锁没有被释放之前，不可以再次获取。即在release()方法调用前,只能lock()或者tryLock()一次。
   - 文件锁的效果是与操作系统相关的。一些系统中文件锁是强制性的，就当Java的某进程获得文件锁后，操作系统将保证其它进程无法对文件做操作了。而另一些操作系统的文件锁是询问式的(advisory)，意思是说要想拥有进程互斥的效果，其它的进程也必须也按照API所规定的那样来申请或者检测文件锁，不然将起不到进程互斥的功能。所以文档里建议将所有系统都当做是询问式系统来处理，这样程序更加安全也更容易移植。
   - 如何避免死锁：在读写关键数据时加锁，操作完成后解锁；一次性申请所有需要的资源，并且在申请不成功的情况下放弃已申请到的资源。