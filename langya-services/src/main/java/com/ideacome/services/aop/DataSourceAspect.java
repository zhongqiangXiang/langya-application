package com.ideacome.services.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ideacome.common.db.config.DataSourceSwitcher;

import lombok.extern.slf4j.Slf4j;

/**
 * 切换数据库的 主从 库 
 * @author zhuting
 *
 */
@Slf4j
@Aspect
@Component
@Order(0) // 当有事务时，该优先级需高于事务的
public class DataSourceAspect {
	@Pointcut("execution(public * com.ideacome.services.bizService.*.*(..))")
	public void dataSourcePoint() {
	}

	@Before("dataSourcePoint()")
	public void deBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String methodName = request.getMethod();
		// 记录下请求内容
		log.info("URL : " + request.getRequestURL().toString());
		log.info("HTTP_METHOD : " + request.getMethod());
		log.info("IP : " + request.getRemoteAddr());
		log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		if (methodName.startsWith("add") || methodName.startsWith("create") || methodName.startsWith("insert")
				|| methodName.startsWith("save") || methodName.startsWith("edit") || methodName.startsWith("update")
				|| methodName.startsWith("delete") || methodName.startsWith("remove")) {
			log.info("切换到: master");
			DataSourceSwitcher.setMaster();
		} else {
			log.info("切换到: slave");
			DataSourceSwitcher.setSlave();
		}
	}
	
	@AfterReturning(returning = "ret", pointcut = "dataSourcePoint()")  
    public void doAfterReturning(Object ret) throws Throwable {  
        // 处理完请求，返回内容  
        log.info("方法的返回值 : {}",JSON.toJSONString(ret)); 
    }
	
	//后置异常通知  
    @AfterThrowing(pointcut="dataSourcePoint()",throwing="ex")  
    public void throwss(JoinPoint jp,Exception ex){  
    	log.info("方法异常时执行.....");
    	ex.printStackTrace();
    }  
  
    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("dataSourcePoint()")  
    public void after(JoinPoint jp){  
    	log.info("方法最后执行.....");  
    	// 业务逻辑执行完 回复默认的数据库
    	DataSourceSwitcher.clearDataSource();
    }
}
