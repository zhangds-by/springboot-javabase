> NoSQL

    键值      redis
    列存储     HBase
    文档数据库   MongoDB
    图形数据库
    
> 应用场景

    缓存
    任务队列
    网站访问统计
    应用排行榜
    数据过期处理
    分布式集群架构中的 session 分离

> 使用

    两种使用方式：使用注解/redisTemplate编程式
    @Cacheable 调用，先查询缓存，在查询数据库
        @Cacheable(cacheNames = "pCache", key = "pkey") key默认为方法名
    @CachePut 更新 Redis 中对应键的值
    @CacheEvict 删除 Redis 中对应键的值
    @CacheConfig(cacheNames = "product") 统一标识在类上
    
> redis 数据结构

    String 字符串，整数或浮点数
    
    List	
    
    Set	
    
    Hash
    
    Zset
   
> redis 实现减库存
  
    问题：
    1、高并发对MySQL性能影响（redis库存）
    2、超卖的根结在于减库存操作是一个事务操作，需要先select，然后insert，最后update -1。库存不存在负数（引入队列，锁机制CAS来实现减库存操作）
    3、争抢InnoDB行锁的问题，导致出现互相等待甚至死锁
    
    1、lua脚本实现
    2、分布式锁来控制只能有一个服务去初始化库存
    3、使用回调函数初始化库存（等到MQ消息消费完了才能重启redis初始化库存）