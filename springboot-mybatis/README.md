> mybatis 原理

    通过 SqlSessionFactoryBuilder 在配置文件中获取 SqlSessionFactory (核心) 实例 
        包括获取数据库连接实例的数据源（DataSource）以及 决定事务作用域和控制方式的事务管理器（TransactionManager）
        
    从 SqlSessionFactory 中 获取 SqlSession 实例来执行sql语句
    
    //保证每一次响应后 SqlSession 都能正常关闭 
    try (SqlSession session = sqlSessionFactory.openSession()) {
    }
    
> mapper.xml

    cache – 该命名空间的缓存配置。
    cache-ref – 引用其它命名空间的缓存配置。
    resultMap – 描述如何从数据库结果集中加载对象，是最复杂也是最强大的元素。
    parameterMap – 老式风格的参数映射。此元素已被废弃，并可能在将来被移除！请使用行内参数映射。文档中不会介绍此元素。
    sql – 可被其它语句引用的可重用语句块。
    insert – 映射插入语句。
    update – 映射更新语句。
    delete – 映射删除语句。
    select – 映射查询语句。
    
    <select id="selectPerson" parameterType="int" resultType="hashmap">
      SELECT * FROM PERSON WHERE ID = #{id}
    </select>
    
    理解为
    String selectPerson = "SELECT * FROM PERSON WHERE ID=?";
    PreparedStatement ps = conn.prepareStatement(selectPerson);
    ps.setInt(1,id);
    
    可供参数设置
    <select
      id="selectPerson"
      parameterType="int"
      parameterMap="deprecated"
      resultType="hashmap"
      resultMap="personResultMap"
      flushCache="false"
      useCache="true"
      timeout="10"
      fetchSize="256"
      statementType="PREPARED"
      resultSetType="FORWARD_ONLY">
      
      useGeneratedKeys="true" 自动生成主键
      <insert id="insertAuthor" useGeneratedKeys="true" keyProperty="id">
        insert into Author (username,password,email,bio)
        values (#{username},#{password},#{email},#{bio})
      </insert>
      
      多行插入
      <insert id="insertAuthor" useGeneratedKeys="true" keyProperty="id">
        insert into Author (username, password, email, bio) values
        <foreach item="item" collection="list" separator=",">
          (#{item.username}, #{item.password}, #{item.email}, #{item.bio})
        </foreach>
      </insert>
      
      <update
        id="updateAuthor"
        parameterType="domain.blog.Author"
        flushCache="true"
        statementType="PREPARED"
        timeout="20">
      
      <delete
        id="deleteAuthor"
        parameterType="domain.blog.Author"
        flushCache="true"
        statementType="PREPARED"
        timeout="20">
        
> 动态sql语句

    if
    choose (when, otherwise)
    trim (where, set)
    foreach
    
    if语句
    <select id="findActiveBlogWithTitleLike" resultType="Blog">
      SELECT * FROM BLOG
      WHERE state = ‘ACTIVE’
      <if test="title != null">
        AND title like #{title}
      </if>
    </select> 
    
    choose when otherwise 语句
    <select id="findActiveBlogLike" resultType="Blog">
      SELECT * FROM BLOG WHERE state = ‘ACTIVE’
      <choose>
        <when test="title != null">
          AND title like #{title}
        </when>
        <when test="author != null and author.name != null">
          AND author_name like #{author.name}
        </when>
        <otherwise>
          AND featured = 1
        </otherwise>
      </choose>
    </select>
    
    where 语句 ：只有在子句中返回内容才会插入 where 关键字并去除AND 和 OR
    <select id="findActiveBlogLike" resultType="Blog">
      SELECT * FROM BLOG
      <where>
        <if test="state != null">
             state = #{state}
        </if>
        <if test="title != null">
            AND title like #{title}
        </if>
        <if test="author != null and author.name != null">
            AND author_name like #{author.name}
        </if>
      </where>
    </select>
    
    trim 定义
    <trim prefix="WHERE" prefixOverrides="AND |OR ">
      ...
    </trim>