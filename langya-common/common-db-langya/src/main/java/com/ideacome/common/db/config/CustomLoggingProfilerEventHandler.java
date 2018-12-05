package com.ideacome.common.db.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.log.Log;
import com.mysql.jdbc.profiler.ProfilerEvent;
import com.mysql.jdbc.profiler.ProfilerEventHandler;

/**
 * 打印sql日志
 * <p>
 * 有些批处理的日志在测试环境超级恶心 所以新增 {@link DbLoggingThreadLocalManager#doNotNeedLogging()} 在查询sql之前加入这个
 * 代表下一条需要查询的sql 不输出日志
 * 
 * @author fei.guo
 *
 */
@Component
public class CustomLoggingProfilerEventHandler implements ProfilerEventHandler {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int MIN_LENGTH = 30;
    
    private Log log;

    /**
     * 要排除的关键字
     */
    private static final List<String> EXCLUDE_KEY_LIST = Arrays.asList("SET sql_mode='STRICT_TRANS_TABLES'",
                    "mysql-connector-java-", "SET character_set_results", "set session transaction read");

    @Override
    public void consumeEvent(ProfilerEvent evt) {
        if (evt.getEventType() == ProfilerEvent.TYPE_WARN) {
            log.logWarn(evt);
        } else {
            if (isNeedLog(evt.getMessage())) {
                this.log.logInfo(format(evt));
            }
        }
    }

    /**
     * 格式化日志
     * 
     * @param evt
     * @return
     */
    private Object format(ProfilerEvent evt) {
        String message = evt.getMessage().replace(LINE_SEPARATOR, " ");
        message = message.replace("\n", " ").replaceAll("[\\s]+", " ").replace("  "," ");
        return message;
    }

    /**
     * 判断是否要打印日志
     * 
     * @param message
     * @return
     */
    private boolean isNeedLog(String message) {
        // 如果为空 或者长度小于30 则不需要
        if (StringUtils.isBlank(message) || message.length() <= MIN_LENGTH) {
            return false;
        }
        // 如果包含特殊字符 则不需要
        for (String string : EXCLUDE_KEY_LIST) {
            if (message.contains(string)) {
                return false;
            }
        }
        // 这个必须放最后 因为判断的时候会移除线程里面的日志标志 设置了不需要输出日志 不需要
        return DbLoggingThreadLocalManager.isNeedLogging();
    }

    @Override
    public void destroy() {
        log = null;
    }

    @Override
    public void init(Connection conn, Properties props) throws SQLException {
        this.log = conn.getLog();
    }
}
