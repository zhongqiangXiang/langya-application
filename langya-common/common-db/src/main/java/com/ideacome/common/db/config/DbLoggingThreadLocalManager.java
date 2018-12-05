package com.ideacome.common.db.config;

/**
 * 数据库sql 输出管理
 * 
 * @author 罗成
 */
public class DbLoggingThreadLocalManager {

    private DbLoggingThreadLocalManager() {}

    /**
     * 存放是否
     */
    private static final ThreadLocal<Boolean> THREAD_LOCAL_LOGGING = new ThreadLocal();


    /**
     * 不需要输出日志
     *
     * @return
     */
    public static void doNotNeedLogging() {
        THREAD_LOCAL_LOGGING.set(Boolean.FALSE);
    }


    /**
     * 是否需要输出日志
     * 
     * @return
     */
    static boolean isNeedLogging() {
        Boolean needLogging = THREAD_LOCAL_LOGGING.get();
        if (needLogging == null) {
            return true;
        }
        THREAD_LOCAL_LOGGING.remove();
        return false;
    }

}
