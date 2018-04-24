# Java网络编程

>  *网络编程的实质就是两个(或多个)设备(例如计算机)之间的数据传输。*

### 网络基础知识

#### 计算机网络可以提供以下主要功能

1. 资源共享
2. 信息传输与集中处理
3. 均衡负荷与分布管理
4. 综合信息服务

#### 计算机网络实现通信的约定——通信协议

> *通信协议负责对**传输速率**、**传输代码**、**代码结构**、**传输控制步骤**、**出错控制**等制定处理标准。*

1. **语义部分**：用于决定双方对话的类型
2. **语法部分**：用于决定双方对话的格式
3. **变换规则**：用于决定通信双方的应答关系。 

 #### OSI（开放系统互连参考模型）

> *开放系统互连参考模型力求将网络简化，并以模块化的方式来设计网络。*

**开放系统互连参考模型**把计算机网络分为**物理层**、**数据链路层**、**网络层**、**传输层**、**会话层**、**表示层**、**应用层**七层。

![OIS](http://upload-images.jianshu.io/upload_images/1156719-afc57efbe98be4f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### IP地址和端口号

> *IP地址用于唯一地标识网络中的一个通信实体，IP地址是数字型的，是一个32位的整数，分为4个8位的二进制数，中间用.分开，每个8位整数可以转换成一个0~255的十进制整数。一个IP地址可以对应多个域名，一个域名只能对应一个IP地址。*
>
> *IP地址用于唯一地标识网络中的一个通信实体，但一个通信实体可以有多个通信程序同时提供网络服务，此时就需要使用端口。端口是一个16位整数，用于表示数据交给哪个程序处理。端口就是应用程序与外界交流的出入口，它是一种抽象的软件结构，包括一些数据结构和I/O缓冲区。*

### Java的基本网络支持

####  使用InetAddress

> *Java提供InetAddress类来代表IP地址，InetAddress有两个子类：Inet4Address、Inet6Address，它们分别代表IPv4地址和IPv6地址。*

InetAddress类没有提供构造器，而是提供了两个静态方法来获取InetAddress实例

1. getByName(String host)：提供主机获取对应的InetAddress对象。
2. getByAddress(byte[] addr)：根据原始IP地址来获取对应的InetAddress对象。

InetAddress提供了三个方法来获取InetAddress实例对应的IP地址和主机名

1. String getCanonicalHostName()：获取此IP地址的全限定域名。
2. String getHostAddress()：返回该InetAddress实例对应的IP地址字符串。
3. String getHostName()：获取此IP地址的主机名。

**getLocalHost()**方法获取本机IP地址对应的InetAddress实例。

**isReachable()**方法：用于测试是否到达该地址。

#### 使用URLDecoder和URLEncoder

1. URLDecoder类包含一个decode(String s, String encode)静态方法，它可以将application/x-www-form-urlencoded MIME字符串转换成普通字符串。
2. URLEncoder类包含一个encode(string s, String encode)静态方法，它可以将普通字符串转换为application/x-www-form-urlencoded MIME字符串。 

#### 使用URL、URLConnection和URLPermission

> *URL对象代表同意资源定位器，它是指向互联网“资源”的指针。通常有协议名、主机、端口和资源组成。*

URL类

* String getFile()；获得该URL的资源名。
* String getHost()；获取该URL的主机名。
* String getPath()；获取该URL的路径部分。
* int getPort()；获取该URL的端口号。
* String getProtocol()；获取该URL协议名称。
* String getQuery();获取该URl的查询字符串部分。
* URLConnection openConnection()；返回一个URLConnection对象，它代表了与URL所引用的远程对象的连接。
* InputStream openStream()；打开与此URL的连接，并返回一个用于读取该URL资源的InputStream。

URLPermission工具类

> *用于管理HttpURLConnection的权限问题，如果在HttpURLConnection安装了安全管理器，通过该对象打开连接时就需要先获得权限。*

**创建一个和URL的连接，并发送请求、读取此URL引用的资源的步骤：**

1. 通过调用URL对象的openConnection()方法来创建URLConnection对象。
2. 设置URLConnection的参数和普通请求属性。
3. 如果只是发送GET方式请求，则使用connect()方法建立和远程资源之间的实际连接即可；如果是发送POST方式请求，则需要获取URLConnection实例对应的输出流来发送请求参数。
4. 远程资源变为可用，程序可以访问远程资源的头字段或通过输入流读取远程资源的数据。

### 基于TCP协议的网络编程

> *TCP/IP通信协议是一种可靠的网络协议，它在通信的两端各建立一个Socket，从而在通信的两端之间形成网络虚拟链路。一旦建立了虚拟的网络链路，两端的成员就可以通过虚拟链路进行通信。Java是对基于TCP协议的网络通信提供了良好的封装，Java使用Socket对象来代表两端的通信端口，并通过Socket产生IO流来进行网络通信。*

#### 使用ServerSocket创建TCP服务器端

​	Java中能接收其他通信实体连接请求的类是ServerSocket，ServerSocket对象用于监听来自客户端Socket连接，如果没有连接，它将一直处于等待状态。

​	接收客户端Socket的请求时，服务器端也对应产生一个Socket：`Socket socket = serverSocket.accept();`

#### 使用Socket进行通信

创建连接到主机、30000端口的Socket：`Socket socket = new Socket("127.0.0.1", 30000);`

Socket提供了两个方法获取输入流和输出流

1. InputStream getInputStream()；返回该Socket对象对应的输入流，让程序通过该输入流从Socket中取出数据。
2. OutputStream getOutputStream()；返回该Socket对象对应的输出流，让程序通过该输出流向Socket中输出数据。

#### 多客户端进行通信——加入多线程

> *服务器为每个Socket单独启动一个想线程，每个线程负责与一个客户端进行通信。通过把Socket存入List集合中实现功能。*

#### 记录用户信息

> *客户端发来的信息要有特殊的标识，让服务器端可以判断是公聊信息还是私聊信息，每个客户端都注册一个用户名。*

#### 半关闭的Socket

1. shutdownInput()：关闭Socket的输入流，程序还可以通过该Socket的输出流输出数据。
2. shutdownOutput()：关闭该Socket的输出流，程序还可以通过该Socket的输入流读取数据。
3. 当调用Socket的shutdownOutput()或shutdownInput()方法关闭输出流或输入流之后，该Socket无法再次打开输出流或输入流，因此这种做法通常不适合保持持久通信状态的交互式应用，只适用于一站式的通信协议，例如HTTP协议。

#### 使用NIO实现非阻塞Socket通信

1. 服务器端

   1. 使用ServerSocket Channel来监听客户端的连接请求，实例为调用它的open()静态方法得到。再使用它的bind()方法指定他在某个端口监听。

      ```java
      // 通过open方法来打开一个未绑定的ServerSocketChannel实例
      ServerSocketChannel server = ServerSocketChannel.open();
      InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 30000);
      // 将该ServerSocketChannel绑定到指定的IP地址
      server.bind(inetSocketAddress);
      ```

   2. 设置为非阻塞模式，并将其注册到指定的Selector

      ```java
      // 设置ServerSocket以非阻塞的方式工作
      server.configureBlocking(false);
      // 将server注册到指定的Selector对象
      server.register(selector, SelectionKey.OP_ACCEPT);
      ```

   3. 使用Selector的select()方法监控所有注册的Channel，当它们中间有需要处理的IO操作时，该方法返回，并将对应的SelectionKey加入被选择的SelectionKey集合中，该方法返回这些Channel的数量。

   4. 遍历Selector上的每个已选择的SelectionKey。

      1. 从selector上的已选择的Key集中删除正在处理的SelectionKey`selector.selectedKeys().remove(sk);`

      2. 接收客户端请求（判断）

         ```java
         // 调用accept方法接受连接，产生服务器端的SocketChannel
         SocketChannel sc = server.accept();
         // 设置采用非阻塞模式
         sc.configureBlocking(false);
         // 将该SocketChannel注册到selector
         sc.register(selector, SelectionKey.OP_READ);
         // 将sk对应的Channel设置成准备接收其他请求
         sk.interestOps(SelectionKey.OP_ACCEPT);
         ```

      3. 读取客户端数据

      4. 反馈数据给客户端

2. 客户端

   1. 调用open静态方法创建连接到指定主机的SocketChannel。
   2. 设置SocketChannel以非阻塞的方式工作。
   3. 将sc注册到指定的Selector对象。
   4. 启动读取服务器端数据的线程。

#### 使用AIO实现非阻塞通信

1. IO操作可以分为两步：程序发出IO请求；完成实际的IO操作。

   1. 阻塞IO和非阻塞IO是针对第一步来划分的。如果发出IO请求会阻塞线程，就是阻塞IO；如果发出IO请求没有阻塞线程，就是非阻塞IO。
   2. 同步IO与异步IO的区别在第二步。如果实际的IO操作由操作系统完成，再将结果返回给应用程序，这就是异步IO；如果实际的IO需要程序本身去执行，会阻塞线程，那就是同步IO。

2. AIO的接口和实现类

   ​

   ![](http://7xrgh9.com1.z0.glb.clouddn.com/16-8-5/38898070.jpg)

   AIO提供了两个接口和三个实现类，其中AsynchronousSocketChannel和AsynchronousServerSocketChannel是支持TCP通信的异步Channel。

   AsynchronousServerSocketChannel是一个负责监听的Channel，创建可用的AsynchronousServerSocketChannel需要如下两步。

   1. 调用它的open()静态方法创建一个未监听端口的AsynchronousServerSocketChannel；
   2. 调用AsynchronousServerSocketChannel的bind()方法指定该Channel在指定地址、指定端口监听。

   AsynchronousServerSocketChannel的open()方法有两个版本：

   1. open()：创建一个默认的AsynchronousServerSocketChannel。
   2. open(AsynchronousChannelGroup group)：使用指定的AsynchronousChannelGroup来创建AsynchronousServerSocketChannel。

   AsynchronousChannelGroup是异步Channel的分组管理器，它可以实现资源共享。创建AsynchronousChannelGroup时需要传入一个ExecutorService，也就是说，它会绑定一个线程池，该线程池负责两个任务：处理IO事件和触发CompletionHandler。

   * 直接创建AsynchronousServerSocketChannel

     ```java
     // 以指定线程池来创建一个AsynchronousServerSocketChannel
     serverChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(PORT));
     ```

   * 使用AsynchronousChannelGroup创建AsynchronousServerSocketChannel

     ```java
     // 创建一个线程池
     ExecutorService executor = Executor.newFixedThreadPool(80);
     // 以指定线程池来创建一个AsynchronousChannelGroup
     AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);
     // 以指定线程池来创建一个AsynchronousServerSocketChannel
     serverChannel = AsynchronousServerSocketChannel.open(channelGroup).bind(new InetSocketAddress(PORT));
     ```

   AsynchronousServerSocketChannel创建成功后，调用accept()方法接受来自客户端的连接。

   AIO的两个accept方法：

   1. **Future<AsynchronousSocketChannel> accept()**：接受客户端的请求。如果程序需要获得连接成功后返回的AsynchronousSocketChannel，则应该调用该方法返回的Future对象的get()方法——但get()方法会阻塞线程。
   2. **<A> void accept(A attachment, CompletionHandler<AsynchronousSocketChannel, ? super A> handler)**：接受来自客户端的请求，连接成功或失败都会触发CompletionHandler对象里相应的方法。其中AsynchronousSocketChannel就代表连接成功后返回的AsynchronousSocketChannel。
      1. completed(V result, A attachment)：当IO操作完成时触发该方法。该方法第一个参数代表IO操作所返回的对象；第二个参数代表发起IO操作时传入的附加参数。
      2. failed(Throwable exc, A attachment)：当IO操作失败时触发该方法。该方法第一个参数代表IO操作失败引发的异常或错误；第二个参数代表发起IO操作时传入的附加参数。

   **服务器端代码：**

   ```java
   import java.io.IOException;
   import java.net.InetSocketAddress;
   import java.nio.ByteBuffer;
   import java.nio.channels.AsynchronousServerSocketChannel;
   import java.nio.channels.AsynchronousSocketChannel;
   import java.util.concurrent.ExecutionException;
   import java.util.concurrent.Future;

   /**
    * AIO通信服务器端
    * Created by 郑俊铭 on 2017/7/15.
    */
   public class SimpleAIOServer {
       private static final int PORT = 30000;

       public static void main(String[] args) throws IOException {
           try (
                   AsynchronousServerSocketChannel serverSocketChannel =
                           AsynchronousServerSocketChannel.open()
           ) {
               serverSocketChannel.bind(new InetSocketAddress(PORT));
               while (true) {
                   Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
                   AsynchronousSocketChannel socketChannel = future.get();
                   socketChannel.write(ByteBuffer.wrap("Hello, AIO".getBytes("UTF-8"))).get();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }
       }
   }
   ```

   AsynchronousSocketChannel的用法也分为三步：

   1. 调用open()静态方法创建AsynchronousSocketChannel。调用open()方法时同样可指定一个AsynchronousChannelGroup作为分组管理器。
   2. 调用AsynchronousSocketChannel的connect()方法连接到指定IP地址、指定端口的服务器。
   3. 调用AsynchronousSocketChannel的read()、write()方法进行读写。

   **客户端代码：**

   ```java
   import java.io.IOException;
   import java.net.InetSocketAddress;
   import java.nio.ByteBuffer;
   import java.nio.channels.AsynchronousSocketChannel;
   import java.nio.charset.Charset;
   import java.util.concurrent.ExecutionException;

   /**
    * AIO客户端
    * Created by 郑俊铭 on 2017/7/15.
    */
   public class SimpleAIOClient {
       private static final int PORT = 30000;

       public static void main(String[] args) throws IOException {
           ByteBuffer buffer = ByteBuffer.allocate(1024);
           Charset charset = Charset.forName("UTF-8");
           try (
                   AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
           ) {
               channel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
               buffer.clear();
               // 从channel读数据
               channel.read(buffer).get();
               buffer.flip();
               String content = charset.decode(buffer).toString();
               System.out.println("服务器信息：" + content);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (ExecutionException e) {
               e.printStackTrace();
           }
       }
   }
   ```

### 基于UDP协议的网络编程

> *用户数据报协议，主要用来支持需要在计算机之间传输数据的网络连接。UDP协议是一种不可靠的网络协议，它在通信实例的两端各建立一个Socket，但这两个之间并没有虚拟链路，这两个Socket只是发送、接收数据报的对象。Java提供了DatagramSocket对象作为基于UDP协议的Socket，使用DatagramPacket代表DatagramSocket发送、接收的数据报。*

* UDP协议的主要作用是完成网络数据流和数据报之间的转换——在信息的发送端，UDP协议将网络数据流封装成数据报，然后将数据报发送出去；在信息的接收端，UDP协议将数据报转换成实际数据内容。

#### UDP协议和TCP协议的对比

1. TCP协议：可靠，传输大小无限制，但是需要连接建立时间，差错控制开销大。
2. UDP协议：不可靠，差错控制开销小，传输大小限制在64KB以下，不需要建立连接。

#### 使用DatagramSocket发送、接收数据

> *DatagramSocket本身只是码头，不维护状态，不能产生IO流，它的唯一作用就是接收和发送数据报。*

1. receive(DatagramPacket p)：从该DatagramSocket中接收数据报。
2. send(DatagramPacket p)：以该DatagramSocket对象向外发送数据报。

#### 使用DatagramPacket

1. InetAddress getAddress()：当程序准备发送此数据报时，该方法返回此数据报的目标机器的IP地址；当程序刚接收到一个数据报时，该方法返回该数据报的发送主机的IP地址。
2. int getPort()：当程序准备发送此数据报时，该方法返回此数据报的目标机器的端口；当程序刚接收到一个数据报时，该方法返回该数据报的发送主机的端口。
3. SocketAddress getSocketAddress()：当程序准备发送此数据报时，该方法返回此数据报的目标机器的SocketAddress；当程序刚接收到一个数据报时，该方法返回该数据报的发送主机的SocketAddress。

#### 使用MulticastSocket实现多点广播

1. public MulticastSocket()：使用本机默认地址，随机端口创建。

2. public MulticastSocket(int port)：使用本机默认地址，指定端口创建。

3. public MulticastSocket(SocketAddress bindaddr)：使用本机指定IP地址、指定端口创建。

4. joinGroup(InetAddress multicastAddr)：将该MulticastSocket加入指定的多点广播地址。

5. leaveGroup(InetAddress multicastAddr)：让该MulticastSocket离开指定的多点广播地址。

6. 有多个网络接口时，需要在一个指定的网络接口上监听，通过调用setInterface()方法可以强制MulticastSocket使用指定的网络接口；也可以使用getInterface()方法查询MulticastSocket监听的网络接口。

   ​