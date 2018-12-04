package com.ideacome.services.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.ideacome.common.db.config.DataSourceSwitcher;

import lombok.extern.slf4j.Slf4j;

/**
 * 参考类
 * @author 朱停
 *
 */
@Slf4j
//@Component("dataSourceAdvice")
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice{
	// service方法执行之前被调用
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.debug("切入点: " + target.getClass().getName() + "类中" + method.getName() + "方法");
        if (method.getName().startsWith("add")
            || method.getName().startsWith("create")
            || method.getName().startsWith("insert")
            || method.getName().startsWith("save")
            || method.getName().startsWith("edit")
            || method.getName().startsWith("update")
            || method.getName().startsWith("delete")
            || method.getName().startsWith("remove")) {
            log.debug("切换到: master");
            DataSourceSwitcher.setMaster();
        } else {
        	log.debug("切换到: slave");
            DataSourceSwitcher.setSlave();
        }
    }

    // service方法执行完之后被调用
    @Override
    public void afterReturning(Object arg0, Method method, Object[] args,
                               Object target) throws Throwable {
        DataSourceSwitcher.clearDataSource();
    }

    // 抛出Exception之后被调用
    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception ex) throws Throwable {
        //业务异常无需切换
        if (ex instanceof RuntimeException) {
            return;
        }
        DataSourceSwitcher.clearDataSource();
        log.error("出现异常,切换到: slave", ex);
    }
}
