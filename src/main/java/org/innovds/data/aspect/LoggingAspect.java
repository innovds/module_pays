package org.innovds.data.aspect;

import java.util.Arrays;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.innovds.data.exceptions.FunctionalException;
import org.innovds.data.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger(JoinPoint joinPoint) {
		return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
	}

	@Pointcut("execution(public * *(..))")
	private void publicMethods() {}
	
//	@Pointcut("within(@RestController *)")
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
	private void restControllers() {}
	@Pointcut("execution(public * *..web..*Controller.*(..))")
	private void restPackages() {}
	
	@Pointcut("@annotation(org.springframework.stereotype.Service)")
	private void services() {}
	@Pointcut("execution(public * *..service..*Service.*(..))")
	private void servicePackages() {}
	

	@Around("servicePackages() || services()")
	public Object serviceInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().toShortString();
		Logger log = logger(joinPoint);
		log.debug("Enter: {} - with argument[s] = {}", name, Arrays.toString(joinPoint.getArgs()));
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = null;
		Throwable exception = null;		
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			exception = e;
		}
		stopWatch.stop();
		log.debug("Exit: {} - after {} ms - with result = {}", name, stopWatch.getTotalTimeMillis(), result);
		if (exception != null) {
			throw exception;
		}
		return result;
	}

	@Around("restPackages() || restControllers()")
	public Object restInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		String name = joinPoint.getSignature().toShortString();
		Logger log = logger(joinPoint);
		log.trace("Calling api: {}", name);
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = null;
		Throwable exception = null;
		try {
			result = joinPoint.proceed();
			if (result instanceof Optional && ObjectUtils.isEmpty(result)) {
				throw new NotFoundException();
			}
		} catch (Throwable e) {
			String message = String.format("Exception in %s with cause = %s and message = %s", name, e.getCause(), e.getMessage());
			if (e instanceof FunctionalException) {
				log.warn(message);
			} else {
				log.error(message);
			}
			exception = e;
		}
		stopWatch.stop();
		long time = stopWatch.getTotalTimeMillis();
		String message = String.format("End api: %s - after %s ms", name, time);
		if (time > 15000) {
			log.error(message);
		} else if (time > 8000) {
			log.warn(message);
		} else if (time > 4000) {
			log.info(message);
		} else {
			log.trace(message);
		}
		if (exception != null) {
			throw exception;
		}
		return result;
	}

}
