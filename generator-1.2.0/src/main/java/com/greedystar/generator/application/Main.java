package com.greedystar.generator.application;

import com.greedystar.generator.invoker.Many2ManyInvoker;
import com.greedystar.generator.invoker.One2ManyInvoker;
import com.greedystar.generator.invoker.SingleInvoker;
import com.greedystar.generator.invoker.base.Invoker;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        single();
//        one2one();
        one2many();
//        many2many();
    }

    public static void many2many() throws Exception {
        Invoker invoker = new Many2ManyInvoker.Builder()
                .setTableName("org")
                .setClassName("Org")
                .setParentTableName("cou")
                .setParentClassName("Cou")
                .setRelationTableName("cou_org")
                .setForeignKey("orgId")
                .setParentForeignKey("couId")
                .build();
        invoker.execute();
    }
    public static void one2one() throws Exception {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setTableName("house")
                .setClassName("House")
                .setParentTableName("room")
                .setParentClassName("Room")
                .setForeignKey("roomId")
                .build();
        invoker.execute();
    }
    public static void one2many() throws Exception {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setTableName("person")
                .setClassName("Person")
                .setParentTableName("idcard")
                .setParentClassName("Idcard")
                .setForeignKey("personId")
                .build();
        invoker.execute();
    }
    //单表
    public static void single() throws Exception {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("t_card")
                .setClassName("Card")
                .build();
        invoker.execute();
    }

}
