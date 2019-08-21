package com.sieyuan.apple.interceptor;

import com.sieyuan.apple.entity.Page;
import com.sieyuan.apple.utils.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @program: producer
 * @description: my interceptor
 * @author: wenfeng
 * @create: 2019-07-12 10:34
 **/
@Slf4j
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})
})
public class MyInterceptor implements Interceptor {
    private String dataBaseType;
    //拦截目标对象
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler)invocation.getTarget();
        StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler,"delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object object = boundSql.getParameterObject();
        if (object instanceof Page<?>){
            Page<?> page = (Page<?>)object;
            MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate,"mappedStatement");
            Connection connection = (Connection)invocation.getArgs()[0];
            String sql = boundSql.getSql();
            this.setTotalRecord(page,mappedStatement,connection);
            String pageSql = this.getPageSql(page,sql);
            ReflectUtil.setFieldValue(boundSql,"sql",pageSql);
        }
        return invocation.proceed();

    }
    //包装目标对象，为目标对象创建动态代理
    @Override
    public Object plugin(Object o) {
        log.info("要包装的对象"+o);
        Object target = Plugin.wrap(o,this);
        return target;
    }
    //获取插件初始参数
    @Override
    public void setProperties(Properties properties) {
        this.dataBaseType = properties.getProperty("param");
    }

    private void setTotalRecord(Page<?> page, MappedStatement mappedStatement,Connection connection){
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        String sql = boundSql.getSql();
        String countSql = this.getCountSql(sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(),countSql,parameterMappings,page);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,page,countBoundSql);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            parameterHandler.setParameters(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            int totalRecords = 0;
            if (resultSet.next()) {
                totalRecords++;
                page.setTotalPage(totalRecords);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private String getPageSql(Page<?> page, String sql){
        StringBuffer sb = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(dataBaseType)) {
            return getMysqlPageSql(page,sb);
        } else
            return getOraclePageSql(page,sb);
    }

    private String getMysqlPageSql(Page<?> page, StringBuffer sql) {
        int offset = (page.getPageNo()-1)*page.getPageSize();
        StringBuffer sb = sql.append(" limit ").append(offset).append(",").append(page.getPageSize());
        return sb.toString();
    }

    private String getOraclePageSql(Page<?> page, StringBuffer sql) {
        return sql.toString();
    }

    private String getCountSql(String sql){
        int index = sql.indexOf("from");
        return "select count(*) " + sql.substring(index);
    }
}
