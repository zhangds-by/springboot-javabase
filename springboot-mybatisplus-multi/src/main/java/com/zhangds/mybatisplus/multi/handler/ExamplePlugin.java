package com.zhangds.mybatisplus.multi.handler;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Statement;
import java.util.Properties;

/**
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed) // sql执行语句处理
 * ParameterHandler (getParameterObject, setParameters) // sql参数处理
 * ResultSetHandler (handleResultSets, handleOutputParameters)  // JDBC与数据库交互，连接信息
 * StatementHandler (prepare, parameterize, batch, update, query)   // 结果集处理
 *
 * 自定义拦截器：在映射语句执行过程中的某一点进行拦截调用
 * 会拦截在 Executor 实例中所有的 “update” 方法调用
 * @author: zhangds
 * @date: 2020/10/15 14:18
 */

@Intercepts({//注意看这个大花括号，也就这说这里可以定义多个@Signature对多个地方拦截，都用这个拦截器
        @Signature(type = ResultSetHandler.class,// 拦截接口
                method = "handleResultSets", // 接口方法名
                args = {Statement.class}), // 拦截方法的入参
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class ExamplePlugin implements Interceptor {
    private Properties properties = new Properties();

    public Object intercept(Invocation invocation) throws Throwable {
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
