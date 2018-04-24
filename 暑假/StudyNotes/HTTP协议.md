[TOC]

# HTTP协议

> *HTTP协议是Hyper Text Transfer Protocol（超文本传输协议）的缩写,是用于从万维网（WWW:World Wide Web ）服务器传输超文本到本地浏览器的传送协议。HTTP是一个基于TCP/IP通信协议来传递数据（HTML 文件, 图片文件, 查询结果等）。*

### 主要特点

1. 支持客户/服务器模式
2. 简单快速。客户向服务器请求服务时，只需传送请求方法和路径。由于HTTP协议的简单，使得HTTP服务器的程序规模小，因而通信速度很快。
3. 灵活。HTTP允许传输任意类型的数据对象，正在传输的类型由Content-Type加以标记。
4. 无连接：无连接的含义是限制每次连接只处理一个请求。
5. 无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快。
6. 基于请求/响应模型。

### HTTP协议之URL

> HTTP URL (URL是一种特殊类型的URI，包含了用于查找某个资源的足够的信息)的格式如下：
>
> *`http://host [":"port][abs_path]`：host表示合法的Internet主机域名或者IP地址；port指定一个端口号，为空则使用缺省端口80；abs_path指定请求资源的URI；如果URL中没有给出abs_path，那么当它作为请求URI时，必须以“/”的形式给出，通常这个工作浏览器自动帮我们完成。*

在浏览器输入URL之后的一系列过程

