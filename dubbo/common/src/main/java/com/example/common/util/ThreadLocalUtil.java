package com.example.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * thread工具类
 *
 * @author wenfeng
 * @date 2020年 05月27日 13:31:57
 */
@Slf4j
public class ThreadLocalUtil {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    public static String getThreadLocal() {
        log.info("getThreadLocal："+threadLocal.get());
        return threadLocal.get();
    }

    public static void setThreadLocal(String str) {
        log.info("setThreadLocal："+str);
        threadLocal.set(str);
    }

    public static void cleanThreadLocal() {
        log.info("cleanThreadLocal");
        threadLocal.remove();
    }
}
