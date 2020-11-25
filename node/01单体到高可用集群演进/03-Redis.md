# Redis基础

## 1.Redis 简介

简单来说 **Redis 就是一个使用 C 语言开发的数据库**，不过与传统数据库不同的是 **Redis 的数据是存在内存中的** ，也就是它是内存数据库，所以读写速度非常快，因此 Redis 被广泛应用于缓存方向。

另外，**Redis 除了做缓存之外，Redis 也经常用来做分布式锁，甚至是消息队列。**

**Redis 提供了多种数据类型来支持不同的业务场景。Redis 还支持事务 、持久化、Lua 脚本、多种集群方案。**

## 2.为什么要用 Redis/为什么要用缓存？

简单，来说使用缓存主要是为了提升用户体验以及应对更多的用户。

**高性能** ：

假如用户第一次访问数据库中的某些数据的话，这个过程是比较慢，毕竟是从硬盘中读取的。但是，如果说，用户访问的数据属于高频数据并且不会经常改变的话，那么我们就可以很放心地将该用户访问的数据存在缓存中。

**这样有什么好处呢？** 那就是保证用户下一次再访问这些数据的时候就可以直接从缓存中获取了。操作缓存就是直接操作内存，所以速度相当快。

不过，要保持数据库和缓存中的数据的一致性。 如果数据库中的对应数据改变的之后，同步改变缓存中相应的数据即可！

**高并发：**

一般像 MySQL 这类的数据库的 QPS 大概都在 1w 左右（4 核 8g） ，但是使用 Redis 缓存之后很容易达到 10w+，甚至最高能达到 30w+（就单机 redis 的情况，redis 集群的话会更高）。

> QPS（Query Per Second）：服务器每秒可以执行的查询次数；

所以，直接操作缓存能够承受的数据库请求数量是远远大于直接访问数据库的，所以我们可以考虑把数据库中的部分数据转移到缓存中去，这样用户的一部分请求会直接到缓存这里而不用经过数据库。进而，我们也就提高的系统整体的并发。

## 3.什么是分布式缓存？

1. 我们可以把分布式缓存（Distributed Cache） 看作是一种内存数据库的服务，它的最终作用就是提供缓存数据的服务。

2. 使用分布式缓存之后，缓存部署在一台单独的服务器上，即使同一个相同的服务部署在再多机器上，也是使用的同一份缓存。 并且，单独的分布式缓存服务的性能、容量和提供的功能都要更加强大。

## 4.Redis 和 Memcached 的区别和共同点

**共同点** ：

1. 都是基于内存的数据库，一般都用来当做缓存使用。
2. 都有过期策略。
3. 两者的性能都非常高。

**区别** ：

1. **Redis 支持更丰富的数据类型（支持更复杂的应用场景）**。Redis 不仅仅支持简单的 k/v 类型的数据，同时还提供 list，set，zset，hash 等数据结构的存储。Memcached 只支持最简单的 k/v 数据类型。
2. **Redis 支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用,而 Memecache 把数据全部存在内存之中。**
3. **Redis 有灾难恢复机制。** 因为可以把缓存中的数据持久化到磁盘上。
4. **Redis 在服务器内存使用完之后，可以将不用的数据放到磁盘上。但是，Memcached 在服务器内存使用完之后，就会直接报异常。**
5. **Memcached 没有原生的集群模式，需要依靠客户端来实现往集群中分片写入数据；但是 Redis 目前是原生支持 cluster 模式的.**
6. **Memcached 是多线程，非阻塞 IO 复用的网络模型；Redis 使用单线程的多路 IO 复用模型。** （Redis 6.0 引入了多线程 IO ）
7. **Redis 支持发布订阅模型、Lua 脚本、事务等功能，而 Memcached 不支持。并且，Redis 支持更多的编程语言。**
8. **Memcached过期数据的删除策略只用了惰性删除，而 Redis 同时使用了惰性删除与定期删除。**



## 5.安装配置 redis

https://class.imooc.com/lesson/1228#mid=29188

1. yum 直接安装

   ```shell
   # yum install -y redis 
   ```

2. 修改配置文件

   ```shell
   # cd /etc  配置文件目录 
   # cp redis.conf redis.conf.bat 备份配置文件
   # vim redis.conf 修改配置文件
   ```

   修改内容：

   - daemonize no 修改为 daemonize yes 目的是为了让redis在linux后台运行
   - dir /var/lib/redis linux 的工作目录 这里我没有改
   - bind 0.0.0.0 ，否则只有本机能用
   - requirepass 123456 设置密码

3. 管理 redis service


   ```shel
   # systemctl start redis 启动
   # systemctl status redis 查看状态
   # systemctl stop redis 停止服务
   # systemctl restart redis 重启服务
   # systemctl enable redis 设置开机启动
   # ps -ef | grep redis 查看redis进程
   ```

 4. 使用redis客户端连接测试，

    [码云下载地址]: https://gitee.com/qishibo/AnotherRedisDesktopManager/releases

   


## 6.常见的数据类型

- String

- hash

- list

- set

- zset

  参考： http://redisdoc.com/

# SpringBoot 整合Redis

## SpringBoot 引入redis

1. 引入依赖

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   ```

   
2. 配置redis

   ```yml
   spring:
     redis:
       database: 1
       host: 192.168.1.191
       port: 6379
       password: imooc
   ```

   

3. controller 测试

   ```java
   package com.zch.controller;
   
   import com.zch.result.CommonResult;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.data.redis.core.RedisTemplate;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import springfox.documentation.annotations.ApiIgnore;
   
   /**
    * @author zch
    * @date 2020/11/25 16:25
    */
   @ApiIgnore
   @RestController
   @RequestMapping("/redis")
   public class RedisTestController {
   
       @Autowired
       private RedisTemplate redisTemplate;
   
       @GetMapping("/set")
       public CommonResult set(String key, String value){
           redisTemplate.opsForValue().set(key, value);
           return CommonResult.success();
       }
   
       @GetMapping("/get")
       public CommonResult get(String key){
           return CommonResult.success(redisTemplate.opsForValue().get(key));
       }
   
       @GetMapping("/delete")
       public CommonResult delete(String key){
           return CommonResult.success(redisTemplate.delete(key));
       }
   }
   
   ```

   