![](http://hi.csdn.net/attachment/201201/25/0_1327503443VoOv.gif)

### HTTP协议之请求

> *HTTP请求由三部分组成，分别是：请求行、消息报头、请求正文。*

![](http://hi.csdn.net/attachment/201201/25/0_13275033792bYk.gif)

请求行以一个方法符号开头，以空格分开，后面跟着请求的URI和协议的版本，格式如下：Method Request-URI HTTP-Version CRLF

其中 Method表示请求方法；Request-URI是一个统一资源标识符；HTTP-Version表示请求的HTTP协议版本；CRLF表示回车和换行（除了作为结尾的CRLF外，不允许出现单独的CR或LF字符）。  

#### 请求方法

1. GET    请求获取Request-URL所标识的资源
2. POST    在Request-URL所标识的资源后附加新的数据
3. PUT    请求服务器存储一个资源，并用Request-URL作为其标识
4. DELETE    请求服务器删除Request-URL所标识的资源

### HTTP协议之响应

> *HTTP响应也是由三个部分组成，分别是：状态行、消息报头、响应正文。*

![](http://hi.csdn.net/attachment/201201/25/0_1327503391CT1T.gif)

1. 状态行格式：`HTTP-Version Status-Code Reason-Phrase CRLF`。

   HTTP-Version表示服务器HTTP协议的版本；Status-Code表示服务器发回的响应状态代码；Reason-Phrase表示状态代码的文本描述。

2. 状态代码有三位数字组成，第一个数字定义了响应的类别，且有五种可能取值：

   * 1xx：指示信息--表示请求已接收，继续处理。
   * 2xx：成功--表示请求已被成功接收、理解、接受。
   * 3xx：重定向--要完成请求必须进行更进一步的操作。
   * 4xx：客户端错误--请求有语法错误或请求无法实现。
   * 5xx：服务器端错误--服务器未能实现合法的请求。

3. 常见状态代码、状态描述、说明：

   * 200 OK      //客户端请求成功
   * 400 Bad Request  //客户端请求有语法错误，不能被服务器所理解
   * 401 Unauthorized //请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用 
   * 403 Forbidden  //服务器收到请求，但是拒绝提供服务
   * 404 Not Found  //请求资源不存在，eg：输入了错误的URL
   * 500 Internal Server Error //服务器发生不可预期的错误
   * 503 Server Unavailable  //服务器当前不能处理客户端的请求，一段时间后可能恢复正常

### HTTP协议之消息报头

> *HTTP消息报头包括**普通报头**、**请求报头**、**响应报头**、**实体报头**。*
> *每一个报头域都是由名字+“：”+空格+值 组成，消息报头域的名字是大小写无关的。*

#### 普通报头

> *在普通报头中，有少数报头域用于所有的请求和响应消息，但并不用于被传输的实体，只用于传输的消息。*

**Cache-Control**   用于指定缓存指令，缓存指令是单向的（响应中出现的缓存指令在请求中未必会出现），且是独立的（一个消息的缓存指令不会影响另一个消息处理的缓存机制），HTTP1.0使用的类似的报头域为Pragma。

请求时的缓存指令包括：**no-cache**（用于指示请求或响应消息不能缓存）、no-store、max-age、max-stale、min-fresh、only-if-cached;

响应时的缓存指令包括：public、private、no-cache、no-store、no-transform、must-revalidate、proxy-revalidate、max-age、s-maxage.

#### 请求报头

> *请求报头允许客户端向服务器端传递请求的附加信息以及客户端自身的信息。*

* 常用的请求报头

  * **Accept**
    Accept请求报头域用于指定客户端接受哪些类型的信息。eg：Accept：image/gif，表明客户端希望接受GIF图象格式的资源；Accept：text/html，表明客户端希望接受html文本。
  * **Accept-Charset**
    Accept-Charset请求报头域用于指定客户端接受的字符集。eg：Accept-Charset:iso-8859-1,gb2312.如果在请求消息中没有设置这个域，缺省是任何字符集都可以接受。
  * **Accept-Encoding**
    Accept-Encoding请求报头域类似于Accept，但是它是用于指定可接受的内容编码。eg：Accept-Encoding:gzip.deflate.如果请求消息中没有设置这个域服务器假定客户端对各种内容编码都可以接受。
  * **Accept-Language**
    Accept-Language请求报头域类似于Accept，但是它是用于指定一种自然语言。eg：Accept-Language:zh-cn.如果请求消息中没有设置这个报头域，服务器假定客户端对各种语言都可以接受。
  * **Authorization**
    Authorization请求报头域主要用于证明客户端有权查看某个资源。当浏览器访问一个页面时，如果收到服务器的响应代码为401（未授权），可以发送一个包含Authorization请求报头域的请求，要求服务器对其进行验证。
  * **Host（发送请求时，该报头域是必需的）**
    Host请求报头域主要用于指定被请求资源的Internet主机和端口号，它通常从HTTP URL中提取出来的，eg：
    我们在浏览器中输入：`http://www.baidu.com/index.html`
    浏览器发送的请求消息中，就会包含Host请求报头域，如下：
    Host：`www.baidu.com`
    此处使用缺省端口号80，若指定了端口号，则变成：Host：`www.baidu.com`:指定端口号
  * **User-Agent**
    User-Agent请求报头域允许客户端将它的操作系统、浏览器和其它属性告诉服务器。不过，这个报头域不是必需的。

* 请求报头举例：

  ```http
  GET /form.html HTTP/1.1 (CRLF)
  Accept:image/gif,image/x-xbitmap,image/jpeg,application/x-shockwave-flash,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,/ (CRLF)
  Accept-Language:zh-cn (CRLF)
  Accept-Encoding:gzip,deflate (CRLF)
  If-Modified-Since:Wed,05 Jan 2007 11:21:25 GMT (CRLF)
  If-None-Match:W/"80b1a4c018f3c41:8317" (CRLF)
  User-Agent:Mozilla/4.0(compatible;MSIE6.0;Windows NT 5.0) (CRLF)
  Host:www.guet.edu.cn (CRLF)
  Connection:Keep-Alive (CRLF)
  (CRLF)
  ```

#### 响应报头

> *响应报头允许服务器传递不能放在状态行中的附加响应信息，以及关于服务器的信息和对Request-URI所标识的资源进行下一步访问的信息。*

* 常用的响应报头：
  * **Location**
    Location响应报头域用于重定向接受者到一个新的位置。Location响应报头域常用在更换域名的时候。
  * **Server**
    Server响应报头域包含了服务器用来处理请求的软件信息。与User-Agent请求报头域是相对应的。下面是
    Server响应报头域的一个例子：
    Server：Apache-Coyote/1.1
  * **WWW-Authenticate**
    WWW-Authenticate响应报头域必须被包含在401（未授权的）响应消息中，客户端收到401响应消息时候，并发送Authorization报头域请求服务器对其进行验证时，服务端响应报头就包含该报头域。
    eg：WWW-Authenticate:Basic realm="Basic Auth Test!"  //可以看出服务器对请求资源采用的是基本验证机制。

#### 实体报头

> *请求和响应消息都可以传送一个实体。一个实体由实体报头域和实体正文组成，但并不是说实体报头域和实体正文要在一起发送，可以只发送实体报头域。实体报头定义了关于实体正文（eg：有无实体正文）和请求所标识的资源的元信息。*

* 常用的实体报头

  * **Content-Encoding**

    Content-Encoding实体报头域被用作媒体类型的修饰符，它的值指示了已经被应用到实体正文的附加内容的编码，因而要获得Content-Type报头域中所引用的媒体类型，必须采用相应的解码机制。Content-Encoding这样用于记录文档的压缩方法，eg：Content-Encoding：gzip

  * **Content-Language**

    Content-Language实体报头域描述了资源所用的自然语言。没有设置该域则认为实体内容将提供给所有的语言阅读者。eg：Content-Language:da

  * **Content-Length**

    Content-Length实体报头域用于指明实体正文的长度，以字节方式存储的十进制数字来表示。

  * **Content-Type**

    Content-Type实体报头域用语指明发送给接收者的实体正文的媒体类型。eg：

    ```http
    Content-Type:text/html;charset=ISO-8859-1
    Content-Type:text/html;charset=GB2312
    ```

  * **Last-Modified**

    Last-Modified实体报头域用于指示资源的最后修改日期和时间。

  * **Expires**

    Expires实体报头域给出响应过期的日期和时间。为了让代理服务器或浏览器在一段时间以后更新缓存中(再次访问曾访问过的页面时，直接从缓存中加载，缩短响应时间和降低服务器负载)的页面，我们可以使用Expires实体报头域指定页面过期的时间。eg：Expires：Thu，15 Sep 2006 16:23:12 GMT

    HTTP1.1的客户端和缓存必须将其他非法的日期格式（包括0）看作已经过期。eg：为了让浏览器不要缓存页面，我们也可以利用Expires实体报头域，设置为0，jsp中程序如下：`response.setDateHeader("Expires","0");`

### HTTP/1.0的缺点

HTTP/1.0 版的主要缺点是，每个TCP连接只能发送一个请求。发送数据完毕，连接就关闭，如果还要请求其他资源，就必须再新建一个连接。

TCP连接的新建成本很高，因为需要客户端和服务器三次握手，并且开始时发送速率较慢（slow start）。所以，HTTP 1.0版本的性能比较差。随着网页加载的外部资源越来越多，这个问题就愈发突出了。

为了解决这个问题，有些浏览器在请求时，用了一个非标准的`Connection`字段。

```http
Connection: keep-alive
```

这个字段要求服务器不要关闭TCP连接，以便其他请求复用。服务器同样回应这个字段。

```http
Connection: keep-alive
```
一个可以复用的TCP连接就建立了，直到客户端或服务器主动关闭连接。但是，这不是标准字段，不同实现的行为可能不一致，因此不是根本的解决办法。

### HTTP/1.1

#### 持久连接

> *即TCP连接默认不关闭，可以被多个请求复用，不用声明`Connection: keep-alive`。*

客户端和服务器发现对方一段时间没有活动，就可以主动关闭连接。不过，规范的做法是，客户端在最后一个请求时，发送`Connection: close`，明确要求服务器关闭TCP连接。

目前，对于同一个域名，大多数浏览器允许同时建立6个持久连接。

#### 管道机制

> *即在同一个TCP连接里面，客户端可以同时发送多个请求。这样就进一步改进了HTTP协议的效率。*

#### Content-Length 字段

> *一个TCP连接现在可以传送多个回应，势必就要有一种机制，区分数据包是属于哪一个回应的。这就是`Content-length`字段的作用，声明本次回应的数据长度。*

#### 分块传输编码

使用`Content-Length`字段的前提条件是，服务器发送回应之前，必须知道回应的数据长度。

对于一些很耗时的动态操作来说，这意味着，服务器要等到所有操作完成，才能发送数据，显然这样的效率不高。更好的处理方法是，产生一块数据，就发送一块，采用"流模式"（stream）取代"缓存模式"（buffer）。

因此，1.1版规定可以不使用`Content-Length`字段，而使用"**分块传输编码**"（chunked transfer encoding）。只要请求或回应的头信息有`Transfer-Encoding`字段，就表明回应将由数量未定的数据块组成。

```http
Transfer-Encoding: chunked
```

每个非空的数据块之前，会有一个16进制的数值，表示这个块的长度。最后是一个大小为0的块，就表示本次回应的数据发送完了。

```http
HTTP/1.1 200 OK
Content-Type: text/plain
Transfer-Encoding: chunked

25
This is the data in the first chunk
1C
and this is the second one
3
con
8
sequence
0
```

#### 缺点

虽然1.1版允许复用TCP连接，但是同一个TCP连接里面，所有的数据通信是按次序进行的。服务器只有处理完一个回应，才会进行下一个回应。要是前面的回应特别慢，后面就会有许多请求排队等着。这称为"**队头堵塞**"（Head-of-line blocking）。

为了避免这个问题，只有两种方法：一是减少请求数，二是同时多开持久连接。这导致了很多的网页优化技巧，比如合并脚本和样式表、将图片嵌入CSS代码、域名分片（domain sharding）等等。如果HTTP协议设计得更好一些，这些额外的工作是可以避免的。