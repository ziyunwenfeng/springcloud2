package com.greedystar.generator.invoker;

import com.greedystar.generator.invoker.base.BaseBuilder;
import com.greedystar.generator.invoker.base.BaseInvoker;
import com.greedystar.generator.task.*;
import com.greedystar.generator.utils.GeneratorUtil;
import com.greedystar.generator.utils.StringUtil;

import java.sql.SQLException;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class Many2ManyInvoker extends BaseInvoker {

    @Override
    protected void getTableInfos() throws SQLException {
        tableInfos = connectionUtil.getMetaData(tableName);
        parentTableInfos = connectionUtil.getMetaData(parentTableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.add(new DaoTask(className,tableInfos));
        taskQueue.add(new ServiceTask(className,tableInfos));
        taskQueue.add(new ControllerTask(className,tableInfos));
        taskQueue.add(new DaoTask(parentClassName,parentTableInfos));
        taskQueue.add(new ServiceTask(parentClassName,parentTableInfos));
        taskQueue.add(new ControllerTask(parentClassName,parentTableInfos));
        taskQueue.add(new EntityTask(className, parentClassName, foreignKey, parentForeignKey, tableInfos));
//        taskQueue.add(new EntityTask(parentClassName, parentTableInfos));
        taskQueue.add(new EntityTask(parentClassName, className, parentForeignKey, foreignKey, parentTableInfos));
        taskQueue.add(new MapperTask(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos));
        taskQueue.add(new MapperTask(parentTableName, parentClassName,tableName, className, parentForeignKey, foreignKey, relationalTableName, tableInfos, tableInfos));
    }

    public static class Builder extends BaseBuilder {
        private Many2ManyInvoker invoker = new Many2ManyInvoker();

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder setParentTableName(String parentTableName) {
            invoker.setParentTableName(parentTableName);
            return this;
        }

        public Builder setParentClassName(String parentClassName) {
            invoker.setParentClassName(parentClassName);
            return this;
        }

        public Builder setForeignKey(String foreignKey) {
            invoker.setForeignKey(foreignKey);
            return this;
        }

        public Builder setRelationTableName(String relationTableName) {
            invoker.setRelationalTableName(relationTableName);
            return this;
        }

        public Builder setParentForeignKey(String parentForeignKey) {
            invoker.setParentForeignKey(parentForeignKey);
            return this;
        }

        @Override
        public BaseInvoker build() throws Exception {
            if (!isParamtersValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getParentTableName())) {
                throw new Exception("Expect parent table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getRelationalTableName())) {
                throw new Exception("Expect relational table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getForeignKey())) {
                throw new Exception("Expect foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getParentForeignKey())) {
                throw new Exception("Expect parent foreign key, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
            if (StringUtil.isBlank(invoker.getParentClassName())) {
                invoker.setParentClassName(GeneratorUtil.generateClassName(invoker.getParentTableName()));
            }
        }
    }

}
