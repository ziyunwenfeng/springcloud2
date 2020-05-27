package com.example.demo.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * mybatis拦截器
 *
 * @author wenfeng
 * @date 2020年 05月26日 10:26:10
 */
@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class,
                        Object.class,
                        RowBounds.class,
                        ResultHandler.class})
})
@Slf4j
//@Component
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("--------------------");
        MappedStatement statement = (MappedStatement)invocation.getArgs()[0];
        Object parameter =null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = statement.getId();
        BoundSql boundSql = statement.getBoundSql(parameter);
        Configuration configuration = statement.getConfiguration();
        Object result =  invocation.proceed();
        String sql = getSql(configuration,boundSql,sqlId);
//        String sql = boundSql.getSql();
        log.info(sql);
        return result;
    }

    @Override
    public Object plugin(Object target) {
        log.info("77777777777777777");
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("////////////////");
    }

    public static String getSql(Configuration configuration,BoundSql boundSql,String sqlId) {
        Object sqlParam = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql();
        if (parameterMappings.size() > 0 && sqlParam != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(sqlParam.getClass())) {
                sql = sql.replaceFirst("\\?",getParameterValue(sqlParam));
            } else {
                MetaObject metaObject = configuration.newMetaObject(sqlParam);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        sql = sql.replaceFirst("\\?",getParameterValue(metaObject));
                    } else {
                        Object object = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(object));
                    }
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object object) {
        String value ;
        if (object instanceof String) {
            value = "'" + object.toString() + "'";
        } else if (object instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (object != null) {
                value = object.toString();
            } else {
                value = "";
            }
        }
        return value != null ? makeQueryStringAllRegExp(value):value;
    }

    private static String makeQueryStringAllRegExp(String str) {
        if (str != null && !str.equals("")) {
            return str.replace("\\", "\\\\").replace("*", "\\*")
                    .replace("+", "\\+").replace("|", "\\|")
                    .replace("{", "\\{").replace("}", "\\}")
                    .replace("(", "\\(").replace(")", "\\)")
                    .replace("^", "\\^").replace("$", "\\$")
                    .replace("[", "\\[").replace("]", "\\]")
                    .replace("?", "\\?").replace(",", "\\,")
                    .replace(".", "\\.").replace("&", "\\&");
        }
        return str;
    }
}
